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
<<<<<<< HEAD
		game = new ClueGame("PlayerData.txt");
=======
		game = new ClueGame("PlayerData.txt", "cardConfig.txt", "BoardLayout.csv", "ClueLegend.txt");
>>>>>>> Okay, the implemented stuff passes
		game.loadConfigFiles();
		//game.deal();
	}	
	
	@Test
	public void LoadingPlayers(){
<<<<<<< HEAD
		assert(game.getPlayers().get(0).getName().equals("Miss Scarlet"));
		assert(game.getPlayers().get(1).getName().equals("Colonel Mustard"));
		assert(game.getPlayers().get(4).getName().equals("Professor Plum"));
		assert(game.getPlayers().get(0).getColor().equals("red"));
		assert(game.getPlayers().get(1).getColor().equals("yellow"));
		assert(game.getPlayers().get(4).getColor().equals("purple"));
		assert(game.getPlayers().get(0).getStartingLocation() == 7);
		assert(game.getPlayers().get(1).getStartingLocation() == 296);
		assert(game.getPlayers().get(4).getStartingLocation() == 18);
=======
		assert(game.getPlayers().get(0).getName().equals("Andrew Lancaster"));
		assert(game.getPlayers().get(1).getName().equals("Attia Rei"));
		assert(game.getPlayers().get(4).getName().equals("Xina Atal"));
		assert(game.getPlayers().get(0).getColor().equals("brown"));
		assert(game.getPlayers().get(1).getColor().equals("white"));
		assert(game.getPlayers().get(4).getColor().equals("black"));
		assert(game.getPlayers().get(0).getStartingLocation() == game.getBoard().calcIndex(5, 3));
		assert(game.getPlayers().get(1).getStartingLocation() == game.getBoard().calcIndex(5, 17));
		assert(game.getPlayers().get(4).getStartingLocation() == game.getBoard().calcIndex(20, 14));
>>>>>>> Okay, the implemented stuff passes
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
			Card.CardType ct = (Card.CardType)(c.getType());
			if (ct.equals(Card.CardType.ROOM))
					room = true;
			else if (ct.equals(Card.CardType.WEAPON))
					weapon = true;
			else if (ct.equals(Card.CardType.PERSON))
					person = true;
			else fail("Invalid card type");
		}
		assert(room);
		assert(weapon);
		assert(person);
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
