package clueGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private String color;
	private int startingLocation;

	private ArrayList<Card> myCards;
	
	public Player(String n, String c, int s){
		name = n;
		color = c;
		startingLocation = s;
		myCards = new ArrayList<Card>();
	}
	
	public Card disproveSuggestion(String p, String r, String w){
		
		return myCards.get(0);
	}
	
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public int getStartingLocation() {
		return startingLocation;
	}
	
	public ArrayList<Card> getCards(){
		return myCards;
	}
}
