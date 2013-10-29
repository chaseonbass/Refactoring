package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {

	private String name;
	private Color color;
	private int startingLocation;

	private ArrayList<Card> myCards;
	
	public Player() {
		// for testing
		myCards = new ArrayList<Card>();
	}
	
	public Player(String n, Color c, int s){
		name = n;
		color = c;
		startingLocation = s;
		myCards = new ArrayList<Card>();
	}
	
	public Card disproveSuggestion(Card suspect, Card weapon, Card room){
		ArrayList<Card> matches = new ArrayList<Card>();
		for (Card c : getCards())
			if (c.equals(suspect) || c.equals(weapon) || c.equals(room))
				matches.add(c);
		
		if (matches.size() >= 1)
			return matches.get((new Random()).nextInt(matches.size()));
		
		return null;
	}
	
	// Overloaded function for working with strings
	public Card disproveSuggestions(String room, String weapon, String suspect) {
		ArrayList<Card> matches = new ArrayList<Card>();
		for (Card c : getCards()){
			if (c.getName().equals(room) || c.getName().equals(weapon) || c.getName().equals(suspect))
				matches.add(c);
		}
		if (matches.size() >= 1)
			return matches.get((new Random()).nextInt(matches.size()));
		else
			return null;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
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
	
	public String toString(){
		String s = "";
		for (Card c : myCards)
			s = s + c + " | ";
		return s;
	}
}
