package dataStructures;

public class Data {

	private int index;
	private boolean found;
	private Cell cell;
	
	public Data(boolean found, Cell cell, int index) {
		this.found = found;
		this.cell = cell;
		this.index = index;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public boolean isFound() {
		return found;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
