"use strict";

let data;
let attributes = ['x1','x2','x3','x4'];
let N = 0;
//Kmeans
let uMatrix = [[],[-1],[-1]];
let b = 2;
let epsilon = 0.01;
let OldCentroidMatrix = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];
let centroidMatrix = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];

//Lloyd
let gammaKj = 0.1;
let epsilonLoyd = 0.0000000001;
let kMaxLoyd = 10;
let centroidMatrixLloyd = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];
let OldCentroidMatrixLloyd = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];

$(()=> {
    data =[
        "5.1,3.5,1.4,0.2,Iris-setosa",
        "4.9,3.0,1.4,0.2,Iris-setosa",
        "4.7,3.2,1.3,0.2,Iris-setosa",
        "4.6,3.1,1.5,0.2,Iris-setosa",
        "5.0,3.6,1.4,0.2,Iris-setosa",
        "5.4,3.9,1.7,0.4,Iris-setosa",
        "4.6,3.4,1.4,0.3,Iris-setosa",
        "5.0,3.4,1.5,0.2,Iris-setosa",
        "4.4,2.9,1.4,0.2,Iris-setosa",
        "4.9,3.1,1.5,0.1,Iris-setosa",
        "5.4,3.7,1.5,0.2,Iris-setosa",
        "4.8,3.4,1.6,0.2,Iris-setosa",
        "4.8,3.0,1.4,0.1,Iris-setosa",
        "4.3,3.0,1.1,0.1,Iris-setosa",
        "5.8,4.0,1.2,0.2,Iris-setosa",
        "5.7,4.4,1.5,0.4,Iris-setosa",
        "5.4,3.9,1.3,0.4,Iris-setosa",
        "5.1,3.5,1.4,0.3,Iris-setosa",
        "5.7,3.8,1.7,0.3,Iris-setosa",
        "5.1,3.8,1.5,0.3,Iris-setosa",
        "5.4,3.4,1.7,0.2,Iris-setosa",
        "5.1,3.7,1.5,0.4,Iris-setosa",
        "4.6,3.6,1.0,0.2,Iris-setosa",
        "5.1,3.3,1.7,0.5,Iris-setosa",
        "4.8,3.4,1.9,0.2,Iris-setosa",
        "5.0,3.0,1.6,0.2,Iris-setosa",
        "5.0,3.4,1.6,0.4,Iris-setosa",
        "5.2,3.5,1.5,0.2,Iris-setosa",
        "5.2,3.4,1.4,0.2,Iris-setosa",
        "4.7,3.2,1.6,0.2,Iris-setosa",
        "4.8,3.1,1.6,0.2,Iris-setosa",
        "5.4,3.4,1.5,0.4,Iris-setosa",
        "5.2,4.1,1.5,0.1,Iris-setosa",
        "5.5,4.2,1.4,0.2,Iris-setosa",
        "4.9,3.1,1.5,0.1,Iris-setosa",
        "5.0,3.2,1.2,0.2,Iris-setosa",
        "5.5,3.5,1.3,0.2,Iris-setosa",
        "4.9,3.1,1.5,0.1,Iris-setosa",
        "4.4,3.0,1.3,0.2,Iris-setosa",
        "5.1,3.4,1.5,0.2,Iris-setosa",
        "5.0,3.5,1.3,0.3,Iris-setosa",
        "4.5,2.3,1.3,0.3,Iris-setosa",
        "4.4,3.2,1.3,0.2,Iris-setosa",
        "5.0,3.5,1.6,0.6,Iris-setosa",
        "5.1,3.8,1.9,0.4,Iris-setosa",
        "4.8,3.0,1.4,0.3,Iris-setosa",
        "5.1,3.8,1.6,0.2,Iris-setosa",
        "4.6,3.2,1.4,0.2,Iris-setosa",
        "5.3,3.7,1.5,0.2,Iris-setosa",
        "5.0,3.3,1.4,0.2,Iris-setosa",
        "7.0,3.2,4.7,1.4,Iris-versicolor",
        "6.4,3.2,4.5,1.5,Iris-versicolor",
        "6.9,3.1,4.9,1.5,Iris-versicolor",
        "5.5,2.3,4.0,1.3,Iris-versicolor",
        "6.5,2.8,4.6,1.5,Iris-versicolor",
        "5.7,2.8,4.5,1.3,Iris-versicolor",
        "6.3,3.3,4.7,1.6,Iris-versicolor",
        "4.9,2.4,3.3,1.0,Iris-versicolor",
        "6.6,2.9,4.6,1.3,Iris-versicolor",
        "5.2,2.7,3.9,1.4,Iris-versicolor",
        "5.0,2.0,3.5,1.0,Iris-versicolor",
        "5.9,3.0,4.2,1.5,Iris-versicolor",
        "6.0,2.2,4.0,1.0,Iris-versicolor",
        "6.1,2.9,4.7,1.4,Iris-versicolor",
        "5.6,2.9,3.6,1.3,Iris-versicolor",
        "6.7,3.1,4.4,1.4,Iris-versicolor",
        "5.6,3.0,4.5,1.5,Iris-versicolor",
        "5.8,2.7,4.1,1.0,Iris-versicolor",
        "6.2,2.2,4.5,1.5,Iris-versicolor",
        "5.6,2.5,3.9,1.1,Iris-versicolor",
        "5.9,3.2,4.8,1.8,Iris-versicolor",
        "6.1,2.8,4.0,1.3,Iris-versicolor",
        "6.3,2.5,4.9,1.5,Iris-versicolor",
        "6.1,2.8,4.7,1.2,Iris-versicolor",
        "6.4,2.9,4.3,1.3,Iris-versicolor",
        "6.6,3.0,4.4,1.4,Iris-versicolor",
        "6.8,2.8,4.8,1.4,Iris-versicolor",
        "6.7,3.0,5.0,1.7,Iris-versicolor",
        "6.0,2.9,4.5,1.5,Iris-versicolor",
        "5.7,2.6,3.5,1.0,Iris-versicolor",
        "5.5,2.4,3.8,1.1,Iris-versicolor",
        "5.5,2.4,3.7,1.0,Iris-versicolor",
        "5.8,2.7,3.9,1.2,Iris-versicolor",
        "6.0,2.7,5.1,1.6,Iris-versicolor",
        "5.4,3.0,4.5,1.5,Iris-versicolor",
        "6.0,3.4,4.5,1.6,Iris-versicolor",
        "6.7,3.1,4.7,1.5,Iris-versicolor",
        "6.3,2.3,4.4,1.3,Iris-versicolor",
        "5.6,3.0,4.1,1.3,Iris-versicolor",
        "5.5,2.5,4.0,1.3,Iris-versicolor",
        "5.5,2.6,4.4,1.2,Iris-versicolor",
        "6.1,3.0,4.6,1.4,Iris-versicolor",
        "5.8,2.6,4.0,1.2,Iris-versicolor",
        "5.0,2.3,3.3,1.0,Iris-versicolor",
        "5.6,2.7,4.2,1.3,Iris-versicolor",
        "5.7,3.0,4.2,1.2,Iris-versicolor",
        "5.7,2.9,4.2,1.3,Iris-versicolor",
        "6.2,2.9,4.3,1.3,Iris-versicolor",
        "5.1,2.5,3.0,1.1,Iris-versicolor",
        "5.7,2.8,4.1,1.3,Iris-versicolor"
    ];
    N = data.length;
    data = formatInput(data);//return an array of objects
    kmeans(); //calculate the new centroids
    let v1 = centroidMatrix[1];
    let v2 = centroidMatrix[2];
    $('#centroid1').html('v1: &emsp;' + v1[0] + '&emsp;' + v1[1] + '&emsp;' + v1[2] + '&emsp;' + v1[3]);
    $('#centroid2').html('v2: &emsp;' + v2[0] + '&emsp;' + v2[1] + '&emsp;' + v2[2] + '&emsp;' + v2[3]);
    showInputModeKmeans();
    
    lloyd();
    showLloyd();
   // bayes();
    showBayes();
});



