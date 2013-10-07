package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection{UP, DOWN, LEFT, RIGHT, NONE}
	int row, column;
	String cell;
	DoorDirection doorDirection;
	char roomInitial;
	public RoomCell(int row, int column, String cell){
		super(row, column, cell);
	}
	
/*	public void draw(){
		
	}*/

}
