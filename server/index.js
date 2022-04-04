const express = require('express')
const app = express()
const port = 3000
const mysql = require("mysql");
const osobyRouter = require("./routes/osoby");



app.get("/", (req, res) => {
  res.json({ message: "ok" });
});

app.use("/osoby", osobyRouter);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
