const express = require('express')
const app = express()
const port = 3000
const mysql = require("mysql");

const conn = mysql.createConnection({
    host: "sql11.freemysqlhosting.net",
    user: "sql11483393",
    password: "rM1fKsgC1K",
    database: "sql11483393",
});


app.get('/osoby', (req, res) => {
  conn.query('SELECT osoby.id,osoby.imie,osoby.nazwisko,data_urodzenia,miejscowosc,' +
      'CONCAT(ksiadz.imie, " ", ksiadz.nazwisko) AS ksiadz FROM osoby INNER JOIN ksiadz' +
      ' ON osoby.id_ksiedza=ksiadz.id ORDER by osoby.id;',(err,rows,fields)=>{
    if(!err)
      res.json(rows);
    else
      console.log(err);

  })
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
