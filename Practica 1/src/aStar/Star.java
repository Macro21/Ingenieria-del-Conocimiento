package aStar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private Board board;
	private int startX;
	private int startY;
	private int goalX;
	private int goalY;
	private Cell matrix[][];
	private int N;
	private int M;
	
	public Star(Board board) {
		this.open = new ArrayList<Cell>();
		this.close = new ArrayList<Cell>();
		this.board = board;
	}
	
	private void add(Cell c) {
		Data data = existsCell(this.open, c.getX(), c.getY());
		if(!data.isFound()) {
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

	public void play() {
		boolean fail = false;
		//Celda de inicio
		Cell c = matrix[startX][startY];
		if(c.getCell().getText().equals("")) {// Si no es un obstaculo
			double a = (startX-goalX);
			double b = (startY-goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b,2)));
			c.setH(stim);
			c.setG(0);
			c.setF(stim);
			c.setFather(null);
			add(c);
		}
		while(!fail) {
			//Si hay en abierta hay elems meto el primero en cerrada y lo borro
			if(this.open.size() > 0 ) {
				Cell a = this.open.get(0);
				this.close.add(a);
				this.open.remove(0);
			}
			else {
				fail = true;
			}
			//Si en cerrada es meta se acaba el proceso
			if(this.close.get(this.close.size()-1).getX() == goalX && this.close.get(this.close.size()-1).getY() == goalY) {
				fail = true;
			}
			//Si no es meta en cerrada se meten sus hijos
			else {
				Cell a = this.close.get(this.close.size()-1);
				getChildrens(a.getX(), a.getY());	
			}
		}
	}

	//Mete en abierta todos los nodos hijos de la casilla x y
	private void getChildrens(int x, int y) {
		Cell m = matrix[x][y];
		double sTwo = Math.sqrt(2);
		double mg = m.getG();

		//down
		if(x+1 <= N && !matrix[x+1][y].isBarrier()) {
			Cell c = new Cell();
			c.setX(x+1);
			c.setY(y);
			c.setG(mg + 1);
			
			double a = ((x+1)-goalX);
			double b = (y-goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b,2)));
			
			c.setH(stim);//stim es la distancia estimada desde este hijo al objetivo
			c.setF(c.getG() + stim);	
			
			//si no tenia padre, le pongo padre
			if(matrix[x+1][y].getFather() == null) {
				c.setFather(m);
			}
			Data d = existsCell(this.open, x+1, y);
			Data d2 = existsCell(this.close, x+1, y);

			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x+1][y] = c;
			}
			
			else if (d.isFound() && d.getCell().getG() >= c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + 1);
			}
		}
		//down right
		if(x+1 <= N && y+1 <= M && !matrix[x+1][y+1].isBarrier()) {
			
			Cell c = new Cell();
			c.setX(x+1);
			c.setY(y+1);
			c.setG(mg + sTwo);
			
			double a = ((x+1) - goalX);
			double b = ((y+1) - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x+1, y+1);
			Data d2 = existsCell(this.close, x+1, y+1);
			
			if(matrix[x+1][y+1].getFather() == null) {
				c.setFather(m);				
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x+1][y+1] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + sTwo);
			}
		}
		//down left
		if(x+1 <= N && y-1 > 0 && !matrix[x+1][y-1].isBarrier()) {	
	
			Cell c = new Cell();
			c.setX(x+1);
			c.setY(y-1);
			c.setG(mg + sTwo);
			
			double a = ((x+1) - goalX);
			double b = ((y-1) - goalY);
			double stim = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x+1, y-1);
			Data d2 = existsCell(this.close, x+1, y-1);
			
			if(matrix[x+1][y-1].getFather() == null) {
				c.setFather(m);
			
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x+1][y-1] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + sTwo);
			}
	
		}
		//right
		if(y+1 <= M && !matrix[x][y+1].isBarrier()) {
			Cell c = new Cell();
			c.setX(x);
			c.setY(y+1);
			c.setG(mg + 1);
			
			double a = (x - goalX);
			double b = ((y+1) - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x, y+1);
			Data d2 = existsCell(this.close, x, y+1);
			
			if(matrix[x][y+1].getFather() == null) {
				c.setFather(m);
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x][y+1] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + 1);
			}	
			
		}
		//left
		if(y-1 >0 && !matrix[x][y-1].isBarrier()) {
		
			Cell c = new Cell();
			c.setX(x);
			c.setY(y-1);
			c.setG(mg +1);
			
			double a = (x - goalX);
			double b = ((y-1) - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x, y-1);
			Data d2 = existsCell(this.close, x, y-1);
			
			if(matrix[x][y-1].getFather() == null) {
				c.setFather(m);
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x][y-1] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + 1);
			}
		}
		//up
		if(x-1 >0 && !matrix[x-1][y].isBarrier()) {
			Cell c = new Cell();
			c.setX(x-1);
			c.setY(y);
			c.setG(mg + 1);
			
			double a = ((x-1) - goalX);
			double b = (y - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			c.setH(stim);
			c.setF(c.getG() + stim);
			Data d = existsCell(this.open, x-1, y);
			Data d2 = existsCell(this.close, x-1, y);
			
			if(matrix[x-1][y].getFather() == null) {
				c.setFather(m);
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x-1][y] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + 1);
			}
		}
		//up right
		if(x-1 >0 && y+1 <= M && !matrix[x-1][y+1].isBarrier()) {

			Cell c = new Cell();
			c.setX(x-1);
			c.setY(y+1);
			c.setG(mg + sTwo);
			
			double a = ((x-1) - goalX);
			double b = ((y+1) - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x-1, y+1);
			Data d2 = existsCell(this.close, x-1, y+1);
			
			if(matrix[x-1][y+1].getFather() == null) {
				c.setFather(m);
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x-1][y+1] = c;
			}
			
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + sTwo);
			}
		}
		//up left
		if(x-1>0 && y-1>0 && !matrix[x-1][y-1].isBarrier()) {		
			Cell c = new Cell();
			c.setX(x-1);
			c.setY(y-1);
			c.setG(mg + sTwo);
			
			double a = ((x-1) - goalX);
			double b = ((y-1) - goalY);
			double stim = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
			c.setH(stim);
			c.setF(c.getG() + stim);
			
			Data d = existsCell(this.open, x-1, y-1);
			Data d2 = existsCell(this.close, x-1, y-1);
			
			if(matrix[x-1][y-1].getFather() == null) {
				c.setFather(m);
			}
			
			if(!d.isFound() && !d2.isFound()) {
				add(c);
				matrix[x-1][y-1] = c;
			}
			else if ( d.isFound() && d.getCell().getG() > c.getG()) {
				d.getCell().setFather(m);
				d.getCell().setG(m.getG() + sTwo);
			}	
		}
	}
	
	private Data existsCell(ArrayList<Cell> list, int x, int y){
		boolean ok = false;
		Cell found = null;
		int i = 0;
		while(i<list.size() && !ok) {
			Cell aux = list.get(i);
			if(aux.getX() == x && aux.getY()==y){
				ok=true;
				found = aux;
			}
			i++;
		}
		return new Data(ok,found);
	}
	
	private void iniStar() throws Exception {
		try {
			startX = Integer.parseInt(board.getStartXtxt().getText());
			startY = Integer.parseInt(board.getStartYtxt().getText());
			goalX = Integer.parseInt(board.getxGoal().getText());
			goalY = Integer.parseInt(board.getyGoal().getText());
			board.setXs(startX);
			board.setYs(startY);
			board.setXg(goalX);
			board.setYg(goalY);
			N = board.getN_ROWS();
			M = board.getN_COLS();
			
			if(startX > N || startY > M || goalX > N || goalY > M)
				throw new Exception("Error, fuera de rango!");
			
			matrix = board.getMatrix();
			if(!matrix[startX][startY].getCell().getText().equals(""))
				throw new Exception("Error, inicio en obstaculo!");
			if(!matrix[goalX][goalY].getCell().getText().equals(""))
				throw new Exception("Error, fin en obstaculo!");
			board.repaintDefaultMatrix(matrix);
			this.close.clear();
			this.open.clear();
			
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException();
		}
	}
	
	public void ready() {
        this.board.getStart().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String err = "Los datos introducidos son incorrectos!";
				try{
					iniStar();
					play();
					ArrayList<Cell> path = new ArrayList<Cell>();
					getPath(close.get(close.size()-1), path);
					board.pathPaint(path);
					//board.pathPaint(posibleCamino);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(new JFrame(),err,"Error, formato incorrecto",JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(new JFrame(),err,excep.getMessage(),JOptionPane.ERROR_MESSAGE);
				}
			}

			
        });
	}
	
	private void getPath(Cell c, ArrayList<Cell> path) {
		if(c != null) {
			path.add(c);
			getPath(c.getFather(), path);
		}
	}
	
	public void ready2() {
		String err = "Los datos introducidos son incorrectos!";
		try{
			play();
			ArrayList<Cell> path = new ArrayList<Cell>();
			getPath(close.get(close.size()-1), path);
			board.pathPaint(path);
			//board.pathPaint(posibleCamino);
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(new JFrame(),err,"Error, formato incorrecto",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception excep) {
			JOptionPane.showMessageDialog(new JFrame(),err,excep.getMessage(),JOptionPane.ERROR_MESSAGE);
		}
	}
}
