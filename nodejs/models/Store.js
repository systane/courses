const mongoose = require('mongoose');
mongoose.Promise = global.Promise;
const slug = require('slugs');

const storeSchema = new mongoose.Schema({
    name:{
        type: String,
        trim: true,
        required: 'Please enter a store name!'
    },
    slug: String,
    description: {
        type: String,
        trim: true
    },
    tags: [String],
    created: {
        type: Date,
        default: Date.now
    },
    location: {
        type: {
            type: String,
            default: 'Point'
        },
        coordinates:[{
            type: Number,
            require: 'You must supply coordinates!'
        }],
        address: {
            type: String,
            require: 'You must suplly an address!'
        }
    },
    photo: String,
    author: {
        type: mongoose.Schema.ObjectId,
        ref: 'User',
        required: 'You must supply an author'
    }

});

//Define our index
storeSchema.index({
    name: 'text',
    description: 'text'
});

storeSchema.index({
    location: '2dsphere'
})

storeSchema.pre('save', async function (next) {
    if(!this.isModified('name')){
        next(); //skip it
        return; //stop this function from running
    }
    this.slug = slug(this.name);

    const slugRegEx = new RegExp(`^(${this.slug})((-[0-9]*$)?)`, 'i');
    const storesWithSlug = await this.constructor.find({slug: slugRegEx});

    if(storesWithSlug.length){
        this.slug = `${this.slug}-${storesWithSlug.length + 1}`;
    }

    next();
    //TODO make more resiliant so slugs are unique
});

storeSchema.statics.getTagsList = function() {
    return this.aggregate([
        {$unwind: '$tags'},
        {$group: {_id: '$tags', count: {$sum: 1}}},
        {$sort: {count: -1}} // count: 1 --> sort the data asc and count: -1 --> sort desc
    ]);
}

storeSchema.statics.getTopStores = function(){
    return this.aggregate([
        //lookup stores and populate their reviews
        {
            $lookup: {
                //from: 'reviews' mongoBD put our model as lowercase and add 's' at the final
                from: 'reviews', localField: '_id', 
                foreignField: 'store', as: 'reviews'
            }
        },
        //Filter for only items that have 2 or more reviews
        {
            $match: {'reviews.1': {$exists: true}} //reviews.1 --> where the second item in the reviews exist, 
        },
        //Add the average reviews field
        {
            $project: { 
                photo: '$$ROOT.photo', //$$ROOT --> It's the selector to the original document
                name: '$$ROOT.name',
                reviews: '$$ROOT.reviews',
                slug: '$$ROOT.slug',
                averageRating: {$avg: '$reviews.rating'} //create a new field.
                //'$reviews' --> The '$' sign is used to take the data that it's being piped from our match
            }
        },
        //Sort it by out new field, highest reviews first
        {$sort: {averageRating: -1}},
        //limit to at most 10
        {$limit: 10}
    ]);
}

//Find reviews where the stores _id property === reviews store property
storeSchema.virtual('reviews', {
    ref: 'Review',
    localField: '_id',
    foreignField: 'store'
}, {
    //virtual fields aren't explicit, so if you want to see them we must explicitly call the virtual property
    //but using the below option these fields become explicit
    toJson: {virtuals: true},
    toObject: {virtuals: true}
});


function autopopulate(next) {
    this.populate('reviews');
    next();
}

storeSchema.pre('find', autopopulate);
storeSchema.pre('findOne', autopopulate);

module.exports = mongoose.model('Store', storeSchema);