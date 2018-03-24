package aStar;

import java.util.ArrayList;
import java.util.Comparator;

import dataStructures.Cell;
import dataStructures.Data;

public class Star {

    //g es la distancia real acumulada
    //h es la distancia estumada con la formula de abajo
    //g = 1 si te mueves en la matriz arriba, abajo iz dr. raiz de dos si te mueves en diagonal y se suman
    //Es decir si vas de (1,1) a (1,2)
    /*
	 * Distancia estimada es: d[(x1,y1),(x2,y2) = raiz de (x1-x2)^2 + (y1,y2)^2)] donde x1,y1 es la posicion en la que estoy y x2,y2 es siempre la meta
	 * */
    private ArrayList<Cell> open;
    private ArrayList<Cell> close;
    //private Board board;

    private Cell matrix[][];
    private int N;
    private int M;

    public Star(Cell[][] matrix) {
        this.open = new ArrayList<Cell>();
        this.close = new ArrayList<Cell>();
        this.matrix = matrix;
        N = matrix.length;
        M = matrix[0].length;
    }

    private void add(Cell c) {
        Data data = existsCell(this.open, c.getX(), c.getY());
        if (!data.isFound()) {
            this.open.add(c);
        }
        this.open.sort(new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                // TODO Auto-generated method stub
                return c1.compareTo(c2);
            }
        });
    }

    public Cell[][] play(int startX, int startY, int goalX, int goalY) throws Exception {
        boolean fail = false;
        //Celda de inicio
        Cell c = matrix[startX][startY];
        Cell c2 = matrix[goalX][goalY];
        if(c.isBarrier()){
            throw new Exception("Inicio en obstáculo!");
        }
        else if (c2.isBarrier()){
            throw new Exception("Fin en obstáculo!");
        }
        else{// Si no es un obstaculo
            double a = (startX - goalX);
            double b = (startY - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
            c.setH(stim);
            c.setG(0);
            c.setFather(null);
            add(c);
        }
        while (!fail) {
            //Si hay en abierta hay elems meto el primero en cerrada y lo borro
            if (this.open.size() > 0) {
                Cell a = this.open.get(0);
                this.close.add(a);
                this.open.remove(0);
            } else {
                throw new Exception("Error, destino inalcanzable!");
            }
            //Si en cerrada es meta se acaba el proceso
            if (this.close.get(this.close.size() - 1).getX() == goalX && this.close.get(this.close.size() - 1).getY() == goalY) {
                fail = true;
            } //Si no es meta en cerrada se meten sus hijos
            else {
                Cell a = this.close.get(this.close.size() - 1);
                getChildrens(a.getX(), a.getY(), goalX, goalY, startX, startY);
            }
        }
        return matrix;
    }

    //Mete en abierta todos los nodos hijos de la casilla x y
    private void getChildrens(int x, int y, int goalX, int goalY, int startX, int startY) {
        Cell m = matrix[x][y];
        double sTwo = Math.sqrt(2);
        double mg = m.getG();

        //down
        if (x + 1 < N && !matrix[x + 1][y].isBarrier()) {
            Cell c = new Cell();
            c.setX(x + 1);
            c.setY(y);
            String cost = matrix[x + 1][y].getCost();
            putCost(c, cost);
            c.setG(mg + 1);

            double a = ((x + 1) - goalX);
            double b = (y - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

            c.setH(stim);//stim es la distancia estimada desde este hijo al objetivo
            c.setFather(matrix[x + 1][y].getFather());
            //si no tenia padre, le pongo padre
            if (c.getFather() == null) {
                c.setFather(m);
            }
            Data d = existsCell(this.open, x + 1, y);
            Data d2 = existsCell(this.close, x + 1, y);

            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x + 1][y] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + 1);
            }
        }
        //down right
        if (x + 1 < N && y + 1 < M && !matrix[x + 1][y + 1].isBarrier()) {

            Cell c = new Cell();
            c.setX(x + 1);
            c.setY(y + 1);
            String cost = matrix[x + 1][y + 1].getCost();
            putCost(c, cost);
            c.setG(mg + sTwo);
            c.setFather(matrix[x + 1][y + 1].getFather());

            double a = ((x + 1) - goalX);
            double b = ((y + 1) - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

            c.setH(stim);

            Data d = existsCell(this.open, x + 1, y + 1);
            Data d2 = existsCell(this.close, x + 1, y + 1);

            if (c.getFather() == null) {
                c.setFather(m);
            }
            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x + 1][y + 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + sTwo);
            }
        }
        //down left
        if (x + 1 < N && y - 1 > 0 && !matrix[x + 1][y - 1].isBarrier()) {

            Cell c = new Cell();
            c.setX(x + 1);
            c.setY(y - 1);
            String cost = matrix[x + 1][y - 1].getCost();
            putCost(c, cost);
            c.setG(mg + sTwo);

            double a = ((x + 1) - goalX);
            double b = ((y - 1) - goalY);
            double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

            c.setH(stim);

            Data d = existsCell(this.open, x + 1, y - 1);
            Data d2 = existsCell(this.close, x + 1, y - 1);

            if (matrix[x + 1][y - 1].getFather() == null) {
                c.setFather(m);
            }
            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x + 1][y - 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + sTwo);
            }

        }
        //right
        if (y + 1 < M && !matrix[x][y + 1].isBarrier()) {
            Cell c = new Cell();
            c.setX(x);
            c.setY(y + 1);
            String cost = matrix[x][y + 1].getCost();
            putCost(c, cost);
            c.setG(mg + 1);
            c.setFather(matrix[x][y + 1].getFather());

            double a = (x - goalX);
            double b = ((y + 1) - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

            c.setH(stim);

            Data d = existsCell(this.open, x, y + 1);
            Data d2 = existsCell(this.close, x, y + 1);

            if (c.getFather() == null) {
                c.setFather(m);
            }
            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x][y + 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + 1);
            }

        }
        //left
        if (y - 1 > 0 && !matrix[x][y - 1].isBarrier()) {

            Cell c = new Cell();
            c.setX(x);
            c.setY(y - 1);
            String cost = matrix[x][y - 1].getCost();
            putCost(c, cost);
            c.setG(mg + 1);

            double a = (x - goalX);
            double b = ((y - 1) - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

            c.setH(stim);

            Data d = existsCell(this.open, x, y - 1);
            Data d2 = existsCell(this.close, x, y - 1);

            if (matrix[x][y - 1].getFather() == null) {
                c.setFather(m);
            }
            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x][y - 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + 1);
            }
        }
        //up
        if (x - 1 > 0 && !matrix[x - 1][y].isBarrier()) {
            Cell c = new Cell();
            c.setX(x - 1);
            c.setY(y);
            String cost = matrix[x - 1][y].getCost();
            putCost(c, cost);
            c.setG(mg + 1);

            double a = ((x - 1) - goalX);
            double b = (y - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
            c.setH(stim);

            Data d = existsCell(this.open, x - 1, y);
            Data d2 = existsCell(this.close, x - 1, y);

            if (matrix[x - 1][y].getFather() == null) {
                c.setFather(m);
            }

            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x - 1][y] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + 1);
            }
        }
        //up right
        if (x - 1 > 0 && y + 1 < M && !matrix[x - 1][y + 1].isBarrier()) {

            Cell c = new Cell();
            c.setX(x - 1);
            c.setY(y + 1);
            String cost = matrix[x - 1][y + 1].getCost();
            putCost(c, cost);
            c.setG(mg + sTwo);

            double a = ((x - 1) - goalX);
            double b = ((y + 1) - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
            c.setH(stim);

            Data d = existsCell(this.open, x - 1, y + 1);
            Data d2 = existsCell(this.close, x - 1, y + 1);

            if (matrix[x - 1][y + 1].getFather() == null) {
                c.setFather(m);
            }

            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x - 1][y + 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + sTwo);
            }
        }
        //up left
        if (x - 1 > 0 && y - 1 > 0 && !matrix[x - 1][y - 1].isBarrier()) {
            Cell c = new Cell();
            c.setX(x - 1);
            c.setY(y - 1);
            String cost = matrix[x - 1][y - 1].getCost();
            putCost(c, cost);
            c.setG(mg + sTwo);

            double a = ((x - 1) - goalX);
            double b = ((y - 1) - goalY);
            double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
            c.setH(stim);

            Data d = existsCell(this.open, x - 1, y - 1);
            Data d2 = existsCell(this.close, x - 1, y - 1);

            if (matrix[x - 1][y - 1].getFather() == null) {
                c.setFather(m);
            }

            if (!d.isFound() && !d2.isFound()) {
                add(c);
                matrix[x - 1][y - 1] = c;
            } else if (d.isFound() && d.getCell().getG() > c.getG()) {
                d.getCell().setFather(m);
                d.getCell().setG(m.getG() + sTwo);
            }
        }
    }

    private Data existsCell(ArrayList<Cell> list, int x, int y) {
        boolean ok = false;
        Cell found = null;
        int i = 0;
        while (i < list.size() && !ok) {
            Cell aux = list.get(i);
            if (aux.getX() == x && aux.getY() == y) {
                ok = true;
                found = aux;
            }
            i++;
        }
        return new Data(ok, found);
    }

    public ArrayList<Cell> getPath() {
        ArrayList<Cell> path = new ArrayList<Cell>();
        getPath(this.close.get(this.close.size() - 1), path);
        return path;
    }

    private void getPath(Cell c, ArrayList<Cell> path) {
        if (c != null) {
            path.add(c);
            getPath(c.getFather(), path);
        }
    }

    private void putCost(Cell c, String cost) {
        switch (cost) {
            case "cost1":
                c.setCost1();
                break;
            case "cost2":
                c.setCost2();
                break;
            case "cost3":
                c.setCost3();
                break;
            default:
                break;
        }
    }

}
