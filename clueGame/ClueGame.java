package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClueGame {

	private String playerData;
	private ArrayList<Card> deck;
	private ArrayList<Player> players;
	Board b;
	int weapon[];
	Solution s = new Solution("1","1","1");
	
	public ClueGame(String playerData, String cardConfig, String boardLayout, String clueLegend){
		this.playerData = playerData;
		b = new Board(boardLayout, clueLegend);
		b.loadConfigFiles();
		players = new ArrayList<Player>();
		createDeck(cardConfig);
		
	}
	
	private void createDeck(String cardConfig) {
		// TODO Auto-generated method stub
		deck = new ArrayList<Card>();
		try {
			FileReader f = new FileReader(cardConfig);
			Scanner in = new Scanner(f);
			while (in.hasNext()){
				String[] cardLine = in.nextLine().split(", ");
				System.out.println((cardLine[0]) + " " + cardLine[1]);
				if (cardLine[1].equals("PERSON"))
					deck.add(new Card(cardLine[0], Card.CardType.PERSON));
				else if (cardLine[1].equals("WEAPON"))
					deck.add(new Card(cardLine[0], Card.CardType.WEAPON));
				else if (cardLine[1].equals("ROOM"))
					deck.add(new Card(cardLine[0], Card.CardType.ROOM));
				else throw new BadConfigFormatException("Card name: " + cardLine[0] +
						" and/or type " + cardLine[1] + " is not valid");
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Just grabbing the rooms from the board.java  config files. Probably not a good idea but hey this happens when I code alone =(
		for (String room : b.getRooms().values())
			if (!room.equals("Walkway") && !room.equals("Closet"))
				deck.add(new Card(room, Card.CardType.ROOM));
		deal();
	}

	public void deal(){
		ArrayList<Card> tempDeck = deck;
		Collections.shuffle(deck);
		ArrayList<Card> hand;
		for (Player p : players){
			hand = new ArrayList<Card>();
			for (Card c : deck)
				if (!hasType(p,c)){
					hand.add(c);
					deck.remove(c);
				}
		}
		deck = tempDeck; //restore the deck. IDK why just seems legit.
	}
	
	public boolean hasType(Player p, Card c){
		for (Card d : p.getCards())
			if (c.getType().equals(d.getClass()))
				return true;
		return false;
	}
	
	public void loadConfigFiles(){
		players = new ArrayList<Player>();
		try{
			FileReader reader = new FileReader(playerData);
			Scanner in = new Scanner(reader);
			String tempName;
			String tempColor;
			int tempStartRow, tempStartCol;
			while(in.hasNext()){
				String[] tempLine = in.nextLine().split(", ");
				tempName = tempLine[0];
				tempColor = tempLine[1];
				tempStartRow = Integer.parseInt(tempLine[2]);
				tempStartCol = Integer.parseInt(tempLine[3]);
				players.add(new Player(tempName,tempColor,b.calcIndex(tempStartRow, tempStartCol)));
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
	
	public Board getBoard(){
		return b;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public boolean checkAccusation(Solution s){
		return false;
	}
}
