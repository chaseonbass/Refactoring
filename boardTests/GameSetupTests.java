package boardTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.ClueGame;

public class GameSetupTests {
	private static ClueGame game;
	
	@BeforeClass
	public static void setUp() {
		game = new ClueGame("PlayerData.txt", "cardConfig.txt", "BoardLayout.csv", "legend.txt");
		game.loadConfigFiles();
		game.deal();
	}	
	
	@Test
	public void LoadingPlayers(){
		assertEquals(game.getPlayers().size(),6);
		assertEquals(game.getPlayers().get(0).getName(),"Miss Scarlet");
		assertEquals(game.getPlayers().get(1).getName(),"Colonel Mustard");
		assertEquals(game.getPlayers().get(4).getName(),"Professor Plum");
		assertEquals(game.getPlayers().get(0).getColor(),"red");
		assertEquals(game.getPlayers().get(1).getColor(),"yellow");
		assertEquals(game.getPlayers().get(4).getColor(),"purple");
		assertEquals(game.getPlayers().get(0).getStartingLocation() , 7);
		assertEquals(game.getPlayers().get(1).getStartingLocation() , 296);
		assertEquals(game.getPlayers().get(4).getStartingLocation() , 18);
	}
	
	@Test
	public void LoadingCards(){
		ArrayList<Card> tempCards = game.getDeck();
		assertEquals(tempCards.size(),21);
		assertEquals(tempCards.get(2).getName(),"Warrior's SwordStaff");
		assertEquals(tempCards.get(2).getType(),Card.CardType.WEAPON);
		assertEquals(tempCards.get(10).getName(),"Professor Plum");
		assertEquals(tempCards.get(10).getType(),Card.CardType.SUSPECT);
		assertEquals(tempCards.get(17).getName(),"Study");
		assertEquals(tempCards.get(17).getType(),Card.CardType.ROOM);
	}
	
	@Test
	public void DealingCards(){
		fail("not yet implemented");
	}

}
