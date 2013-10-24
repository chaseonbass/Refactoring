package clueGame;

public class Suggestion {
	String room = "";
	String weapon = "";
	String suspect = "";
	
	public Suggestion (String room, String weapon, String suspect){
		this.room = room;
		this.weapon = weapon;
		this.suspect = suspect;
	}

	public String getRoom() {
		return room;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getSuspect() {
		return suspect;
	}
	public String toString(){
		return getRoom() + getWeapon() + getSuspect();
	}
	
}
