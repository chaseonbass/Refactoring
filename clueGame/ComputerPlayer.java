package clueGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private ArrayList<Card> seen, knownDeck;
	private ArrayList<Card> suspect, weapon, room;
	String[] knownSolution = {"", "", ""};
	String currentRoom = "";
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

	
	public Suggestion makeSuggestion(String roomName){
		//Suggestion sol;
		//Cannot be in hand
		//Cannot be from seen
		ArrayList<Card> al = new ArrayList<Card>();
		al = knownDeck;
		
		int i = 0;
		while (!al.isEmpty()){
			Card c = al.remove(0);
			boolean removeCard = true;
			for (Card d : getCards())
				if (c.getName().equals(d.getName()))
						removeCard = false;
			for (Card d : getSeen())
				if (c.getName().equals(d.getName()))
						removeCard = false;
			if (!removeCard)
				i = 0;
			else
				al.add(c);
			i+=1;
			if (i > 20)
				break;
		}

		boolean suspect = false;
		boolean weapon = false;
		boolean room = false;
		String s = "";
		String w = "";
		String r = "";
		Collections.shuffle(al);
		while (!al.isEmpty() && (!suspect || !weapon || !room)){
			Card c = al.remove(0);
			if (!suspect && c.getType() == Card.CardType.SUSPECT){
				s = c.getName();
				suspect = true;
			}
			else if (!weapon && c.getType() == Card.CardType.WEAPON){
				w = c.getName();
				weapon = true;
			}
			else if (!room && c.getType() == Card.CardType.ROOM){
				r = c.getName();
				room = true;
			}
			else{
				al.add(c);
			}
		}
		
		return new Suggestion(roomName, w, s);

	}
	
	public void updateSeen(Card other){
		for (Card c : seen)
			if (c.getName().equals(other.getName()))
				return;
		seen.add(other);
	}
	
	public void setKnownDeck(ArrayList<Card> a){
		knownDeck = a;
	}
	
	public ArrayList<Card> getSeen(){
		return seen;
	}

	public char getLRV() {
		return lastRoomVisited;
	}

	public void setLRV(char r) {
		lastRoomVisited = r;
	}

	public Card getWeapon() {
		if (suspect.size() >= 1)
			return weapon.get(new Random().nextInt(weapon.size()));
		else
			return null;
	}

	public Card getSuspect() {
		if (suspect.size() >= 1)
			return suspect.get(new Random().nextInt(suspect.size()));
		else
			return null;
	}

	public Card getRoom() {
		return new Card(Card.CardType.ROOM, currentRoom);
	}
	
}
