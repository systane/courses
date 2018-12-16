const mongoose = require('mongoose');
const User = mongoose.model('User');
const promisify = require('es6-promisify');

exports.loginForm = (req, res) => {
    res. render('login', {title: 'Login'});
}

exports.registerForm = (req, res) => {
    res. render('register', {title: 'Register'});
}

exports.validateRegister = (req, res, next) => {
    req.sanitizeBody('name');
    req.checkBody('name', 'You must supply a name!').notEmpty();
    
    req.sanitizeBody('email').normalizeEmail({
        remove_dots: false,
        remove_extension: false,
        gmail_remove_subaddress: false,
    });
    req.checkBody('email', 'That Email is not valida!').notEmpty().isEmail();

    req.checkBody('password', 'Password cannot be blank!').notEmpty();
    req.checkBody('password-confirm', 'Confirmed Password cannot be blank!').notEmpty();
    req.checkBody('password-confirm', 'Oops! Your passwords do not match').equals(req.body.password);

    const errors = req.validationErrors();

    if(errors){
        req.flash('error', errors.map(err => err.msg));
        res.render('register', {title: 'Register', body: req.body, flashes: req.flash()});
        return; //stop the function from running
    }
    next();//there were no errors - continue with the process of save the register

}

exports.register = async (req, res, next) => {
    const user = new User({email: req.body.email, name: req.body.name});
    
    //We need pass User as second argument because User.register isn't a top level function, so in order to bind this function to its Object, you must pass the object as second argument
    const register = promisify(User.register, User); //turn possible return a promise from a function callback based (User.register(entity, callbackFunction))
    await register(user, req.body.password);
    next(); // pass to authController.login
}

exports.account = (req, res) => {
    res.render('account', {title: 'Edit your account'});
}

exports.updateAccount = async (req, res) => {
    const updates = {
        name: req.body.name,
        email: req.body.email
    };

    const user = await User.findOneAndUpdate(
        {_id: req.user._id}, //query
        {$set: updates}, //updates
        {new: true, runValidators: true, context: 'query'} //options do validate the data that will be update, because mongobd just validate the date when saving in the 1° time
    );
    
    req.flash('success', 'Updatetd the profile');
    res.redirect('back'); //send the users right back to the other router that they have
}