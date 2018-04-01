"use strict";

let data;
let event;
let positiveDecision = 'si';
let nFinalDecision = 2;
let N = 0;
let infoCount = 1;

let para = 0;
let attributes; 

$(()=> {
    data = new MyFileReader();
    
    //TEMP
    
    data = [
       //TempExter, Temperatura, Humed, Viento, Jugar
        ["soleado", "caluroso", "alta", "falso", "no"],
        ["soleado", "caluroso", "alta", "verdad", "no"],
        ["nublado", "caluroso", "alta", "falso", "si"],
        ["lluvioso", "templado", "alta", "falso", "si"],
        ["lluvioso", "frio", "normal", "falso", "si"],
        ["lluvioso", "frio", "normal", "verdad", "no"],
        ["nublado", "frio", "normal", "verdad", "si"],
        ["soleado", "templado", "alta", "falso", "no"],
        ["soleado", "frio", "normal", "falso", "si"],
        ["lluvioso", "templado", "normal", "falso", "si"],
        ["soleado", "templado", "normal", "verdad", "si"],
        ["nublado", "templado", "alta", "verdad", "si"],
        ["nublado", "caluroso", "normal", "falso", "si"],
        ["lluvioso", "templado", "alta", "verdad", "no"]
    ];
    N = data.length;
    attributes = ["TiempoExterior", "Temperatura", "Humedad", "Viento", "Jugar"];
    createTree(id3(attributes,data));
    //
   // putInTree(null);
});

function dataReader(ev){
    event = ev;
};

function attributesReader(ev){
    data.readAttributes(ev,(err,attributes)=>{
        if(attributes && event){
            data.readData(event,attributes.length,(err,data,dataLen)=>{
                if(data){
                    N = dataLen;
                    id3(attributes,data);
                }
                else{
                    alert("Choose a data file and then attributes file!");
                }
            });
        }
        else{
            alert("Choose a data file and then attributes file!");
        }
    });
};

function id3(attributes, data){
    para++;
    let nodeStructure = {
        text: '',
        children : []
    };
    let attributesDomain = [];//array de arrays donde cada subarray tiene el dominio de cada attributo
    let attrInfo = []; //cuenta los ejemplos positivos del fichero y los guarda en orden de attributo leido
    let totalExamples = data.length;  
    let id3Info = [];

    for(let i = 0; i<attributes.length; i++){
        attributesDomain.push(countAttrDomain(data,i,id3Info)); 
    }
    
    for(let i = 0; i<attributesDomain.length-1; i++){
        attrInfo.push(countPositiveExamples(i,attributesDomain[i],attributes,data));
    }

    showDataInfo(attributes,data);
    showTables(attrInfo);
    let betterGainInfo = calculateGains(attrInfo);
    let decisionInfo = getID3Attributes(betterGainInfo,attrInfo[betterGainInfo.i]);

    nodeStructure.text = {name : betterGainInfo.type};//root

    for(let i of decisionInfo){
        if(i.info === 'next'){
            let newData = prepareNextData(i.attrName,data);
            N = newData.length;
            let newNode = {
                children: []
            };
            newNode.text = {name: i.attrName};
            
            let idVal = id3(attributes,newData);
            newNode.children.push(idVal);
            nodeStructure.children.push(newNode);
        } 
        else{
            nodeStructure.children.push({text: {name : i.attrName}, children: [{text: {name: i.info}}]});
        } 
    }
    return nodeStructure;
};

function createTree(nodeStructure){
    let div = $('<div>').attr('id','tree-simple').appendTo('.container');

    var simple_chart_config = {
        chart: {
            container: "#tree-simple"
        },
        nodeStructure: {
            text: { name: nodeStructure.text.name },
            children: nodeStructure.children
        }
    };
    var my_chart = new Treant(simple_chart_config);

}; 

function prepareNextData(attrName, data){
    let newData = [];

    for(let i = 0; i < data.length; i++){
        for(let j = 0; j < data[i].length; j++){
            if(data[i][j] === attrName){
                newData.push(data[i]);
            }
        }
    }
    
    return newData;
};

/**
 * @data: array of all data
 * @attr: index of data
 * @return: this function return an array with the domain of the attr. For example: ['soleado','nublado','lluvioso'] for attr 0
 */
function countAttrDomain(data,attr,id3Info){
    let types = [];
    for(let t of data){
        if(types.indexOf(t[attr]) === -1){
            if(id3Info.length > 1)
                for(let a of id3Info){
                    if(a.attrName === t[attr] && a.info === 'next'){
                        types.push(t[attr]);
                    }
                }
            else
                types.push(t[attr]);
        }
    }
    return types;
};

