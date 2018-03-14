package dataStructures;

import java.awt.Color;

import javax.swing.JButton;

public class Cell {
	
	private JButton cell;
	private int x;
	private int y;
	private double g;
	private double h;
	private double f;
	private Cell father;
	private boolean isBarrier;
	
	public Cell() {
		this.cell = new JButton();
		this.cell.setBackground(new Color(255,255,204));
		isBarrier = false;
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.cell = new JButton();
		this.cell.setBackground(new Color(255,255,204));
		isBarrier = false;
	}
	
	public void mark(Color c) {
		this.cell.setBackground(c);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public JButton getCell() {
		return cell;
	}
	
	public void setBarrier(Color c) {
		this.cell.setBackground(c);
		isBarrier = true;
	}
	
	public double getG() {
		return g;
	}
	
	public void setG(double dist) {
		this.g = dist;
	}
	
	public double getH() {
		return h;
	}
	
	public void setH(double stim) {
		this.h = stim;
	}
	
	public double getF() {
		return f;
	}
	
	public void setF(double f) {
		this.f = f;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int compareTo(Cell c2) {
		if(this.h + this.g < c2.getH() + c2.getG()) {
			return -1;
		}
		return 1;
	}

	public void setDefaultColor(Color c) {
		this.cell.setBackground(c);
	}
	
	public void setFather(Cell father) {
		this.father = father;
	}
	
	public Cell getFather() {
		return this.father;
	}
	
	public boolean isBarrier() {
		return this.isBarrier;
	}

	public void setStart() {
		// TODO Auto-generated method stub
		this.cell.setBackground(new Color(0,0,153));
	}

	public void setGoal() {
		// TODO Auto-generated method stub
		this.cell.setBackground(new Color(128,255,0));
	}
}
