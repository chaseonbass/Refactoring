package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClueGame {

	private String playerData;
	private String cardData;
	private Solution solution;
	private ArrayList<Card> deck;
	private ArrayList<Card> checkDeck;
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
	
	public Card[] getThree(ArrayList<Card> l){
		boolean suspect = false;
		boolean weapon = false;
		boolean room = false;
		Card[] cards = new Card[3];
		for (int i = 0; i<l.size() && (!suspect || !weapon || !room); i++){
			Card c = deck.get(i);
			if (!suspect && c.getType() == Card.CardType.SUSPECT){
				suspect = true;
				cards[0] = c;
			}
			else if (!weapon && c.getType() == Card.CardType.WEAPON){
				weapon = true;
				cards[1] = c;
			}
			else if (!room && c.getType() == Card.CardType.ROOM){
				room = true;
				cards[2] = c;
			}
			else{
				deck.add(c);
			}
		}
		return cards;
		
	}
	
	public void deal(){
		checkDeck = deck;
		Collections.shuffle(deck);
		
		//these are indicators that solution doesn't have these types of cards, YET
		boolean suspect = false;
		boolean weapon = false;
		boolean room = false;
		String s = "";
		String w = "";
		String r = "";
		
		while (!deck.isEmpty() && (!suspect || !weapon || !room)){
			Card c = deck.remove(0);
			if (!suspect && c.getType() == Card.CardType.SUSPECT){
				s = c.getName();
				suspect = true;
			}
			else if (!weapon && c.getType() == Card.CardType.WEAPON){
				w = c.getName();
				weapon = true;
			}
			else if (!room && c.getType() == Card.CardType.ROOM){
				r = c.getName();
				room = true;
			}
			else{
				deck.add(c);
			}
		}
		
		solution = new Solution(s,r,w);
		Collections.shuffle(deck);
		
		int i = 0;
		while (!deck.isEmpty()) {
			players.get(i % players.size()).addCard(deck.remove(0));
			i++;
		}
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
			boolean humanSet = false;
			while(in.hasNext()){
				String[] tempLine = in.nextLine().split(", ");
				tempName = tempLine[0];
				tempColor = tempLine[1];
				tempStart = Integer.parseInt(tempLine[2]);
				if (!humanSet) {
					players.add(new HumanPlayer(tempName,tempColor,tempStart));
					humanSet = true;
				} else {
					players.add(new ComputerPlayer(tempName,tempColor,tempStart));
				}
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
	
	public Card handleSuggestion(Card card, Card card2, Card card3, Player suggester){
		// probably calls disproveSuggestion() on the array 'players'
		// will loop through 'players' and each will try to disprove the suggestion (except the suggester)
		// will return the first instance of a valid disproval or null if none occurred
		for (Player p : getPlayers())
			if (!p.equals(suggester)){
				Card c = p.disproveSuggestion(card3, card2, card);
				if (c!=null)
					return c;
			}
		return null;
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	public ArrayList<Card> getCheckDeck(){
		return checkDeck;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public boolean checkAccusation(Solution s){
		return s.equals(solution);
	}
	
	public Solution getSolution() {
		// ONLY FOR TESTING
		return solution;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void setSolution(Solution s){
		solution = s;
	}
	public void setCheckDeck(ArrayList<Card> c){
		checkDeck = c;
	}
}
