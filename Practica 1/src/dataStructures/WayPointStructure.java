package dataStructures;

import java.util.ArrayList;

public class WayPointStructure {
	
	private ArrayList<Cell> fragment;
	private int startX;
	private int startY;
	private int goalX;
	private int goalY;
	
	public WayPointStructure(ArrayList<Cell> f, int stX, int stY, int goX, int goY) {
		fragment = f;
		startX = stX;
		startY = stY;
		goalX = goX;
		goalY = goY;
		
	}
	
	public ArrayList<Cell> getFragment() {
		return fragment;
	}
	
	public int getGoalX() {
		return goalX;
	}
	public int getGoalY() {
		return goalY;
	}
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
}
