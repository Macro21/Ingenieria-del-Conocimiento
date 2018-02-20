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
        cell.setText("XXXXXXX");
	}
}
