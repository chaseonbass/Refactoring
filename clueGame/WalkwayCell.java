package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell{
	public WalkwayCell(int row, int column, String cell) {
		super(row, column, cell);
		// TODO Auto-generated constructor stub
	}

	public boolean isWalkway(){
		return true;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, DIM, DIM);
		g.setColor(Color.black);
		g.drawRect(x, y, DIM, DIM);
	}
}
