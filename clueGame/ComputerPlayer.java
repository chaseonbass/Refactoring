package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	
	public ComputerPlayer() {
		// for testing
		super();
		seen = new ArrayList<Card>();
	}
	
	public ComputerPlayer(char lrv) {
		lastRoomVisited = lrv;
		seen = new ArrayList<Card>();
	}
	
	public ComputerPlayer(String n, String c, int s) {
		super(n, c, s);
		seen = new ArrayList<Card>();
	}
	
	public BoardCell pickLocation(Set<BoardCell> t){
		// this uses 'lastRoomVisited'
		// Temporary return type WalkwayCell. Can be either a RoomCell or WalkwayCell
		ArrayList<BoardCell> tempUnvisitedDoor = new ArrayList<BoardCell>();
		for(BoardCell b : t) {
			if(b.isDoorway() && b.getInitial() != lastRoomVisited)
				tempUnvisitedDoor.add(b);
		}
		
		Random rand = new Random();
		
		if(!tempUnvisitedDoor.isEmpty()){
			int randNum = rand.nextInt(t.size()) % tempUnvisitedDoor.size();
			return tempUnvisitedDoor.get(randNum);
		} else {
			int randNum = rand.nextInt(t.size()) % t.size();
			int i = 0;
			for (BoardCell b : t) {
				if (i == randNum)
					return b;
				else
					i++;
			}
		}
		
		// As a last resort, if it's failing, this is the 'failure' condition
		return new WalkwayCell(0,0,"X");
	}
	
	public void makeSuggestion(){
		
	}
	
	public void updateSeen(Card seen){
		
	}

	public char getLRV() {
		return lastRoomVisited;
	}

	public void setLRV(char r) {
		lastRoomVisited = r;
	}
	
}
