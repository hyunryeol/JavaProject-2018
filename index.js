'use strict';
var express = require('express');
var mysql = require('mysql');
var logger = require('morgan');
var app = express();
var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'testing00',
    database: 'pj_pc',
    port: 3306
});
var app = express();
app.use(logger('dev'))
app.set('port', process.env.port || 3000);
connection.connect(function (err) {
    if (err) {
        console.error("ERR!", err);
    }
    else {
        console.log("Connected..\n");
    }
});
app.get("/test", function (req, res) {
        connection.query('select pc_status from pc ', function (err, rows) {
            if (err) throw err;

            console.log('Open DataBase');
            res.json(rows);
        });
});
app.post('/post', (req, res) => {
    console.log('who get in here post /users');
    var inputData;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        console.log("SSIBAR;");
    });

    res.json(connection);
    res.end();
});

app.listen(app.get('port'), function () {
    console.log('port' + app.get('port'));
});