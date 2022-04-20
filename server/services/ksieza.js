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

async function getKsiezaMsza(id){

    const rows = await db.query(
        'select ksiadz.id, ksiadz.imie,ksiadz.nazwisko,ksiadz.stanowisko from ksiadz_msza join ksiadz on ksiadz.id=ksiadz_msza.ksiadz where msza ='+id+';'
    );

    return rows;
}
module.exports = {
    getKsieza,
    getKsiadz,
    getKsiezaMsza
}