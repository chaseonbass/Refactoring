package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.ClueGame;

public class GameActionTests {
	private static ClueGame board;
	
	@BeforeClass
	public static void setUp() {
		board = new ClueGame();
		board.loadConfigFiles();
		board.deal();
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
