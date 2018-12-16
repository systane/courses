const mongoose = require('mongoose');
const Store = mongoose.model('Store');
const multer = require('multer');
const jimp = require('jimp');
const uuid = require('uuid');



const multerOptions = {
    storage: multer.memoryStorage(),
    fileFilter: function(req, file, next){
        const isPhoto = file.mimetype.startsWith('image/');
        if(isPhoto){
            next(null, true);//callback premise in node: when you call next(value, null) means that something gone wrong (error), if you call next(null, value) means that something worked.
        }
        else{
            next({message: 'That filetype isn\'t allowed'}, false);
        }
    }
}

exports.homePage = (req, res) => {
    console.log(req.name);
    res.render('index');
}

exports.addStore = (req, res) => {
    console.log(req.name);
    res.render('editStore', {title: 'Add store'});
}

exports.upload = multer(multerOptions).single('photo');

exports.resize = async(req, res, next) => {
    if(!req.file){
        next(); //If there is no file to resize, skip to the next middleware
        return; //stop this function of running further
    }
    const extension = req.file.mimetype.split('/')[1];
    req.body.photo = `${uuid.v4()}.${extension}`;
    const photo = await jimp.read(req.file.buffer)//buffer is the representation of an image on memory
    await photo.resize(800, jimp.AUTO);
    await photo.write(`./public/uploads/${req.body.photo}`);
    next();
}

exports.createStore = async(req, res) => {
    const store = await (new Store(req.body)).save();
    req.flash('success', `Successfuly create ${store.name}. Care to leave a review?`);
    res.redirect(`/store/${store.slug}`);
}

exports.getStores = async(req, res) => {
    const stores = await Store.find();
    res.render('stores', {title: 'Stores', stores});
}

exports.editStore = async(req, res) => {
    const store = await(Store.findOne({_id: req.params.id}));
    //TODO Confirm they are the owner of the store

    res.render('editStore', {title: `Edit ${store.name}`, store});
}

exports.updateStore = async(req, res) => {
    req.body.location.type = 'Point';

    const store = await Store.findOneAndUpdate({_id: req.params.id}, req.body, {
        new: true, //return the new store instead of the old one
        runValidators: true //force mongobd Validate the fields
    }).exec();

    req.flash('success', `Successfully updated <strong>${store.name}</strong>. <a href="/stores/${store.slug}">View Store -></a>`);

    res.redirect(`/stores/${store._id}/edit`);
}

exports.getStoreBySlug = async(req, res, next) => {
    const store = await Store.findOne({slug: req.params.slug});

    if(!store)return next();
    res.render('store', {title: store.name, store});
}

exports.getStoresByTag = async(req, res) => {
    const tagName = req.params.tag;
    const tagQuery = tagName || {$exists: true}; //return any store that has at least on tag property on it

    const tagsPromise = Store.getTagsList();
    const storesPromise = Store.find({tags: tagQuery});
    const [tags, stores] = await Promise.all([tagsPromise, storesPromise]);

    res.render('tag', {tags, title: 'Tags', tagName, stores});
}