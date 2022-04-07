const express = require('express')
const app = express()
const port = 3000
const osobyRouter = require("./routes/osoby");
const kalendarzRouter = require("./routes/kalendarz");
const ksiezaRouter = require("./routes/ksieza");

app.get("/", (req, res) => {
  res.json({ message: "ok" });
});

app.use("/osoby", osobyRouter);
app.use("/kalendarz", kalendarzRouter);
app.use("/ksieza", ksiezaRouter);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
