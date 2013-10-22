package clueGame;

public class Solution {
	private String person;
	private String room;
	private String weapon;
	
	public Solution(String p, String r, String w){
		person = p;
		room = r;
		weapon = w;
	}
	
	// might need a equals() override
	
	// Getters are just for testing
	
	public String getCriminal(){
		return person;
	}
	
	public String getCrimeScene(){
		return room;
	}
	
	public String getWeapon(){
		return weapon;
	}
}
