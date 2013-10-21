package clueGame;

public class Card {
	public enum CardType {SUSPECT, ROOM, WEAPON}
	private String name;
	private CardType type;
	
	public Card(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public CardType getType(){
		return type;
	}
}
