package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection{UP, DOWN, LEFT, RIGHT, NONE}
	int row, column;
	DoorDirection doorDirection;
	char roomInitial;
	public RoomCell(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public boolean isRoom(){
		return true;
	}

	public Object getDoorDirection() {
		return null;
	}

	public Object getInitial() {
		// TODO Auto-generated method stub
		return null;
	}
	
/*	public void draw(){
		
	}*/

}
