package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.ClueGame;

public class GameSetupTests {
	private static ClueGame game;
	
	@BeforeClass
	public static void setUp() {
		game = new ClueGame("PlayerData.txt");
		game.loadConfigFiles();
		game.deal();
	}	
	
	@Test
	public void LoadingPlayers(){
		assert(game.getPlayers().get(0).getName().equals("Miss Scarlet"));
		assert(game.getPlayers().get(1).getName().equals("Colonel Mustard"));
		assert(game.getPlayers().get(4).getName().equals("Professor Plum"));
		assert(game.getPlayers().get(0).getColor().equals("red"));
		assert(game.getPlayers().get(1).getColor().equals("yellow"));
		assert(game.getPlayers().get(4).getColor().equals("purple"));
		assert(game.getPlayers().get(0).getStartingLocation() == 7);
		assert(game.getPlayers().get(1).getStartingLocation() == 296);
		assert(game.getPlayers().get(4).getStartingLocation() == 18);
	}
	
	@Test
	public void LoadingCards(){
		fail("not yet implemented");
	}
	
	@Test
	public void DealingCards(){
		fail("not yet implemented");
	}

}
