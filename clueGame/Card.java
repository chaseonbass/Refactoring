package clueGame;

public class Card {
	public enum CardType {SUSPECT, ROOM, WEAPON}
	private String name;
	private CardType type;
	
	public Card(CardType t, String n){
		type = t;
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public CardType getType(){
		return type;
	}
}