/**
 * This function parse the data file. 
 * Global data change and take the plant format.
 */
function formatInput(dataInput){
    let plant = {
        x1: -1,
        x2: -1,
        x3: -1,
        x4: -1,
        type: ""
    }
    let plantGroup = [{x1: -1, x2: -1, x3: -1, x4: -1, type: ""}];
    
    //p format is "[5.7,2.8,4.1,1.3,Iris-versicolor]"
    for(let p of dataInput){
        let aux = p.split(',');
        plant.x1 = aux[0];
        plant.x2 = aux[1];
        plant.x3 = aux[2];
        plant.x4 = aux[3];
        try{
            plant.type = aux[4];
        }
        catch(err){

        }
        plantGroup.push(plant);
        plant = new Object();
    }
    return plantGroup;
};


/**
 *  retrun dij
 * @param {*} x // a X object, [x1,x2,x3,x4]
 * @param {*} i // the centroid. 1 --> v1, 2 --> v2
 */
function calculateD(i, sample){
    let dij = 0;
    let centroid = centroidMatrix[i];
    let base = sample.x1 - centroid[0];
    dij += Math.pow(base, b);
    base = sample.x2 - centroid[1];
    dij += Math.pow(base, b);
    base = sample.x3 - centroid[2];
    dij += Math.pow(base, b);
    base = sample.x4 - centroid[3];
    dij += Math.pow(base, b);
    return dij;
};

