#!/usr/bin/nodejs

// INITIALIZATION STUFF
var express = require('express');                     // import express
var app = express();                                  // create an app from express

var socket = require('socket.io-client')('https://user.tjhsst.edu/', { path: '/pckosek/socket.io/'  })
mraa = require('mraa'); //require mraa

myLed = new mraa.Gpio(13); //LED hooked up to digital pin 13 (or built in pin on Galileo Gen1 & Gen2)
myLed.dir(mraa.DIR_OUT); //set the gpio direction to output


// PORT SETUP - NUMBER SPECIFIC TO THIS SYSTEM
app.listen(process.env.PORT || 8080);              // listen for incoming connections

// -------------- variables  -------------- //


// -------------- express getter -------------- //
app.get('/', function (req, res, next) {
    res.sendFile(__dirname+'/index.html');
});

// -------------- socket initialization -------------- //
socket.on('connect', function () {
  // socket connected
  console.log('hi');
  
    socket.on('server_on',function(data){
        console.log("button on");
	    myLed.write(1); 
    });
    socket.on('server_off',function(data){
        console.log("button off");
	    myLed.write(0); 
    });
    
});