package clueGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> myCards;
	
	public Player(String n){
		name = n;
	}
	
	public Card disproveSuggestion(String p, String r, String w){
		
		return myCards.get(0);
	}
}
