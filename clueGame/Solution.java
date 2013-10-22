package clueGame;

public class Solution {
	private String suspect;
	private String room;
	private String weapon;
	
	public Solution(String s, String r, String w){
		suspect = s;
		room = r;
		weapon = w;
	}
	
	public boolean equals(Solution other){
		// Just gotta see they are all the same!
		return (suspect.equals(other.suspect) && room.equals(other.room) && weapon.equals(other.weapon));
	}
	
	// Getters are just for testing
	
	public String getSuspect(){
		return suspect;
	}
	
	public String getRoom(){
		return room;
	}
	
	public String getWeapon(){
		return weapon;
	}
}
