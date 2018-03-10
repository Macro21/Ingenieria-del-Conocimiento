package dataStructures;

public class Data {

	private boolean found;
	private Cell cell;
	
	public Data(boolean found, Cell cell) {
		this.found = found;
		this.cell = cell;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public boolean isFound() {
		return found;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