//Kmeans

function calculateUmatrix(){
    let Pvixj = 0;
    for(let i = 1; i<=2; i++)
        for (let j = 1; j < data.length; j++) {
            const sample = data[j];
            let dij =  calculateD(i,sample);
            let dij2 = 0;
            if(i == 1)
                dij2 = calculateD(i+1,sample);
            else
                dij2 = calculateD(i-1,sample);
            let num = (1 / dij);
            Pvixj = num / (num + (1/dij2));
            uMatrix[i].push(Pvixj);
        }
};

function recalculateCentroid(){
    let totalDenominator1 = 0;
    let totalDenominator2 = 0;
    let totalNumerator1 = {x1: 0, x2: 0, x3: 0, x4: 0, type: ""};
    let totalNumerator2 =  {x1: 0, x2: 0, x3: 0, x4: 0, type: ""};
    let auxData = uMatrix[1];
    let aux2Data = uMatrix[2];
    
    let v1 = {x1: 0, x2: 0, x3: 0, x4: 0, type: ""};
    let v2 = {x1: 0, x2: 0, x3: 0, x4: 0, type: ""};

    for(let i = 1; i < auxData.length; i++){
        totalDenominator1 += Math.pow(auxData[i],b);
        totalDenominator2 += Math.pow(aux2Data[i],b);
         
    }

    //v1
    for(let i = 1; i < data.length; i++ && !stop){
       let pro = Math.pow(auxData[i],b);
       totalNumerator1.x1 += pro * data[i].x1;
       totalNumerator1.x2 += pro * data[i].x2;
       totalNumerator1.x3 += pro * data[i].x3;
       totalNumerator1.x4 += pro * data[i].x4;
       let pro2 = Math.pow(aux2Data[i],b);
       totalNumerator2.x1 += pro2 * data[i].x1;
       totalNumerator2.x2 += pro2 * data[i].x2;
       totalNumerator2.x3 += pro2 * data[i].x3;
       totalNumerator2.x4 += pro2 * data[i].x4;
    }

    v1.x1 = (totalNumerator1.x1 / totalDenominator1).toFixed(4);
    v1.x2 = (totalNumerator1.x2 / totalDenominator1).toFixed(4);
    v1.x3 = (totalNumerator1.x3 / totalDenominator1).toFixed(4);
    v1.x4 = (totalNumerator1.x4 / totalDenominator1).toFixed(4);

    
    v2.x1 = (totalNumerator2.x1 / totalDenominator2).toFixed(4);
    v2.x2 = (totalNumerator2.x2 / totalDenominator2).toFixed(4);
    v2.x3 = (totalNumerator2.x3 / totalDenominator2).toFixed(4);
    v2.x4 = (totalNumerator2.x4 / totalDenominator2).toFixed(4);

    centroidMatrix[1] = [v1.x1, v1.x2, v1.x3, v1.x4];
    centroidMatrix[2] = [v2.x1, v2.x2, v2.x3, v2.x4];
};


function kmeans(){
    let stop = false;
    while(!stop){
        calculateUmatrix();
        recalculateCentroid();
        let v1euc = centroidMatrix[1][0] - OldCentroidMatrix[1][0] + centroidMatrix[1][1] - OldCentroidMatrix[1][1] +
        centroidMatrix[1][2] - OldCentroidMatrix[1][2] + centroidMatrix[1][3] - OldCentroidMatrix[1][3];

        let v2euc = centroidMatrix[2][0] - OldCentroidMatrix[2][0] + centroidMatrix[2][1] - OldCentroidMatrix[2][1] +
        centroidMatrix[2][2] - OldCentroidMatrix[2][2] + centroidMatrix[2][3] - OldCentroidMatrix[2][3];

        if( v1euc < epsilon && v2euc < epsilon)
            stop = true;
        OldCentroidMatrix = centroidMatrix.slice(); //Array CLONE!!! Easy
        if(!stop)
            uMatrix = [[],[-1],[-1]];
    }
};

