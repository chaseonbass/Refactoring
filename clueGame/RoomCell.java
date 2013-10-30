package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE}
	int row, column;
	String cell;
	DoorDirection doorDirection;
	char roomInitial;
	boolean roomNameSpot = false;
	
	public RoomCell(int row, int column, String cell){
		super(row, column, cell);
		if(isDoorway()){
			if(cell.toCharArray()[1] == 'U')
				doorDirection = DoorDirection.UP;
			else if(cell.toCharArray()[1] == 'D')
				doorDirection = DoorDirection.DOWN;
			else if(cell.toCharArray()[1] == 'L')
				doorDirection = DoorDirection.LEFT;
			else if(cell.toCharArray()[1] == 'R')
				doorDirection = DoorDirection.RIGHT;
			else
				doorDirection = DoorDirection.NONE;
		}
		if (cell.length() > 1)
			if (cell.toCharArray()[1] == 'N')
				roomNameSpot = true;
	}
	
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	@Override
	public void drawRoom(Graphics g, String string) {
		if(roomNameSpot) {
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString(string,x,y+DIM);
		}
	}

	@Override
	public void draw(Graphics g) {

		if (isRoom()) {
			g.setColor(Color.gray);
			g.fillRect(x, y, DIM, DIM);

			// Draw the door
			g.setColor(Color.blue);
			if (doorDirection != null) {
				if (doorDirection.equals(DoorDirection.UP)) {
					g.drawLine(x,y,x+DIM,y);
					g.drawLine(x,y+1,x+DIM,y+1);
					g.drawLine(x,y+2,x+DIM,y+2);
				} else if (doorDirection.equals(DoorDirection.DOWN)) {
					g.drawLine(x,y+DIM,x+DIM,y+DIM);
					g.drawLine(x,y+DIM-1,x+DIM,y+DIM-1);
					g.drawLine(x,y+DIM-2,x+DIM,y+DIM-2);
				} else if (doorDirection.equals(DoorDirection.RIGHT)) {
					g.drawLine(x+DIM,y,x+DIM,y+DIM);
					g.drawLine(x+DIM-1,y,x+DIM-1,y+DIM);
					g.drawLine(x+DIM-2,y,x+DIM-2,y+DIM);
				} else if (doorDirection.equals(DoorDirection.LEFT)) {
					g.drawLine(x,y,x,y+DIM);
					g.drawLine(x+1,y,x+1,y+DIM);
					g.drawLine(x+2,y,x+2,y+DIM);
				}
			}
		} else if (isWalkway()) {
			g.setColor(Color.yellow);
			g.fillRect(x, y, DIM, DIM);
			g.setColor(Color.black);
			g.drawRect(x, y, DIM, DIM);
		}
	}
	
}
