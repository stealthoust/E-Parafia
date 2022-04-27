const express = require('express');
const router = express.Router();
const msze = require('../services/msze');

/* GET programming languages. */

router.post('/add', async function(req, res, next) {
    try {
        res.json(await msze.addMsza(req.body.data, req.body.godzina, req.body.typ));
    } catch (err) {
        console.error(`Error while getting programming languages `, err.message);
        next(err);
    }
});

module.exports = router;