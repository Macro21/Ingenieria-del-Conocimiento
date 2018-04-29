"use strict";

let data;
let attributes = ['x1','x2','x3','x4'];
let centroidMatrix = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];
let uMatrix = [[],[-1],[-1]];
let b = 2;
let epsilon = 0.01;
let OldCentroidMatrix = [[],[4.6,3.0,4.0,0.0],[6.8,3.4,4.6,0.7]];

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
    kmeans();
   
});

/**
 * This function parse the data file. 
 * Global data change and take the plant format.
 */
function formatInputData(){
    let plant = {
        x1: -1,
        x2: -1,
        x3: -1,
        x4: -1,
        type: ""
    }
    let plantGroup = [{x1: -1, x2: -1, x3: -1, x4: -1, type: ""}];
    
    //p format is "[5.7,2.8,4.1,1.3,Iris-versicolor]"
    for(let p of data){
        let aux = p.split(',');
        plant.x1 = aux[0];
        plant.x2 = aux[1];
        plant.x3 = aux[2];
        plant.x4 = aux[3];
        plant.type = aux[4];
        plantGroup.push(plant);
        plant = new Object();
    }
    return plantGroup;
};


/**
 *  retrun dij
 * @param {*} x // a X object, [x1,x2,x3,x4]
 * @param {*} j // the centroid. 1 --> v1, 2 --> v2
 */
function calculateD(i, j, sample){
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


function calculateUmatrix(){
    let Pvixj = 0;
    for(let i = 1; i<=2; i++)
        for (let j = 1; j < data.length; j++) {
            const sample = data[j];
            let dij =  calculateD(i,j,sample);
            let dij2 = 0;
            if(i == 1)
                dij2 = calculateD(i+1,j,sample);
            else
                dij2 = calculateD(i-1,j,sample);
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
    let t = 0;
    let stop = false;
    while(!stop && t < 10){
        calculateUmatrix();
        recalculateCentroid();
        let v1euc = centroidMatrix[1][0] - OldCentroidMatrix[1][0] + centroidMatrix[1][1] - OldCentroidMatrix[1][1] +
        centroidMatrix[1][2] - OldCentroidMatrix[1][2] + centroidMatrix[1][3] - OldCentroidMatrix[1][3];

        let v2euc = centroidMatrix[2][0] - OldCentroidMatrix[2][0] + centroidMatrix[2][1] - OldCentroidMatrix[2][1] +
        centroidMatrix[2][2] - OldCentroidMatrix[2][2] + centroidMatrix[2][3] - OldCentroidMatrix[2][3];

        if( v1euc < epsilon || v2euc < epsilon)
            stop = true;
        OldCentroidMatrix = centroidMatrix.slice(); //Array CLONE!!! Easy
        uMatrix = [[],[-1],[-1]];
        t++;
    }
}