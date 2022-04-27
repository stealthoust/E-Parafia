const db = require('./db');
const config = require('../config2');

async function getZakresKalendarz(){

    const rows = await db.query(
        '(SELECT DAY(data) as day, MONTH(data) as month, YEAR(data) as year FROM `msza` order by data asc LIMIT 1) UNION (SELECT DAY(data), MONTH(data), YEAR(data) FROM `msza` order by data desc LIMIT 1);'
    );
    return rows;
}

async function getWydarzeniaKalendarz(day, month, year){

    const rows = await db.query(
        "(SELECT msza.godzina,wydarzenia.nazwa, wydarzenia.typ, wydarzenia.opis FROM `msza_wydarzenia` join msza on msza_wydarzenia.msza=msza.id" +
        " join wydarzenia on wydarzenia.id=msza_wydarzenia.wydarzenia where msza.data='"+year+"-"+month+"-"+day+"');"
    );
    return rows;
}

async function getMszeToday(day, month, year){

    const rows = await db.query(
        "(SELECT * FROM msza WHERE DAY(data)="+day+" AND MONTH(data)="+month+" AND YEAR(data)="+year+")"
    );
    return rows;
}

async function getWydarzeniaMsza(id){

    const rows = await db.query(
        "SELECT msza.godzina,wydarzenia.nazwa, wydarzenia.typ, wydarzenia.opis FROM `msza_wydarzenia` " +
        "join msza on msza_wydarzenia.msza=msza.id join wydarzenia on wydarzenia.id=msza_wydarzenia.wydarzenia where msza.id="+id+";"
    );
    return rows;
}

async function addWydarzenie(nazwa,typ,opis){

    const result = await db.query(
        'INSERT INTO wydarzenia (id,nazwa,typ,opis) VALUES (null,"'+nazwa+'","'+typ+'","'+opis+'");'
    )

    let message = 'Błąd podczas dodawania wydarzenia';

    if (result.affectedRows) {
        message = 'Wydarzenie pomyślnie dodane';
    }

    return {message};
}

async function addWydarzenieMsza(msza_id,wydarzenie_id){

    const result = await db.query(
        'INSERT INTO msza_wydarzenia (msza,wydarzenia) VALUES ('+msza_id+','+wydarzenie_id+');'
    )

    let message = 'Błąd podczas przypisywania wydarzenia do mszy';

    if (result.affectedRows) {
        message = 'Wydarzenie pomyślnie przypisane';
    }

    return {message};
}

async function addMszaKsiadz(msza_id,ksiadz_id){

    const result = await db.query(
        'INSERT INTO ksiadz_msza (msza,ksiadz) VALUES ('+msza_id+','+ksiadz_id+');'
    )

    let message = 'Błąd podczas przypisywania księdza do mszy';

    if (result.affectedRows) {
        message = 'Ksiądz pomyślnie przypisany do mszy';
    }

    return {message};
}

module.exports = {
    getZakresKalendarz,
    getWydarzeniaKalendarz,
    getMszeToday,
    getWydarzeniaMsza,
    addWydarzenie,
    addWydarzenieMsza,
    addMszaKsiadz
}