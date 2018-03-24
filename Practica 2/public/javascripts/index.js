"use strict";

let data;

$(()=> {
    data = new MyFileReader();
});

function dataReader(event){
    data.readData(event,(err,data)=>{
        if(data){
            console.log(data);
        }
        else{
            console.log("Error!");
        }
    });
};

function attributesReader(event){
    data.readAttributes(event,(err,data)=>{
        if(data){
            console.log(data);
        }
        else{
            console.log("Error!");
        }
    });
};

