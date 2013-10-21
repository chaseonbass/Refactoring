package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	
	public ComputerPlayer(String n, String c, int s) {
		super(n, c, s);
		// TODO Auto-generated constructor stub
	}
	
	public void pickLocation(Set<BoardCell> t){
		// this uses seen
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
