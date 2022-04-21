const db = require('./db');
const config = require('../config2');

async function getOsoby(){

    const rows = await db.query(
        'SELECT osoby.id,osoby.imie,osoby.nazwisko,data_urodzenia,miejscowosc,' +
        'CONCAT(ksiadz.imie, " ", ksiadz.nazwisko) AS ksiadz FROM osoby INNER JOIN ksiadz' +
        ' ON osoby.id_ksiedza=ksiadz.id ORDER by osoby.id;'
    );

    return rows;
}

async function addOsoba(imie, nazwisko, data_urodzenia, miejscowosc, id_ksiedza){

    const result = await db.query(
        'INSERT INTO osoby (imie, nazwisko, data_urodzenia, miejscowosc, id_ksiedza) VALUES ("'+imie+'","'+nazwisko+'","'+data_urodzenia+'","'+miejscowosc+'",'+id_ksiedza+');'
    )

    let message = 'Błąd podczas dodawania osoby';

    if (result.affectedRows) {
        message = 'Osoba pomyślnie dodana';
    }

    return {message};
}

module.exports = {
    getOsoby,
    addOsoba
}