const express = require('express');
const router = express.Router();
const storeController = require('../controllers/storeController');
const userController = require('../controllers/userController');
const authController = require('../controllers/authController');
const {catchErrors} = require('../handlers/errorHandlers');

router.get('/', catchErrors(storeController.getStores));
router.get('/stores', catchErrors(storeController.getStores));
router.get('/add', storeController.addStore);
router.post('/add', 
  storeController.upload, 
  catchErrors(storeController.resize), 
  catchErrors(storeController.createStore)
);

router.post('/add/:id',
  storeController.upload, 
  catchErrors(storeController.resize), 
  catchErrors(storeController.updateStore)
);
router.get('/stores/:id/edit', catchErrors(storeController.editStore));
router.get('/store/:slug', catchErrors(storeController.getStoreBySlug));

router.get('/tags', catchErrors(storeController.getStoresByTag));
router.get('/tags/:tag', catchErrors(storeController.getStoresByTag));

router.get('/login', userController.loginForm);
router.get('/register', userController.registerForm);
router.post('/register', 
  userController.validateRegister, 
  userController.register, 
  authController.login
);


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
