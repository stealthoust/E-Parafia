const db = require('./db');
const config = require('../config2');



async function addWydarzenie(nazwa, typ, opis){

    const result = await db.query(
        'INSERT INTO wydarzenia (nazwa, typ, opis) VALUES ("'+nazwa+'","'+typ+'","'+opis+'");'
    )

    let message = 'Błąd podczas dodawania wydarzenia';

    if (result.affectedRows) {
        message = 'Wydarzenie dodane pomyslnie';
    }

    return {message};
}

module.exports = {
    addWydarzenie
}