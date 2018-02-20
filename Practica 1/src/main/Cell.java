package main;

import java.awt.Color;

import javax.swing.JButton;

public class Cell {

	private JButton cell;
	private int x;
	private int y;
	
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		cell = new JButton();
		cell.setBackground(new Color(255,255,204));
	}
	
	public void mark() {
		cell.setBackground(new Color(244,155,57));
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
	
}
