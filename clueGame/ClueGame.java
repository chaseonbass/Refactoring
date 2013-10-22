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
	private Board board;
	
	public ClueGame(String playerData, String cardData, 
			String sheetName, String legendFile){
		this.playerData = playerData;
		this.cardData = cardData;
		// Don't forget to make the board!
		board = new Board(sheetName, legendFile);
		board.loadConfigFiles();
	}
	
	public void deal(){
		
	}
	
	public void loadConfigFiles(){
		loadPlayers();
		loadCards();
	}
	
	public void loadPlayers() {
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
	
	public void loadCards() {
		deck = new ArrayList<Card>();
		try {
			FileReader reader = new FileReader(cardData);
			Scanner in = new Scanner(reader);
			String tempName;
			while(in.hasNext()) {
				String[] tempLine = in.nextLine().split(", ");
				String tempTypeAsStr = tempLine[0];
				tempName = tempLine[1];
				if (tempTypeAsStr.equals("WEAPON"))
					deck.add(new Card(Card.CardType.WEAPON, tempName));
				else if (tempTypeAsStr.equals("SUSPECT"))
					deck.add(new Card(Card.CardType.SUSPECT, tempName));
				else if (tempTypeAsStr.equals("ROOM"))
					deck.add(new Card(Card.CardType.ROOM, tempName));
				else
					throw new BadConfigFormatException("Bad Card Configuration");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException b) {
			System.out.println(b.getMessage());
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