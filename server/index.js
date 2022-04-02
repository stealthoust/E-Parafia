const express = require('express')
const app = express()
const port = 3000
const mysql = require("mysql");

const conn = mysql.createConnection({
  host: "studweb.edu.int.pwste.edu.pl",
  user: "stealthoust",
  password: "INFormatyki!@",
  database: "parafia",
});


app.get('/osoby', (req, res) => {
  conn.query('SELECT * FROM osoby',(err,rows,fields)=>{
    if(!err)
      res.json(rows);
    else
      console.log(err);

  })
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
