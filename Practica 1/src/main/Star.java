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
	
	private ArrayList<Node> open;
	//private ArrayList<int> cerrada;

	public Star() {
		this.open = new ArrayList<Node>();
		//this.cerrada = new ArrayList<int>();
	}
	
	public void add(Node n) {
		this.open.add(n);
	}
	public void updateValue() {
		
	}
	public void read() {
		for(int i = 0; i<this.open.size(); i++) {
			System.out.println(this.open.get(i).getName());
		}
	}
	
}
