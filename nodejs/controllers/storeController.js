exports.homePage = (req, res) => {
    console.log(req.name);
    res.render('index');
}

exports.addStore = (req, res) => {
    console.log(req.name);
    res.render('editStore', {title: 'Add store'});
}

exports.createStore = (req, res) => {
    res.json(req.body);
}