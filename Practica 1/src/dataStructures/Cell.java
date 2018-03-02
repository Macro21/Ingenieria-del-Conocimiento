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
	
	public Cell() {
		this.cell = new JButton();
		this.cell.setBackground(new Color(255,255,204));
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.cell = new JButton();
		this.cell.setBackground(new Color(255,255,204));
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
	
	public void isBarrier() {
		this.cell.setBackground(new java.awt.Color(0, 0, 0));
		this.cell.setFont(new java.awt.Font("Verdana", 0, 16)); 
		this.cell.setForeground(new java.awt.Color(255, 0, 0));
		this.cell.setText("X");
	}
	
	public void isNormal() {
		this.cell.setBackground(new Color(255,255,204));
	}
	
	public void setCell(JButton cell) {
		this.cell = cell;
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
		if(this.h < c2.getH()) {
			return -1;
		}
		else if (this.h == c2.getH()) {
			if(this.f < c2.getF()) {
				return -1;
			}
		}
		return 1;
	}
}
