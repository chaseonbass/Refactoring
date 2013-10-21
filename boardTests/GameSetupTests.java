package boardTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.Player;

public class GameSetupTests {
	private static ClueGame game;
	
	@BeforeClass
	public static void setUp() {
		game = new ClueGame("PlayerData");
		game.loadConfigFiles();
		game.deal();
	}	
	
	@Test
	public void LoadingPlayers(){
		assert(game.getPlayers().get(0).getName().equals("Miss Scarlet"));
		assert(game.getPlayers().get(1).getName().equals("Colonel Mustard"));
		assert(game.getPlayers().get(4).getName().equals("Professor Plum"));
		assert(game.getPlayers().get(0).getColor().equals("Red"));
		assert(game.getPlayers().get(1).getColor().equals("Yellow"));
		assert(game.getPlayers().get(4).getColor().equals("Purple"));
		assert(game.getPlayers().get(0).getStartingLocation() == 301);
		assert(game.getPlayers().get(1).getStartingLocation() == 7);
		assert(game.getPlayers().get(4).getStartingLocation() == 141);
	}
	
	@Test
	public void LoadingCards(){
		//Deck contains the correct number of cards
		assert(game.getDeck().size() == 0);
		
		//Deck contains the correct number of cards of each type
		ArrayList<Card> check = new ArrayList<Card>();
		int numberOfTypes = 0;
		for (Card c : game.getDeck())
			for (Card d : check)
				if (d.getType() != c.getType()){
					check.add(c);
					numberOfTypes+=1;
				}
		assert(numberOfTypes == 3);
		
		//I select one room, one weapon, and one person, and ensure the deck contains each of theose
		boolean room = false;
		boolean weapon = false;
		boolean person = false;
		for (Card c : game.getDeck()){
			switch (c.getType()){
			case ROOM: room = true;
			case WEAPON: weapon = true;
			case PERSON: person = true;
			default: fail("Invalid card type");
		}
		assert(room);
		assert(weapon);
		assert(person);
		}
		
	}
	
	@Test
	public void DealingCards(){
		int numCards[] = new int[game.getPlayers().size()];
		for (int i = 0; i < game.getPlayers().size(); i++)
			numCards[i] = game.getPlayers().get(i).getCards().size();
		int dealtSize = 0;
		boolean cardsGivenOkay = true;
		for (int i= 0; i < game.getPlayers().size() - 1; i++){
			if (Math.abs(numCards[i]-numCards[i+1]) > 1)
				cardsGivenOkay = false;
			dealtSize+=numCards[i];
		}
		assert(cardsGivenOkay && dealtSize > 0);
		assert(dealtSize == game.getDeck().size());
			
	}

}
