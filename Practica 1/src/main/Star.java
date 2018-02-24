package main;

import java.util.ArrayList;

public class Star {

	//g es la distancia real acumulada
	//h es la distancia estumada con la formula de abajo
	//g = 1 si te mueves en la matriz arriba, abajo iz dr. raiz de dos si te mueves en diagonal y se suman
	//Es decir si vas de (1,1) a (1,2)
	
	/*
	 * Distancia estimada es: d[(x1,y1),(x2,y2) = raiz de (x1-x2)^2 + (y1,y2)^2)]
	 * */
	
	private ArrayList<Cell> open;
	private ArrayList<Cell> close;

	public Star() {
		this.open = new ArrayList<Cell>();
		this.close = new ArrayList<Cell>();
	}
	
	private void add(Cell c) {
		
		if(this.open.size() == 0) {
			this.open.add(c);
		}
		else if (this.open.size() == 1) {
			if(this.open.get(0).getF() < c.getF()) {
				this.open.add(c);
			}
			else {
				this.open.add(this.open.get(0));
				this.open.set(0, c);
			}
		}
		else {
			for(int i = 0; i< this.open.size(); i++) {
				if(this.open.get(i).getF() > c.getF() ) {
					move(i);
					this.open.set(i, c);
				}
			}
		}
	}
	
	private void move(int i) {
		for(int j = this.open.size(); j>i;j--) {
			this.open.add(this.open.get(j));
		}
	}
	
	public void play(Cell matrix[][], int rows, int cols, int xs, int ys) {
		boolean fail = false;
		//Celda de inicio
		Cell c = matrix[xs][ys];
		if(c.getCell().getText().equals("")) {// Si no es un obstaculo
			c.setName("r");
			add(c);
		}
		//3 3 es el fin
		while(!fail) {
			//Si hay en abierta hay elems meto el primero en cerrada y lo borro
			if(this.open.size() > 0 ) {
				Cell a = this.open.get(0);
				a.setName("m");		
				this.close.add(a);
				this.open.remove(0);
			}
			else {
				fail = true;
			}
			//Si en cerrada es meta se acaba el proceso
			if(this.close.get(this.close.size()-1).getX() == 3 && this.close.get(this.close.size()-1).getY() == 3) {
				fail = true;
			}
			//Si no es meta en cerrada se meten sus hijos
			else {
				Cell a = this.close.get(this.close.size()-1);
				getChildrens(matrix,rows,cols,a.getX(), a.getY());
			}
		}
		System.out.println(this.open.get(0).getF());
	}
	
	//Mete en abierta todos los nodos hijos de la casilla x y
	private void getChildrens(Cell matrix[][], int x, int y, int N, int M) {
		Cell ini = matrix[x][y];
		double sTwo = Math.sqrt(2);
		//up
		if(x+1 < N && matrix[x+1][y].getCell().equals("")) {
			Cell c = matrix[x+1][y];
			c.setRelPos("up");
			c.setG(1);
			double a = (x-(x+1));
			double stim = Math.sqrt(Math.pow(a, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//up right
		if(x+1 < N && y+1 < M && matrix[x+1][y+1].getCell().equals("")) {
			Cell c = matrix[x+1][y+1];
			c.setRelPos("upR");
			c.setG(sTwo);
			double a = (x - (x+1));
			double b = (y - (y+1));
			double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//up left
		if(x+1 < N && y-1 >= 0 && matrix[x+1][y-1].getCell().equals("")) {
			Cell c = matrix[x+1][y-1];
			c.setRelPos("upL");
			c.setG(sTwo);
			double a = (x - (x+1));
			double b = (y - (y-1));
			double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//right
		if(y+1 < M && matrix[x][y+1].getCell().equals("")) {
			Cell c = matrix[x][y+1];
			c.setRelPos("r");
			c.setG(1);
			double b = (y - (y+1));
			double stim = Math.sqrt(Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//left
		if(y-1 >=0 && matrix[x][y-1].getCell().equals("")) {
			Cell c = matrix[x][y-1];
			c.setRelPos("l");
			c.setG(1);
			double b = (y - (y+1));
			double stim = Math.sqrt(Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//down
		if(x-1 >=0 && matrix[x-1][y].getCell().equals("")) {
			Cell c = matrix[x-1][y];
			c.setRelPos("d");
			c.setH(1);
			double a = (x - (x-1));
			double stim = Math.sqrt(Math.pow(a, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//down right
		if(x-1 >=0 && y+1 < M && matrix[x-1][y+1].getCell().equals("")) {
			Cell c = matrix[x-1][y+1];
			c.setRelPos("dr");
			c.setG(sTwo);
			double a = (x - (x-1));
			double b = (y - (y+1));
			double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
		//down left
		if(x-1>=0 && y-1>=0 && matrix[x-1][y-1].getCell().equals("") ) {
			Cell c = matrix[x-1][y-1];
			c.setRelPos("dl");
			c.setG(sTwo);
			double a = (x - (x-1));
			double b = (y - (y-1));
			double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			c.setH(stim);
			c.setF(c.getG() + c.getH());
			double dist = ini.getF() + c.getF();
			c.setF(dist);
			add(c);
		}
	}
	
}
