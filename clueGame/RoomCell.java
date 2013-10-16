package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT}
	int row, column;
	String cell;
	DoorDirection doorDirection;
	char roomInitial;
	public RoomCell(int row, int column, String cell){
		super(row, column, cell);
		if(isDoorway() == true){
			if(cell.toCharArray()[1] == 'U')
				doorDirection = DoorDirection.UP;
			else if(cell.toCharArray()[1] == 'D')
				doorDirection = DoorDirection.DOWN;
			else if(cell.toCharArray()[1] == 'L')
				doorDirection = DoorDirection.LEFT;
			else if(cell.toCharArray()[1] == 'R')
				doorDirection = DoorDirection.RIGHT;
		}
	}
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	
/*	public void draw(){
		
	}*/

}