function solveKmeans(){
    let sample = [$('#sample').prop('value')];
    let auxData = formatInput(sample);
    let Pvixj1 = 0;
    let Pvixj2 = 0;
    let dij1 = calculateD(1,auxData[1]);
    let dij2 = calculateD(2,auxData[1]);

    let num1 = (1 / dij1);
    let num2 = (1 / dij2);
    let den = num1 + num2;
    Pvixj1 = num1 / den;
    Pvixj2 = num2 / den;
    if(Pvixj1 > Pvixj2)
        $('#sol').attr('value','Iris-setosa').removeClass().addClass('col-md-4 btn btn-success');
    else
        $('#sol').attr('value','Iris-versicolor').removeClass().addClass('col-md-4 btn btn-warning');
};


function showInputModeKmeans(){
    let div = $('<div>').attr('id','inputSample');

    let div2 =  $('<div>').addClass('form-group row');
    let input = $('<input>').attr('id','sample').addClass('offset-md-2 text-center col-md-8 form-control');
    input.attr('placeholder','Introduce la muestra separada por comas "\,\" Ej: 5.1,3.5,1.4,0.2');
    input.attr('onkeypress','enter(event)');
    div2.append(input);
    let divB = $('<div>').addClass('col-md-12 text-center')
    let button = $('<button>').attr('type','submit').addClass('btn btn-primary').text('Decide').attr('id','solveBtn');
    button.attr('onClick','solveKmeans()');

    let div3 =  $('<div>').addClass('col-md-12 text-center').attr('id','divSol');
    let lbl = $('<label>').text('Result').addClass('col-md-2').attr('id','lblSol');
    let sol = $('<input>').attr('id','sol').addClass('col-md-4 btn btn-default');
    sol.attr('placeholder','Aqui se mostrará la solución');
    sol.prop('readonly', true);

    divB.append(button);
    div2.append(divB);
    div3.append(lbl);
    div3.append(sol);
    
    div2.append(div3);
    div.append(div2);
    
    $('.container').append(div);
    
};

//Lloyd
function showLloyd(){
    let v1 = centroidMatrixLloyd[1];
    let v2 = centroidMatrixLloyd[2];
    $('.container').append($('<br>'));
    $('.container').append($('<hr>').addClass('hrStyle'));

    let lloydDiv = $('<div>').addClass('centroidDiv');
    lloydDiv.append($('<h1>').text('Algoritmo de Lloyd'));
    lloydDiv.append($('<br>'));
    lloydDiv.append($('<h1>').attr('id','centroid1Lloyd'));
    lloydDiv.append($('<h1>').attr('id','centroid2Lloyd'));
    
    $('.container').append(lloydDiv);
    $('#centroid1Lloyd').html('v1: &emsp;' + v1[0].toFixed(4) + '&emsp;' + v1[1].toFixed(4) + '&emsp;' + v1[2].toFixed(4) + '&emsp;' + v1[3].toFixed(4));
    $('#centroid2Lloyd').html('v2: &emsp;' + v2[0].toFixed(4) + '&emsp;' + v2[1].toFixed(4) + '&emsp;' + v2[2].toFixed(4) + '&emsp;' + v2[3].toFixed(4));

    let div = $('<div>');

    let div2 =  $('<div>').addClass('form-group row');
    let input = $('<input>').attr('id','sampleLloyd').addClass('offset-md-2 text-center col-md-8 form-control');
    input.attr('placeholder','Introduce la muestra separada por comas "\,\" Ej: 5.1,3.5,1.4,0.2');
    div2.append(input);
    let divB = $('<div>').addClass('col-md-12 text-center')
    let button = $('<button>').attr('type','submit').addClass('btn btn-primary').text('Decide').attr('id','solveBtn');
    button.attr('onClick','solveLloyd()');

    let div3 =  $('<div>').addClass('col-md-12 text-center').attr('id','divSol');
    let lbl = $('<label>').text('Result').addClass('col-md-2').attr('id','lblSol');
    let sol = $('<input>').attr('id','solLloyd').addClass('col-md-4 btn btn-default');
    sol.attr('placeholder','Aqui se mostrará la solución');
    sol.prop('readonly', true);

    divB.append(button);
    div2.append(divB);
    div3.append(lbl);
    div3.append(sol);
    
    div2.append(div3);
    div.append(div2);
    
    $('.container').append(div);

};

