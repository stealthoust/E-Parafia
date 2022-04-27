const express = require('express');
const router = express.Router();
const wydarzenia = require('../services/wydarzenia');
const kalendarz = require("../services/kalendarz");

router.post('/add', async function(req, res, next) {
    try {
        res.json(await wydarzenia.addWydarzenie(req.body.nazwa, req.body.typ, req.body.opis));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});
router.post('/addmw', async function(req, res, next) {
    try {
        res.json(await wydarzenia.addWydarzenieMsza(req.body.msza,req.body.wydarzenie));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

router.get('/', async function(req, res, next) {
    try {
        res.json(await wydarzenia.getWydarzenia());
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});
module.exports = router;