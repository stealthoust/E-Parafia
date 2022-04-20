const express = require('express');
const router = express.Router();
const wydarzenia = require('../services/wydarzenia');

router.post('/add', async function(req, res, next) {
    try {
        res.json(await wydarzenia.addWydarzenie(req.body.nazwa, req.body.typ, req.body.opis));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});


module.exports = router;