function updateLloydCentroids(){
    let v1 = centroidMatrixLloyd[1];
    let v2 = centroidMatrixLloyd[2];

    for (let j = 1; j < data.length; j++) {
        const sample = data[j];
        let d1 = calculateD(1,sample);
        let d2 = calculateD(2,sample);

        if(d1 < d2){ // se actualiza c1
            let sol = deduct(sample,v1);
            sol = multiply(gammaKj,sol);
            sol = add(sol,v1);
            centroidMatrixLloyd[1] = [sol.x1,sol.x2,sol.x3,sol.x4];
        }
        else{// se actualiza c2
            let sol = deduct(sample,v2);
            sol = multiply(gammaKj,sol);
            sol = add(sol,v2);
            centroidMatrixLloyd[2] = [sol.x1,sol.x2,sol.x3,sol.x4];
        }
    }
    
};

function lloyd(){
    let stop = false;
    let it = 0;
    
    while(!stop && it < kMaxLoyd){
        updateLloydCentroids();
        let v1euc = deductNewAndOldCentroids(centroidMatrixLloyd[1],OldCentroidMatrixLloyd[1]);
        let v2euc = deductNewAndOldCentroids(centroidMatrixLloyd[2],OldCentroidMatrixLloyd[2]);

        if( v1euc < epsilonLoyd && v2euc < epsilonLoyd)
            stop = true;
        OldCentroidMatrixLloyd = centroidMatrixLloyd.slice(); //Array CLONE!!! Easy
        it++;
    }
};


function deductNewAndOldCentroids(vNew,vOld){
    let sol = 0;
    let aux = vNew[0] - vOld[0];
    sol += Math.pow(aux,2);
    aux = vNew[1] - vOld[1];
    sol += Math.pow(aux,2);
    aux = vNew[2] - vOld[2];
    sol += Math.pow(aux,2);
    aux = vNew[3] - vOld[3];
    sol += Math.pow(aux,2);
    return Math.sqrt(sol);
};

function deduct(sample,centroid){
    let sol = {x1: -1, x2: -1, x3: -1, x4: -1, type: ""};
    sol.x1 = sample.x1 - centroid[0];
    sol.x2 = sample.x2 - centroid[1];
    sol.x3 = sample.x3- centroid[2];
    sol.x4 = sample.x4 - centroid[3];
    return sol;
};

function multiply(gammaKj,x1v1Deduct){
    x1v1Deduct.x1 *= gammaKj;
    x1v1Deduct.x2 *= gammaKj;
    x1v1Deduct.x3 *= gammaKj;
    x1v1Deduct.x4 *= gammaKj;
    return x1v1Deduct;
};

function add(sol,v1){
    sol.x1 = (sol.x1 + v1[0]);
    sol.x2 = (sol.x2 + v1[1]);
    sol.x3 = (sol.x3 + v1[2]);
    sol.x4 = (sol.x4 + v1[3]);
    return sol;
};

function solveLloyd(){
    let sample = [$('#sampleLloyd').prop('value')];
    let auxData = formatInput(sample);
    let Pvixj1 = 0;
    let Pvixj2 = 0;
    let dij1 = calculateD(1,auxData[1]);
    let dij2 = calculateD(2,auxData[1]);

    let num1 = (1 / dij1);
    let num2 = (1 / dij2);
    let den = num1 + num2;
    Pvixj1 = num1 / den;
    Pvixj2 = num2 / den;

    if(Pvixj1 > Pvixj2)
        $('#solLloyd').attr('value','Iris-setosa').removeClass().addClass('col-md-4 btn btn-success');
    else
        $('#solLloyd').attr('value','Iris-versicolor').removeClass().addClass('col-md-4 btn btn-warning');
};

//Bayes
function showBayes(){

    $('.container').append($('<br>'));
    $('.container').append($('<hr>').addClass('hrStyle'));

    let lloydDiv = $('<div>').addClass('centroidDiv');
    lloydDiv.append($('<h1>').text('Algoritmo de Bayes'));
    lloydDiv.append($('<br>'));
    lloydDiv.append($('<h1>').attr('id','centroid1Bayes'));
    lloydDiv.append($('<h1>').attr('id','centroid2Bayes'));
    
    $('.container').append(lloydDiv);

    let div = $('<div>');

    let div2 =  $('<div>').addClass('form-group row');
    let input = $('<input>').attr('id','sampleBayes').addClass('offset-md-2 text-center col-md-8 form-control');
    input.attr('placeholder','Introduce la muestra separada por comas "\,\" Ej: 5.1,3.5,1.4,0.2');
    div2.append(input);
    let divB = $('<div>').addClass('col-md-12 text-center')
    let button = $('<button>').attr('type','submit').addClass('btn btn-primary').text('Decide').attr('id','solveBtn');
    button.attr('onClick','solveBayes()');

    let div3 =  $('<div>').addClass('col-md-12 text-center').attr('id','divSol');
    let lbl = $('<label>').text('Result').addClass('col-md-2').attr('id','lblSol');
    let sol = $('<input>').attr('id','solBayes').addClass('col-md-4 btn btn-default');
    sol.attr('placeholder','Aqui se mostrará la solución');
    sol.prop('readonly', true);

    divB.append(button);
    div2.append(divB);
    div3.append(lbl);
    div3.append(sol);
    
    div2.append(div3);
    div.append(div2);
    
    $('.container').append(div);

};

