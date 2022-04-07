const express = require('express');
const router = express.Router();
const ksieza = require('../services/ksieza');

/* GET programming languages. */
router.get('/', async function(req, res, next) {
    try {
        res.json(await ksieza.getKsieza());
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

router.get('/:id', async function(req, res, next) {
    try {
        res.json(await ksieza.getKsiadz(req.params.id));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

module.exports = router;