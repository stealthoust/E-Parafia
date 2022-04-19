const express = require('express');
const router = express.Router();
const kalendarz = require('../services/kalendarz');

/* GET programming languages. */

router.get('/zakresDat', async function(req, res, next) {
    try {
        res.json(await kalendarz.getZakresKalendarz());
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

router.get('/wydarzenia/:year/:month/:day', async function(req, res, next) {
    try {
        res.json(await kalendarz.getWydarzeniaKalendarz(req.params.day, req.params.month, req.params.year));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

router.get('/mszedzis/:year/:month/:day', async function(req, res, next) {
    try {
        res.json(await kalendarz.getMszeToday(req.params.day, req.params.month, req.params.year));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

module.exports = router;