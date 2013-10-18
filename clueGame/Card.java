package clueGame;

public class Card {
	public enum CardType {PERSON, ROOM, WEAPON}
	private String name;
	private CardType type;
	
	public Card(String n){
		name = n;
	}
	
	public String getCard(){
		return name;
	}
	
	public CardType getType(){
		return type;
	}
}
