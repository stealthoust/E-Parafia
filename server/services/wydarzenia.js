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

async function addWydarzenieMsza(msza_id,wydarzenie_id_){

    const result = await db.query(
        'INSERT INTO msza_wydarzenia (msza,wydarzenia) VALUES ("'+msza_id+'","'+wydarzenie_id_+'");'
    )

    let message = 'Błąd podczas przypisywania wydarzenia do mszy';

    if (result.affectedRows) {
        message = 'Wydarzenie przypisane pomyslnie';
    }

    return {message};
}
async function getWydarzenia(){

    const rows = await db.query(
        "SELECT * FROM `wydarzenia`;"
    );
    return rows;
}
module.exports = {
    addWydarzenie,
    getWydarzenia,
    addWydarzenieMsza
}