package clueGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private String color;
	private int startingLocation;

	private ArrayList<Card> myCards;
	
	public Player() {
		// for testing
		myCards = new ArrayList<Card>();
	}
	
	public Player(String n, String c, int s){
		name = n;
		color = c;
		startingLocation = s;
		myCards = new ArrayList<Card>();
	}
	
	public Card disproveSuggestion(Card suspect, Card weapon, Card room){
		return myCards.get(0);
	}
	
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}
	
	public ArrayList<Card> getCards(){
		return myCards;
	}

	public int getStartingLocation() {
		return startingLocation;
	}

	public void addCard(Card c) {
		myCards.add(c);
	}
}
