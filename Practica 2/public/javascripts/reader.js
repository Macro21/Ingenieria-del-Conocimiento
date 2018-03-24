"use strict";

function MyFileReader() {

    this.readData = function readData(event,callback) {
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.onload = function(event) {
            let data = event.target.result;
            let arrayOfLines = data.split('\n');
            let info = [];
            info = dataParser(arrayOfLines);
            if(info.length <= 0){
                callback(undefined);
            }
            else{
                callback(null,info);
            }
        };
        reader.readAsText(file);
    };

    function dataParser(arrayOfLines){
        let info = [];
        let infoObj = {
            weather : "", //temeratura exterior
            temp : "", //temperatura
            humidity : "",
            wind : "",
            play : ""
        };
        for(let line of arrayOfLines){
            let arrayOfAttr = line.split(',');
            infoObj.weather = arrayOfAttr[0];
            infoObj.temp = arrayOfAttr[1];
            infoObj.humidity = arrayOfAttr[2];
            infoObj.wind = arrayOfAttr[3];
            infoObj.play = arrayOfAttr[4];
            info.push(infoObj);
            infoObj = new Object();
        }
        return info;
    };

    this.readAttributes = function readAttributes(event) {
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.onload = function(event) {
            let data = event.target.result;
            let arrayOfLines = data.split('\n');
            attributesParser(arrayOfLines);
        };
        reader.readAsText(file);
    };

    function attributesParser(arrayOfLines){
        console.log(arrayOfLines);
    };
};