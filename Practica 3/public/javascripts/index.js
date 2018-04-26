"use strict";

let data;
let attributes = ['sepalLength','sepalWidth','petalLength','petalWidth'];
let centroidMatrix = [[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];
let uMatrix = [[],[]];
let b = 2;
let epsilon = 0.01;

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
    data = formatInputData();
    calculateUmatrix(calculateD());
    console.log(uMatrix);
});

/**
 * This function parse the data file. 
 * Global data change and take the plant format.
 */
function formatInputData(){
    let plantGroup = [];
    let plant = {
        sepalLength: -1,
        sepalWidth: -1,
        petalLength: -1,
        petalWidth: -1,
        type: ""
    }
    plantGroup.push(plant);
    //p format is "[5.7,2.8,4.1,1.3,Iris-versicolor]"
    for(let p of data){
        let aux = p.split(',');
        plant.sepalLength = aux[0];
        plant.sepalWidth = aux[1];
        plant.petalLength = aux[2];
        plant.petalWidth = aux[3];
        plant.type = aux[4];
        plantGroup.push(plant);
        plant = new Object();
    }
    return plantGroup;
};

/**
 * This function return a matrix with the next format:
 * a matrix with 2 rows --> row 1 have d11,d21,....,dn1 --> and d11 = ||X1 - V1 ||^2, row 2 idem
 * 2 values with a total sum for each row
 */
function calculateD(){
    let dMatrix = { row1 : [], row2 : []};
    let totalCentroid1 = 0;
    let totalCentroid2 = 0;    
    for(let i = 1; i < data.length; i++){
        let sol = calculate(data[i],0);     
        dMatrix.row1.push(sol);
        totalCentroid1 += sol;
    }

    for(let i = 1; i < data.length; i++){
        let sol = calculate(data[i],1);     
        dMatrix.row2.push(sol);
        totalCentroid2 += sol;
    }

    return {dMatrix: dMatrix, totalCentroid1: totalCentroid1, totalCentroid2: totalCentroid2};
};

/**
 * 
 * @param {*} x // a X object, [x1,x2,x3,x4]
 * @param {*} j // the centroid. 1 --> v1, 2 --> v2
 */
function calculate(x,j){
    let sol = 0;
    let base = x.sepalLength - centroidMatrix[j][0];
    sol += Math.pow(base, 2);
    base = x.sepalWidth - centroidMatrix[j][1];
    sol += Math.pow(base, 2);
    base = x.petalLength - centroidMatrix[j][2];
    sol += Math.pow(base, 2);
    base = x.petalWidth - centroidMatrix[j][3];
    sol += Math.pow(base, 2);
    return sol;
};

/**
 * This function calculate uMatrix, needed for recalculate centroidMatrix
 * @param {*} infoCalculateD //data with a matrix and 2 calues. Its te return of calculateD() function
 */
function calculateUmatrix(infoCalculateD){
    let dMatrix = infoCalculateD.dMatrix;
    let totalCentroid1 = infoCalculateD.totalCentroid1;
    let totalCentroid2 = infoCalculateD.totalCentroid2;
    
    for(let j = 0; j < 2; j++)
        for(let i = 0; i < dMatrix.row1.length; i++){
            let sol = 1/dMatrix.row1[i];
            uMatrix[j].push(sol/totalCentroid1);
        }
    
};

function recalculateCentroidMatrix(){
    
};
