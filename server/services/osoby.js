const db = require('./db');
const config = require('../config');

async function getOsoby(){

    const rows = await db.query(
        'SELECT osoby.id,osoby.imie,osoby.nazwisko,data_urodzenia,miejscowosc,' +
        'CONCAT(ksiadz.imie, " ", ksiadz.nazwisko) AS ksiadz FROM osoby INNER JOIN ksiadz' +
        ' ON osoby.id_ksiedza=ksiadz.id ORDER by osoby.id;'
    );

    return rows;
}

module.exports = {
    getOsoby
}