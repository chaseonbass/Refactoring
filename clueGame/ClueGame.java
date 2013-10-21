package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ClueGame {

	private String playerData;
	private String cardData;
	private ArrayList<Card> deck;
	private ArrayList<Player> players;
	
	public ClueGame(String playerData, String cardData){
		this.playerData = playerData;
		this.cardData = cardData;
	}
	
	public void deal(){
		
	}
	
	public void loadConfigFiles(){
		players = new ArrayList<Player>();
		try{
			FileReader reader = new FileReader(playerData);
			Scanner in = new Scanner(reader);
			String tempName;
			String tempColor;
			int tempStart;
			while(in.hasNext()){
				String[] tempLine = in.nextLine().split(", ");
				tempName = tempLine[0];
				tempColor = tempLine[1];
				tempStart = Integer.parseInt(tempLine[2]);
				players.add(new Player(tempName,tempColor,tempStart));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectAnswer(){
		
	}
	
	public void handleSuggestion(String p, String w, String r, Player accuser){
		
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public boolean checkAccusation(Solution s){
		return false;
	}
}
