const passport = require('passport');

exports.login = passport.authenticate('local', {
    failureRedirect: '/login',
    failureFlash: 'Falied login',
    successRedirect: '/',
    successMessage: 'You are now logged in'
});