package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	
	public ComputerPlayer(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	public void pickLocation(Set<BoardCell> t){
		
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
