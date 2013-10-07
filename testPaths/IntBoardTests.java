package testPaths;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import experiment.IntBoard;

public class IntBoardTests {
	IntBoard board;

	@Before
	public void createBoard(){
		board = new IntBoard(4,4);
	}
	

	@Test
	public void testCalcIndex(){
		int expected = 5;
		int actual = board.calcIndex(1, 1);
		Assert.assertEquals(expected, actual);
		expected = 13;
		actual = board.calcIndex(3,1);
		Assert.assertEquals(expected, actual);
		expected = 0;
		actual = board.calcIndex(0,0);
		Assert.assertEquals(expected, actual);
		expected = 3;
		actual = board.calcIndex(0, 3);
		Assert.assertEquals(expected, actual);
		expected = 7;
		actual = board.calcIndex(1, 3);
		Assert.assertEquals(expected, actual);
		expected = 15;
		actual = board.calcIndex(3, 3);
	}
	@Test
	public void testAdjacency0(){
			board.calcAdjacencies();
			LinkedList testList = board.getAdjList(0);
			Assert.assertTrue(testList.contains(4));
			Assert.assertTrue(testList.contains(1));
			Assert.assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency15(){
		board.calcAdjacencies();
		LinkedList testList = board.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(2, testList.size());
}
	@Test
	public void testAdjacency7(){
		board.calcAdjacencies();
		LinkedList testList = board.getAdjList(7);
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(3, testList.size());
}
	@Test
	public void testAdjacency8(){
		board.calcAdjacencies();
		LinkedList testList = board.getAdjList(8);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(12));
		Assert.assertEquals(3, testList.size());
}
	@Test
	public void testAdjacency5(){
		board.calcAdjacencies();
		LinkedList testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
}
	@Test
	public void testAdjacency10(){
		board.calcAdjacencies();
		LinkedList testList = board.getAdjList(10);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
}
	@Test
	public void testTargets0_3()
	{
		//board.calcAdjacencies();
		board.startTargets(0, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	@Test
	public void testTargets1_1()
	{
		board.calcAdjacencies();
		board.startTargets(1, 1);
		Set targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(2));
	}
	@Test
	public void testTargets6_2()
	{
		board.calcAdjacencies();
		board.startTargets(6, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(11));
	}
	@Test
	public void testTargets15_4()
	{
		board.calcAdjacencies();
		board.startTargets(15, 4);
		Set targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
	}
	
	@Test
	public void testTargets9_5()
	{
		board.calcAdjacencies();
		board.startTargets(9, 5);
		Set targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(15));
	}
	@Test
	public void testTargets12_6()
	{
		board.calcAdjacencies();
		board.startTargets(12, 6);
		Set targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(11));
	}
	
	// Ensure that the adjacency list from a doorway is only the
		// walkway. NOTE: This test could be merged with door 
		// direction test. 
		// These tests are PURPLE on the planning spreadsheet
		@Test
		public void testAdjacencyRoomExit()
		{
			// TEST DOORWAY RIGHT 
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(19, 5));
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.calcIndex(19, 6)));
			// TEST DOORWAY LEFT 
			testList = board.getAdjList(board.calcIndex(21, 17));
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.calcIndex(21, 16)));
			//TEST DOORWAY DOWN
			testList = board.getAdjList(board.calcIndex(10, 1));
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.calcIndex(11, 1)));
			//TEST DOORWAY UP
			testList = board.getAdjList(board.calcIndex(11, 15));
			Assert.assertEquals(1, testList.size());
			Assert.assertTrue(testList.contains(board.calcIndex(10, 15)));
			
		}
		// Ensure that player does not move around within room
		// These cells are ORANGE on the planning spreadsheet
		@Test
		public void testAdjacenciesInsideRooms()
		{
			// Test a corner
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(0, 0));
			Assert.assertEquals(0, testList.size());
			// Test one that has walkway underneath
			testList = board.getAdjList(board.calcIndex(4, 0));
			Assert.assertEquals(0, testList.size());
			// Test one that has walkway above
			testList = board.getAdjList(board.calcIndex(17, 0));
			Assert.assertEquals(0, testList.size());
			// Test one that is in middle of room
			testList = board.getAdjList(board.calcIndex(2, 10));
			Assert.assertEquals(0, testList.size());
			// Test one beside a door
			testList = board.getAdjList(board.calcIndex(7, 17));
			Assert.assertEquals(0, testList.size());
			// Test one in a corner of room
			testList = board.getAdjList(board.calcIndex(11, 5));
			Assert.assertEquals(0, testList.size());
		}
		
		// Test adjacency at entrance to rooms
		// These tests are BLACK in planning spreadsheet
		@Test
		public void testAdjacencyDoorways()
		{
			// Test beside a door direction RIGHT
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(20, 14));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 13)));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 15)));
			Assert.assertTrue(testList.contains(board.calcIndex(19, 14)));
			Assert.assertTrue(testList.contains(board.calcIndex(21, 14)));
			Assert.assertEquals(3, testList.size());
			// Test beside a door direction DOWN
			testList = board.getAdjList(board.calcIndex(5, 17));
			Assert.assertTrue(testList.contains(board.calcIndex(4, 17)));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 17)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 18)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 16)));

			Assert.assertEquals(3, testList.size());
			// Test beside a door direction LEFT
			testList = board.getAdjList(board.calcIndex(20, 9));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 8)));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 10)));
			Assert.assertTrue(testList.contains(board.calcIndex(19, 9)));
			Assert.assertTrue(testList.contains(board.calcIndex(21, 9)));
			Assert.assertEquals(4, testList.size());
			// Test beside a door direction UP
			testList = board.getAdjList(board.calcIndex(6, 19));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 18)));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 20)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 19)));
			Assert.assertTrue(testList.contains(board.calcIndex(7, 19)));
			Assert.assertEquals(4, testList.size());
			// Test beside a door that's not the right direction
			testList = board.getAdjList(board.calcIndex(13, 13));
			Assert.assertTrue(testList.contains(board.calcIndex(12, 13)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 12)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 14)));
			// This ensures we haven't included cell (14, 13) which is a doorway
			Assert.assertEquals(3, testList.size());		
		}
		
		// Test a variety of walkway scenarios
		// These tests are PINK on the planning spreadsheet
		@Test
		public void testAdjacencyWalkways()
		{
			// Test on top edge of board, just one walkway piece
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(0, 12));
			Assert.assertTrue(testList.contains(13));
			Assert.assertEquals(1, testList.size());
			
			// Test on left edge of board, three walkway pieces
			testList = board.getAdjList(board.calcIndex(6, 0));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 0)));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 1)));
			Assert.assertTrue(testList.contains(board.calcIndex(7, 0)));
			Assert.assertEquals(3, testList.size());

			// Test between two rooms, walkways right and left
			testList = board.getAdjList(board.calcIndex(18, 12));
			Assert.assertTrue(testList.contains(board.calcIndex(18, 11)));
			Assert.assertTrue(testList.contains(board.calcIndex(18, 13)));
			Assert.assertEquals(2, testList.size());

			// Test surrounded by 4 walkways
			testList = board.getAdjList(board.calcIndex(14,3));
			Assert.assertTrue(testList.contains(board.calcIndex(14, 2)));
			Assert.assertTrue(testList.contains(board.calcIndex(14, 4)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 3)));
			Assert.assertTrue(testList.contains(board.calcIndex(15, 3)));
			Assert.assertEquals(4, testList.size());
			
			// Test on bottom edge of board, next to 1 room piece
			testList = board.getAdjList(board.calcIndex(21, 14));
			Assert.assertTrue(testList.contains(board.calcIndex(21, 15)));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 14)));
			Assert.assertEquals(2, testList.size());
			
			// Test on right edge of board, next to 1 room piece
			testList = board.getAdjList(board.calcIndex(14, 22));
			Assert.assertTrue(testList.contains(board.calcIndex(14, 21)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 22)));
			Assert.assertEquals(2, testList.size());

			// Test on walkway next to  door that is not in the needed
			// direction to enter
			testList = board.getAdjList(board.calcIndex(5, 3));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 2)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 4)));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 3)));
			Assert.assertEquals(3, testList.size());
		}
		
		
		// Tests of just walkways, 1 step, includes on edge of board
		// and beside room
		// Have already tested adjacency lists on all four edges, will
		// only test two edges here
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsOneStep() {
			board.calcTargets(21, 7, 1);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 7))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 6))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 6))));	
			
			board.calcTargets(14, 0, 1);
			targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 1))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 0))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 0))));			
		}
		// Tests of just walkways, 2 steps
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsTwoSteps() {
			board.calcTargets(21, 7, 2);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(5, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 7))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 6))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 8))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 9))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 5))));
			
			board.calcTargets(14, 0, 2);
			targets= board.getTargets();
			Assert.assertEquals(5, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 0))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 2))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 1))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 1))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 0))));
		}
		// Tests of just walkways, 4 steps
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsFourSteps() {
			board.calcTargets(21, 7, 4);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(8, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 7))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 7))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 6))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 8))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 6))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 9))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 5))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 8))));
			
			// Includes a path that doesn't have enough length
			
			// didn't do this right yet
			board.calcTargets(14, 0, 4);
			targets= board.getTargets();
			Assert.assertEquals(10, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 4))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 2))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 2))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 3))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 2))));
		//	Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 6))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 1))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 1))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 1))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 0))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 0))));
		}	
		// Tests of just walkways plus one door, 6 steps
		// These are LIGHT BLUE on the planning spreadsheet

		@Test
		public void testTargetsSixSteps() {
			board.calcTargets(14, 0, 6);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(9, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 6))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 5))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 0))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 3))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 4))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 1))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 2))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 4))));	
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 1))));
		}	
		
		// Test getting into a room
		// These are LIGHT BLUE on the planning spreadsheet

		@Test 
		public void testTargetsIntoRoom()
		{
			// One room is exactly 2 away
			board.calcTargets(15, 15, 2);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(7, targets.size());
			// directly left (can't go right 2 steps)
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 13))));
			// directly up and down
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 15))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 15))));
			// one up/down, one left/right
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 14))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 16))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 14))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 16))));
		}
		
		// Test getting into room, doesn't require all steps
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsIntoRoomShortcut() 
		{
			board.calcTargets(15, 15, 3);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(12, targets.size());
			// directly up and down
		//	Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 7)))); 	//Cant go up
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 15))));
			// directly right (can't go left)
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 18))));
			// right then down
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 6))));
		//	Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 7))));
			// down then left/right
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 14))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 16))));
			// right then up
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 6))));
			// into the rooms
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 13))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 13))));		
			// 
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 14))));		
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 15))));		
			
		}
///Don't have time to update this yet
		// Test getting out of a room
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testRoomExit()
		{
			// Take one step, essentially just the adj list
			board.calcTargets(4, 20, 1);
			Set<BoardCell> targets= board.getTargets();
			// Ensure doesn't exit through the wall
			Assert.assertEquals(1, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 19))));
			// Take two steps
			board.calcTargets(4, 20, 2);
			targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 19))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 19))));
			Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 18))));
		}


}
