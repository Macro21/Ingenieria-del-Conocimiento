"use strict";

let data;
let event;
let positiveDecision = 'si';

$(()=> {
    data = new MyFileReader();
});

function dataReader(ev){
    event = ev;
};

function attributesReader(ev){
    data.readAttributes(ev,(err,attributes)=>{
        if(attributes && event){
            data.readData(event,attributes.length,(err,data)=>{
                if(data){
                    id3(attributes,data);
                }
                else{
                    alert("Choose a data file and then attributes file!");
                }
            });
           // console.log(attributes);
        }
        else{
            alert("Choose a data file and then attributes file!");
        }
    });
};

function id3(attributes, data){
    let attributesDomain = [];//array de arrays donde cada subarray tiene el dominio de cada attributo
    let attrInfo = []; //cuenta los ejemplos positivos del fichero y los guarda en orden de attributo leido
    let totalExamples = data.length;

    for(let i = 0; i<attributes.length; i++){
        attributesDomain.push(countAttrDomain(data,i)); 
    }
    for(let i = 0; i<attributesDomain.length-1; i++){
        attrInfo.push(countPositiveExamples(i,attributesDomain[i],attributes,data));
    }
    showInfo(attrInfo);
};

function countAttrDomain(data,attr){
    let types = [];
    for(let t of data){
        if(types.indexOf(t[attr]) === -1){
            types.push(t[attr]);
        }
    }
    return types;
};

function countPositiveExamples(attrName,attrDomain,attributes,data){
    //attrName = temperaturaExterior
    // attrDomain ["soleado","nublado","llueve"]
    //data ["soleado","caluroso","alta",...]
    let attrInfo = [];
    let dataOfAttr = {};
    dataOfAttr.attrType = attributes[attrName]; //temperaturaExterior
    dataOfAttr.nExamples = 0;
    dataOfAttr.nPositiveExamples = 0;
    for(let i = 0; i<attrDomain.length; i++){ 
        dataOfAttr.attrName = attrDomain[i];
        for(let line of data){
            if(line[attrName] === attrDomain[i]){
                dataOfAttr.nExamples++;
                if(line[line.length-1] === positiveDecision){
                    dataOfAttr.nPositiveExamples++;
                }
            }
        }
        attrInfo.push(dataOfAttr);
        dataOfAttr = new Object();
        dataOfAttr.attrType = attributes[attrName];
        dataOfAttr.nExamples = 0;
        dataOfAttr.nPositiveExamples = 0;
    }
    return attrInfo;
};

function showInfo(attrInfo){
    console.log(attrInfo);
    for(let i = 0; i < attrInfo.length; i++){
        createTable(attrInfo[i][0].attrType,2,attrInfo[i].length,attrInfo[i]);
    }
};

function createTable(title,rows,cols,attrInfo){
    let table = $('<table>');
    //Header
    let head = $('<thead>');
    let headRow = $('<tr>');
    headRow.appendTo(head);
    head.appendTo(table);
    for(let i = 0; i < cols; i++){
        headRow.append($('<th>').text(attrInfo[i].attrName + ' = ' + attrInfo[i].nExamples));
        headRow.appendTo(table);
    }
    headRow = $('<tr>');
    headRow.prependTo(head);
    let aux = $('<th>').text(title);
    aux.attr('colspan',cols);
    headRow.append(aux);
    headRow.prependTo(table);
    //Tables rows
    for (let i = 0; i < 1; i++) {
        let row = $('<tr>').appendTo(table);
        for (let j = 0; j < cols; j++) {
            let col = $('<td>');
            col.text('p'+(j+1) + ' = ' + attrInfo[j].nPositiveExamples + '/' + attrInfo[j].nExamples).appendTo(row);
        }
    }
    for (let i = 0; i < 1; i++) {
        let row = $('<tr>').appendTo(table);
        for (let j = 0; j < cols; j++) {
            let col = $('<td>');
            col.text('n' +(j+1)+' = ' + (attrInfo[j].nExamples - attrInfo[j].nPositiveExamples) + '/' + attrInfo[j].nExamples).appendTo(row);
        }
    }
    $('#table').append(table);
};