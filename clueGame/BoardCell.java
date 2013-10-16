package clueGame;

public abstract class BoardCell {
	
	private int row, column;
	String cell;
	char roomInitial;
	public BoardCell(int row, int column, String cell){
		this.row = row;
		this.column = column;
		this.cell = cell;
		char[] cellInit = cell.toCharArray();
		roomInitial = cellInit[0];
	}
	
	
	public boolean isWalkway(){
		if(roomInitial == 'W')
			return true;
		else
			return false;
	}
	
	public boolean isRoom(){
		if(cell.length() == 1 && (roomInitial != 'W'))
			return true;
		else
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
