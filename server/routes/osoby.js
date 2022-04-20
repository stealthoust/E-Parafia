const express = require('express');
const router = express.Router();
const osoby = require('../services/osoby');

/* GET programming languages. */
router.get('/', async function(req, res, next) {
    try {
        res.json(await osoby.getOsoby());
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

router.post('/add', async function(req, res, next) {
    try {
        res.json(await osoby.addOsoba(req.body.imie, req.body.nazwisko, req.body.dt_urodzenia, req.body.miejscowosc, req.body.id_ksiedza));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

module.exports = router;