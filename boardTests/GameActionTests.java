package boardTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Card;
import clueGame.ClueGame;
import clueGame.Solution;

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
		if(!realSolution.getRoom().equals("Hall")){
			fakeSolution1 = new Solution(realSolution.getSuspect(),"Hall",realSolution.getWeapon());
		}else{
			fakeSolution1 = new Solution(realSolution.getSuspect(),"Conservatory",realSolution.getWeapon());
		}
		if(!realSolution.getSuspect().equals("Miss Scarlet")){
			fakeSolution2 = new Solution("Miss Scarlet",realSolution.getRoom(),realSolution.getWeapon());
		}else{
			fakeSolution2 = new Solution("Mrs. White",realSolution.getRoom(),realSolution.getWeapon());
		}
		if(!realSolution.getWeapon().equals("Fruitcake")){
			fakeSolution3 = new Solution(realSolution.getSuspect(),realSolution.getRoom(),"Fruitcake");
		}else{
			fakeSolution3 = new Solution(realSolution.getSuspect(),realSolution.getRoom(),"Rubber Ducky");
		}
		
		// test the fake ones!
		
		assertFalse(game.checkAccusation(fakeSolution1));
		assertFalse(game.checkAccusation(fakeSolution2));
		assertFalse(game.checkAccusation(fakeSolution3));
		
		
		
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
