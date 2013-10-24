package clueGame;

import java.util.ArrayList;
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
	
	public void makeSuggestion(String roomName){
		
		ArrayList<Card> a = knownDeck;
		for (Card c : a)
			System.out.println(c);
		Card indexCard = a.get(0);
		//cannot be own hand
		//has to be from known deck
		//cannot be what has already been seen
		boolean isSorting = true;
		while (!a.isEmpty() || isSorting){
			Card c = a.remove(0);
			boolean isInHand = false;
			for (Card d : getCards())
				if (c.getName().equals(d.getName()))
					isInHand = true;
				else{
					for (Card e : seen)
						if (c.getName().equals(e.getName()))
							isInHand = true;
				}
			if (!isInHand){
				indexCard = c;
				a.add(c);
			}
			
			if (c.getName().equals(indexCard.getName()))
				break;
			//return it to the deck and move along		
		}
		suspect = new ArrayList<Card>();
		weapon = new ArrayList<Card>();
		room = new ArrayList<Card>();
		
		while (!a.isEmpty()){
			Card c = a.remove(0);
			if (c.getType() == Card.CardType.SUSPECT)
				suspect.add(c);
			else if (c.getType() == Card.CardType.WEAPON)
				weapon.add(c);
			else if (c.getType() == Card.CardType.ROOM)
				room.add(c);
			else{
				System.out.println("uh oh");
			}
		}
		for (Card c : a)
			System.out.println(c);
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
		// TODO Auto-generated method stub
		if (suspect.size() >= 1)
			return weapon.get(new Random().nextInt(weapon.size()));
		else
			return null;
	}

	public Card getSuspect() {
		// TODO Auto-generated method stub
		if (suspect.size() >= 1)
			return suspect.get(new Random().nextInt(suspect.size()));
		else
			return null;
	}

	public Card getRoom() {
		// TODO Auto-generated method stub
		return new Card(Card.CardType.ROOM, currentRoom);
	}
	
}
