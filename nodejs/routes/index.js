const express = require('express');
const router = express.Router();
const storeController = require('../controllers/storeController');

router.get('/', storeController.homePage);
router.get('/add', storeController.addStore);
router.post('/add', storeController.createStore);




router.get('/hello', (req, res) => {
  res.render('hello', {
    name: 'luis',
    age: req.query.age
  });
});

//deal with query string parameters at urls, like www.teste.com/?name=luis&age=18
router.get('/query', (req, res) => {
  res.send(req.query);
});

//deal with clean url parameters like www.teste.com/luis/18
router.get('/reverse/:name', (req, res) => {
 const reverse = [...req.params.name].reverse().join('');

  res.send(reverse);
});

module.exports = router;
