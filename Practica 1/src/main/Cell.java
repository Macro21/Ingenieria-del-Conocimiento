package main;

import java.awt.Color;

import javax.swing.JButton;

public class Cell {
	
	private String name;
	private JButton cell;
	private int x;
	private int y;
	private String relPos;
	private double g;
	private double h;
	private double f;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		cell = new JButton();
		cell.setBackground(new Color(255,255,204));
	}
	
	public void mark(Color c) {
		cell.setBackground(c);
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
		cell.setBackground(new java.awt.Color(0, 0, 0));
		cell.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        cell.setForeground(new java.awt.Color(255, 0, 0));
        cell.setText("X");
	}
	
	public String getName() {
		return name;
	}
	
	public void setCell(JButton cell) {
		this.cell = cell;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRelPos() {
		return relPos;
	}
	
	public void setRelPos(String relPos) {
		this.relPos = relPos;
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
}
