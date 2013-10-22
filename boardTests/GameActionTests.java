package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.Solution;

public class GameActionTests {
	private static ClueGame game;
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		game = new ClueGame("PlayerData.txt", "cardConfig.txt", "BoardLayout.csv", "legend.txt");
		game.loadConfigFiles();
		game.deal();
		
		board = game.getBoard();
		board.calcAdjacencies();
	}

	@Test
	public void testCheckingAccusation(){
		// 4 tests
		// 1 that is correct
		// 3 that have one of each thing wrong
		// THIS IS THE ONLY TIME WE USE THE GETTERS FOR THE SOLUTION CLASS
		Solution realSolution = game.getSolution();
		Solution fakeSolution1;
		Solution fakeSolution2;
		Solution fakeSolution3;
		assertTrue(game.checkAccusation(realSolution));
		
		// Build the fake solutions based on the real solution
		if(!realSolution.getRoom().equals("Hall"))
			fakeSolution1 = new Solution(realSolution.getSuspect(),"Hall",realSolution.getWeapon());
		else
			fakeSolution1 = new Solution(realSolution.getSuspect(),"Conservatory",realSolution.getWeapon());
		
		if(!realSolution.getSuspect().equals("Miss Scarlet"))
			fakeSolution2 = new Solution("Miss Scarlet",realSolution.getRoom(),realSolution.getWeapon());
		else
			fakeSolution2 = new Solution("Mrs. White",realSolution.getRoom(),realSolution.getWeapon());
		
		if(!realSolution.getWeapon().equals("Fruitcake"))
			fakeSolution3 = new Solution(realSolution.getSuspect(),realSolution.getRoom(),"Fruitcake");
		else
			fakeSolution3 = new Solution(realSolution.getSuspect(),realSolution.getRoom(),"Rubber Ducky");
		
		
		// test the fake ones!
		assertFalse(game.checkAccusation(fakeSolution1));
		assertFalse(game.checkAccusation(fakeSolution2));
		assertFalse(game.checkAccusation(fakeSolution3));
	}
	
	// ----------------------------------------------------------------------------------
	// TESTS FOR SELECTING TARGETS (for the computer players)
	
	@Test
	public void testSingleRoomPreference(){
		// Use a loop to check that if a room is a (new) option, it always picks it
		
		// Test for one room in target list
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with 1 room in target
		board.calcTargets(13, 1, 5); // Lounge is within 3 steps, all else walkways!
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			assertEquals(selected,board.getCellAt(10, 1)); // Downward-facing Lounge Doorway
		}
	}
	
	@Test
	public void testMultipleRoomPreference(){
		// Use a loop to check that if a room is a (new) option, it always chooses one of them
		
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with multiple rooms in target
		board.calcTargets(5, 5, 4);
		int loc_4_3Tot = 0; // Down-facing Door: Billiard Room
		int loc_4_2Tot = 0; // Down-facing Door: Billiard Room
		int loc_2_6Tot = 0; // Down-facing Door: Billiard Room
		int loc_7_5Tot = 0; // Up-facing Door: Lounge
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(4, 3))
				loc_4_3Tot++;
			else if (selected == board.getCellAt(4, 2))
				loc_4_2Tot++;
			else if (selected == board.getCellAt(2, 6))
				loc_2_6Tot++;
			else if (selected == board.getCellAt(7, 5))
				loc_7_5Tot++;
			else // if none happen
				fail("Non-room target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_4_3Tot + loc_4_2Tot + loc_2_6Tot + loc_7_5Tot);
		// Ensure each target was selected more than once
		assertTrue(loc_4_3Tot > 8);
		assertTrue(loc_4_2Tot > 8);
		assertTrue(loc_2_6Tot > 8);
		assertTrue(loc_7_5Tot > 8);
	}
	
	@Test
	public void testRandomChoice(){
		// Use a loop to check that each possibility is chosen at least once ('sum' > 1)
		
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just four WalkwayCells
		board.calcTargets(6, 11, 2);
		int loc_5_12Tot = 0;
		int loc_5_10Tot = 0;
		int loc_6_9Tot = 0;
		int loc_6_13Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(5, 12))
				loc_5_12Tot++;
			else if (selected == board.getCellAt(5, 10))
				loc_5_10Tot++;
			else if (selected == board.getCellAt(6, 9))
				loc_6_9Tot++;
			else if (selected == board.getCellAt(6, 13))
				loc_6_13Tot++;
			else // if none happen
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_5_12Tot + loc_5_10Tot + loc_6_9Tot + loc_6_13Tot);
		// Ensure each target was selected more than once
		assertTrue(loc_5_12Tot > 8);
		assertTrue(loc_5_10Tot > 8);
		assertTrue(loc_6_9Tot > 8);
		assertTrue(loc_6_13Tot > 8);
	}
	
	@Test
	public void testRandomWhenRepeatRoom(){
		// Use a loop to check that each possibility is chosen at least once ('sum' > 1)
		
		ComputerPlayer player = new ComputerPlayer('K');
		// Pick a location with a room in targets
		board.calcTargets(21, 14, 2);
		int loc_20_13Tot = 0; // 'kitchen'
		int loc_20_15Tot = 0;
		int loc_21_16Tot = 0;
		int loc_19_14Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(20, 13))
				loc_20_13Tot++;
			else if (selected == board.getCellAt(20, 15))
				loc_20_15Tot++;
			else if (selected == board.getCellAt(21, 16))
				loc_21_16Tot++;
			else if (selected == board.getCellAt(19, 14))
				loc_19_14Tot++;
			else // if none happen
				fail("Invalid target selected (repeat-room option)");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_20_13Tot + loc_20_15Tot + loc_21_16Tot + loc_19_14Tot);
		// Ensure each target was selected more than once
		assertTrue(loc_20_13Tot > 8);
		assertTrue(loc_20_15Tot > 8);
		assertTrue(loc_20_15Tot > 8);
		assertTrue(loc_19_14Tot > 8);
	}
	
	// ----------------------------------------------------------------------------------
	
	@Test
	public void testDisprovingSuggestion(){
		fail("not yet implemented");
	}
	
	@Test
	public void testMakingSuggestion(){
		fail("not yet implemented");
	}

}
