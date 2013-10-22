package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	
	public ComputerPlayer() {
		// for testing
	}
	
	public ComputerPlayer(char lrv) {
		lastRoomVisited = lrv;
	}
	
	public ComputerPlayer(String n, String c, int s) {
		super(n, c, s);
	}
	
	public BoardCell pickLocation(Set<BoardCell> t){
		// this uses 'lastRoomVisited'
		// Temporary return type WalkwayCell. Can be either a RoomCell or WalkwayCell
		return new WalkwayCell(0,0,"X");
	}
	
	public void createSuggestion(){
		
	}
	
	public void udateSeen(Card seen){
		
	}

	public char getLRV() {
		return lastRoomVisited;
	}

	public void setLRV(char r) {
		lastRoomVisited = r;
	}
	
}
