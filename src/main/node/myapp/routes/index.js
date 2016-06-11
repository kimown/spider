'use strict';
var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


router.get('/json', function(req, res, next) {
  let a={aa:"sdfsdfsdf"};
  res.json(a);
  //res.render('index', { title: 'Express' });
});



module.exports = router;