function solveBayes(){
    let m1 = calculateM(1);
    let m2 = calculateM(2);
    let cov1 = covarianceMatrix(m1,1);
    let cov2 = covarianceMatrix(m2,2);
   
    let input =  $('#sampleBayes').prop('value').split(',');
    for(let i = 0; i < input.length; i++){
        input[i] = Number(input[i]);
    }
    let inv = math.inv(cov1);
    let deductXkm1 = [input[0]-m1.x1,input[1]-m1.x2, input[2]-m1.x3, input[3]-m1.x4];
    let multiply = math.multiply(deductXkm1,inv);
    let transp = math.transpose(deductXkm1);
    let result = math.multiply(transp,multiply);
   
    let inv2 = math.inv(cov2);
    let deductXkm2 = [input[0]-m2.x1,input[1]-m2.x2, input[2]-m2.x3, input[3]-m2.x4];
    let multiply2 = math.multiply(deductXkm2,inv2);
    let transp2 = math.transpose(deductXkm2);
    let result2 = math.multiply(transp2,multiply2);
   
    if(result < result2)
        $('#solBayes').attr('value','Iris-setosa').removeClass().addClass('col-md-4 btn btn-success');
    else
        $('#solBayes').attr('value','Iris-versicolor').removeClass().addClass('col-md-4 btn btn-warning');
};

/**
 * return a vector with the average for each column of data
 */
function calculateM(classs){
    let vector = {x1:0, x2:0, x3:0, x4:0};
    let size;
    let ini;
    if(classs == 1){
        size = N/2;
        ini = 1;
    }
    else{
        size = N;
        ini = (N/2) + 1;
    }
        
    for(let i = ini; i <= size; i++){
        const sample = data[i];
        vector.x1 += Number(sample.x1);
        vector.x2 += Number(sample.x2);
        vector.x3 += Number(sample.x3);
        vector.x4 += Number(sample.x4);
    }
    vector.x1 /= (N/2);
    vector.x2 /= (N/2);
    vector.x3 /= (N/2);
    vector.x4 /= (N/2);
    return vector;
};

function covarianceMatrix(m,classs){
    let v = [];
    let size;
    let ini;
    let matrix = math.matrix([[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]);
    if(classs == 1){
        size = N/2;
        ini = 1;
    }
    else{
        size = N;
        ini = (N/2) + 1;
    }    
    for(let i = ini; i <= size; i++){
        const sample = data[i];
        let auxDeduct = {x1:0, x2:0, x3:0, x4:0};
        auxDeduct.x1 = sample.x1 - m.x1;
        auxDeduct.x2 = sample.x2 - m.x2;
        auxDeduct.x3 = sample.x3 - m.x3;
        auxDeduct.x4 = sample.x4 - m.x4;
        let transp = math.transpose([[auxDeduct.x1],[auxDeduct.x2],[auxDeduct.x3],[auxDeduct.x4]]);
        let multiply = math.multiply([[auxDeduct.x1],[auxDeduct.x2],[auxDeduct.x3],[auxDeduct.x4]], transp);
        matrix = math.add(matrix,multiply);
    }
    return math.divide(matrix, ((N/2)-1));
};

function multiplyVector(auxDeduct){
    auxDeduct.x1 *= auxDeduct.x1;
    auxDeduct.x2 *= auxDeduct.x2;
    auxDeduct.x3 *= auxDeduct.x3;
    auxDeduct.x4 *= auxDeduct.x4;
    return auxDeduct;
};

function sum(vector, auxDeduct){
    vector.x1 += auxDeduct.x1;
    vector.x2 += auxDeduct.x2;
    vector.x3 += auxDeduct.x3;
    vector.x4 += auxDeduct.x4;
    return vector;
}