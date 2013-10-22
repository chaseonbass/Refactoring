package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.ClueGame;

public class GameActionTests {
	private static ClueGame game;
	
	@BeforeClass
	public static void setUp() {
		game = new ClueGame("PlayerData.txt", "cardConfig.txt", "BoardLayout.csv", "legend.txt");
		game.loadConfigFiles();
		game.deal();
	}	

	@Test
	public void CheckingAccusation(){
		fail("not yet implemented");
	}
	
	@Test
	public void SelectingTargetLocation(){
		fail("not yet implemented");
	}
	
	@Test
	public void DisprovingSuggestion(){
		fail("not yet implemented");
	}
	
	@Test
	public void MakingSuggestion(){
		fail("not yet implemented");
	}

}
