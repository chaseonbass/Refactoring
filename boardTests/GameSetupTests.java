package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.ClueGame;

public class GameSetupTests {
	private static ClueGame board;
	
	@BeforeClass
	public static void setUp() {
		board = new ClueGame();
		board.loadConfigFiles();
		board.deal();
	}	
	
	@Test
	public void LoadingPeople(){
		fail("not yet implemented");
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
