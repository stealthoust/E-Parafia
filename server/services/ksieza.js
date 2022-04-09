const db = require('./db');
const config = require('../config2');

async function getKsieza(){

    const rows = await db.query(
        'SELECT id,CONCAT(imie," ", nazwisko)as ksiadz from ksiadz'
    );

    return rows;
}

async function getKsiadz(ids){

    const rows = await db.query(
        'SELECT * FROM `ksiadz` WHERE id='+ids
    );

    return rows;
}

module.exports = {
    getKsieza,
    getKsiadz
}