function countPositiveExamples(attrName,attrDomain,attributes,data){
    //attrName = temperaturaExterior
    //attrDomain ["soleado","nublado","llueve"]
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

function showTables(attrInfo){
    let div = $('<div>').attr('id','tables');
    for(let i = 0; i < attrInfo.length; i++){
        createTable(attrInfo[i][0].attrType,nFinalDecision,attrInfo[i].length,attrInfo[i],div);
    }
};

function showDataInfo(attributes,data){
    let div = $('<div>').attr('id','info'+ infoCount);
    div.css('text-align','center');
    infoCount++;
    let table = $('<table>');
    //Header
    let head = $('<thead>');
    let headRow = $('<tr>');
    headRow.appendTo(head);
    head.appendTo(table);
    for(let i = 0; i < attributes.length; i++){
        headRow.append($('<th>').text(attributes[i]));
        headRow.appendTo(table);
    }
    headRow = $('<tr>');
    headRow.prependTo(head);
    headRow.prependTo(table);
    //Tables rows
    for (let i = 0; i < data.length; i++) {
        let row = $('<tr>').appendTo(table);
        for (let j = 0; j < data[i].length; j++) {
            let col = $('<td>');
            col.text(data[i][j]).appendTo(row);
        }
    }
    div.append(table);
    $('.container').append(div);
};

function createTable(title,rows,cols,attrInfo,div){

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
    div.append(table);
    $('.container').append(div);
};

function calculateGains(attrInfo){

    let gainsInfo = [];
    let auxObj = {};
    let gainValue = 0;
    let r = 0;
    let inforV = 0;

    for(let i = 0; i < attrInfo.length; i++){
        for(let j = 0; j < attrInfo[i].length; j++){
            r = attrInfo[i][j].nExamples/N;
            let p = attrInfo[i][j].nPositiveExamples/attrInfo[i][j].nExamples;
            let n = (attrInfo[i][j].nExamples - attrInfo[i][j].nPositiveExamples) /  attrInfo[i][j].nExamples;
            inforV = infor(p,n);
            if(isNaN(inforV))
                inforV = 0;
            gainValue += (r*inforV);
        }
        gainValue = gainValue.toFixed(3);
        auxObj.i = i;
        auxObj.type = attrInfo[i][0].attrType;
        auxObj.gain = gainValue;
        gainsInfo.push(auxObj); 
        auxObj = new Object();
        gainValue = 0;
    }

    gainsInfo = sortByGain(gainsInfo);
    showGains(attrInfo,gainsInfo,gainsInfo[0].type);
    return gainsInfo[0];
};


function showGains(attrInfo,gainsInfo,mainType){
    //infor(p,n) = p log2(p) n log2(n)
    //am: mérito (am) = E(ri*x infor (pi, ni))
    //ri = ai/N;
    let div = $('<div>').attr('id','gains');
    div.append($('<h3>').text('Gains '));
    
    let gainInfo = [];

    for(let i = 0; i < attrInfo.length; i++){
        let gain = 'Gain(' + attrInfo[i][0].attrType + ') ' + ' = ';
        for(let j = 0; j < attrInfo[i].length; j++){
            gain += (attrInfo[i][j].nExamples + '/' + N + ' * infor(' + (attrInfo[i][j].nPositiveExamples + '/' + attrInfo[i][j].nExamples) +',' + (attrInfo[i][j].nExamples - attrInfo[i][j].nPositiveExamples) + '/' + attrInfo[i][j].nExamples) + ')' + ' + ';
        }
        let aux = gain.slice(0,gain.length-2);
        aux += ' = ';
        gainInfo.push(aux);
    }   
 
    let k = 0;
    for(let k = 0; k < gainsInfo.length; k++){
        div.append($('<h4>').text((k+1 )+ 'º ' + gainInfo[gainsInfo[k].i] + gainsInfo[k].gain));
    }

    div.append($('<hr>').addClass('hrStyle'));
    $('.container').append(div);
};

function getID3Attributes(betterGainInfo,attrInfo){
    let info = [];
  
    let auxObj = {};
    for(let d of attrInfo){
        let p = d.nPositiveExamples/d.nExamples;
        let n = (d.nExamples - d.nPositiveExamples) / d.nExamples;
        if(p === 1){ // yes
            auxObj.attrName = d.attrName;
            auxObj.info = 'si';
            info.push(auxObj);
        }
        else if (n === 1){
            auxObj.attrName = d.attrName;
            auxObj.info = 'no';
            info.push(auxObj);
        }
        else{
            auxObj.attrName = d.attrName;
            auxObj.info = 'next';
            info.push(auxObj);
        }
        auxObj = new Object();
    }
    return info;
};

function sortByGain(gainsInfo){
    for(let j = 0; j < gainsInfo.length; j++){
        for(let i = 0; i < gainsInfo.length - 1; i++){
            if(gainsInfo[i+1].gain < gainsInfo[i].gain){
                let aux = gainsInfo[i+1];
                gainsInfo[i+1] = gainsInfo[i];
                gainsInfo[i] = aux;
            }
        }
    }
    return gainsInfo;
}

function infor(p,n){
    //infor(p,n) = -p log2(p)- n log2(n)
    let log2p = log(2,p);
    let log2n = log(2,n);
    return -p*log2p-n*log2n;
};

function log (base,val){
    return Math.log(val) / Math.log(base);
};
