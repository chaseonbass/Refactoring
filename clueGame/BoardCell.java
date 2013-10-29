package clueGame;

import java.awt.Graphics;

public abstract class BoardCell {
	
	protected int row, column, x, y;
	protected static int DIM = 100;
	String cell;
	char roomInitial;
	public BoardCell(int row, int column, String cell){
		this.row = row;
		this.column = column;
		this.cell = cell;
		char[] cellInit = cell.toCharArray();
		roomInitial = cellInit[0];
		
		// For drawing
		x = DIM*column;
		y = DIM*row;
	}
	
	public abstract void draw(Graphics g);
	
	public boolean isWalkway(){
		if(roomInitial == 'W')
			return true;
		return false;
	}
	
	public boolean isRoom(){
		if((cell.length() == 1 && (roomInitial != 'W')) || cell.length() == 2)
			return true;
		return false;
	}
	
	public boolean isDoorway(){
		if(cell.length() == 2){
			if(cell.charAt(1) == 'R' || cell.charAt(1) == 'U' || cell.charAt(1) == 'D' || cell.charAt(1) == 'L')
				return true;
		}
		return false;
	}
	public char getInitial(){
		return roomInitial;
	}

}
