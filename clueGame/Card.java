package clueGame;

public class Card {
	public enum CardType {PERSON, ROOM, WEAPON}
	private String name;
	private CardType type;
	
	public Card(String n, CardType t){
		name = n;
		type = t;
	}
	
	public String getCard(){
		return name;
	}
	
	public CardType getType(){
		return type;
	}
	
	public boolean equals(Card other){
		if (other == null)
			return false;
		return other.getType().equals(type);
	}
}
