const mongoose = require('mongoose');
const Store = mongoose.model('Store');

exports.homePage = (req, res) => {
    console.log(req.name);
    res.render('index');
}

exports.addStore = (req, res) => {
    console.log(req.name);
    res.render('editStore', {title: 'Add store'});
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