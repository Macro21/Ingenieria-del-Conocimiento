package main;

public class Main {

	public static void main(String[] args) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		Board b = new Board();
		b.markCell(3,3);
		b.markCell(5, 5);
	
		Star e = new Star();
		e.play(b.getMatrix(), b.getRows(),b.getCols(), b.getStartX(), b.getStartY());
	}
}