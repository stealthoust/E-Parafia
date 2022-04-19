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


module.exports = {
    getZakresKalendarz,
    getWydarzeniaKalendarz,
    getMszeToday
}