const db = require('./db');
const config = require('../config2');



async function addMsza(data, godzina, typ){

    const result = await db.query(
        'INSERT INTO msza (data, godzina, typ) VALUES ("'+data+'","'+godzina+'","'+typ+'");'
    )

    let message = 'Błąd podczas dodawania mszy';

    if (result.affectedRows) {
        message = 'masza dodana pomyslnie';
    }

    return {message};
}

module.exports = {
    addMsza
}