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
	
}
