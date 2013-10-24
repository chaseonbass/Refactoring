package boardTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
import clueGame.Suggestion;

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
		
		// for disproval tests----------------------------------------------
		// Our cards to play with for testing the next two tests
		mustardCard = new Card(Card.CardType.SUSPECT, "Colonel Mustard");
		missScarletCard = new Card(Card.CardType.SUSPECT, "Miss Scarlet");
		knivesCard = new Card(Card.CardType.WEAPON, "Dual Butterknives");
		fruitcakeCard = new Card(Card.CardType.WEAPON, "Fruitcake");
		hallCard = new Card(Card.CardType.ROOM, "Hall");
		loungeCard = new Card(Card.CardType.ROOM, "Lounge");
		
		// non-hand cards
		plumCard = new Card(Card.CardType.SUSPECT, "Professor Plum");
		masterCard = new Card(Card.CardType.WEAPON, "Master Blaster of DOOM");
		conservCard = new Card(Card.CardType.ROOM, "Conservatory");
		
		// Build test player and test hand
		testPlayer = new Player();
		testPlayer.addCard(mustardCard);
		testPlayer.addCard(missScarletCard);
		testPlayer.addCard(knivesCard);
		testPlayer.addCard(fruitcakeCard);
		testPlayer.addCard(hallCard);
		testPlayer.addCard(loungeCard);
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
	// TESTING FOR DISPROVING SUGGESTIONS
	private static Card mustardCard;
	private static Card missScarletCard;
	private static Card knivesCard;
	private static Card fruitcakeCard;
	private static Card hallCard;
	private static Card loungeCard;
	private static Player testPlayer;
	// non-hand cards
	private static Card plumCard;
	private static Card masterCard;
	private static Card conservCard;
	
	@Test
	public void testOnePlayerOneMatch(){
		// Test each possible outcome
		Card disproveSuspect = testPlayer.disproveSuggestion(missScarletCard, masterCard, conservCard);
		Card disproveWeapon = testPlayer.disproveSuggestion(plumCard, fruitcakeCard, conservCard);
		Card disproveRoom = testPlayer.disproveSuggestion(plumCard, masterCard, hallCard);
		Card nullSuggestion = testPlayer.disproveSuggestion(plumCard, masterCard, conservCard);
		//System.out.println(testPlayer);
		assertEquals(disproveSuspect, missScarletCard);
		assertEquals(disproveWeapon,fruitcakeCard);
		assertEquals(disproveRoom,hallCard);
		assertNull(nullSuggestion);
	}
	
	@Test
	public void testOnePlayerMultMatches(){
		// Test that a random card is shown for multiple matches
		int suspect = 0;
		int room = 0;
		int weapon = 0;
		for (int i = 0; i<100; i++){
			Card disproval = testPlayer.disproveSuggestion(missScarletCard, knivesCard, loungeCard);
			if(disproval.equals(missScarletCard))
				suspect++;
			else if(disproval.equals(knivesCard))
				weapon++;
			else if(disproval.equals(loungeCard))
				room++;
			else
				fail("valid card not selected");
		}
		assertEquals(100, suspect + room + weapon);
		assertTrue(suspect>10);
		assertTrue(room>10);
		assertTrue(weapon>10);
	}
	
	@Test
	public void testAllQueriedToDisprove(){
		// Make test players
		HumanPlayer player = new HumanPlayer("Miss Scarlet", "Red", 0);
		ComputerPlayer com1 = new ComputerPlayer();
		ComputerPlayer com2 = new ComputerPlayer();
		ComputerPlayer com3 = new ComputerPlayer();
		
		// Give them test hands
		player.addCard(conservCard);
		player.addCard(fruitcakeCard);
		player.addCard(plumCard);
		
		com1.addCard(hallCard);
		com1.addCard(masterCard);
		
		com2.addCard(missScarletCard);
		com2.addCard(knivesCard);
		
		com3.addCard(mustardCard);
		com3.addCard(loungeCard);
		
		// Put them in a test array
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(com1);
		players.add(com2);
		players.add(com3);
		
		// Modify the game so that we can use its methods to test with
		game.setPlayers(players);
		
		// Test five (5) cases
		
		// Test a non-disproveable suggestion, should return 'null'
		Card nonDisprovable1 = game.handleSuggestion((new Card(Card.CardType.SUSPECT, "dud")), 
				new Card(Card.CardType.WEAPON, "dud"), new Card(Card.CardType.ROOM, "dud"), player);
		assertNull(nonDisprovable1);
		Card nonDisprovable2 = game.handleSuggestion(new Card(Card.CardType.SUSPECT, "dud"), 
				new Card(Card.CardType.WEAPON, "dud"), new Card(Card.CardType.ROOM, "dud"), com1);
		assertNull(nonDisprovable2);
		
		// Test a suggestion only human can disprove, should return the card they have
		Card humanDisprovable = game.handleSuggestion(new Card(Card.CardType.SUSPECT, "dud"), 
				fruitcakeCard, new Card(Card.CardType.ROOM, "dud"), com1);
		assertEquals(humanDisprovable, fruitcakeCard);
		
		// Test a suggestion only the suggester can disprove, should return 'null'
		Card nonDisprovableTrick = game.handleSuggestion(conservCard, fruitcakeCard, plumCard, player);
		assertNull(nonDisprovableTrick);
		
		// Test that two could disprove, should return first person's card
		Card twoCanDisprove = game.handleSuggestion(hallCard, knivesCard, plumCard, player);
		assertEquals(twoCanDisprove, hallCard);
		Card twoCanDisprove2 = game.handleSuggestion(hallCard, knivesCard, plumCard, com3);
		assertEquals(twoCanDisprove2, plumCard);
		
		// Test that we get to the end of the list (last person can disprove something)
		Card endCanDisprove = game.handleSuggestion(loungeCard, fruitcakeCard, plumCard, player);
		assertEquals(endCanDisprove, loungeCard);
		Card endCanDisprove2 = game.handleSuggestion(loungeCard, knivesCard, missScarletCard, com2);
		assertEquals(endCanDisprove2, loungeCard);
	}
	
	// ----------------------------------------------------------------------------------
	
	@Test
	public void testMakingSuggestion(){
		
		//computer player enters a room, it makes a suggestion
		ComputerPlayer cp = new ComputerPlayer('H');
		Player p = new Player();
		ArrayList<Player> players = new ArrayList<Player>();
		
		//update cards seen for test
		ArrayList<Card> seenCardsTest = new ArrayList<Card>();

		cp.addCard(knivesCard);
		cp.addCard(missScarletCard);
		cp.addCard(hallCard);
		p.addCard(mustardCard);
		p.addCard(loungeCard);
		p.addCard(fruitcakeCard);
		
		players.add(cp);
		players.add(p);
		game.setPlayers(players);
		
		//Test make suggestion to learn more info, does not include a card that has been seen (except room)
		
		//Test suggestions never seen 
		seenCardsTest.add(mustardCard);
		seenCardsTest.add(loungeCard);
		seenCardsTest.add(hallCard);
		cp.updateSeen(mustardCard);
		cp.updateSeen(loungeCard);
		cp.updateSeen(hallCard);
		
		//Test cards that have been seen
		int hasCard = 0;
		for (Card c : seenCardsTest)
			for (Card d : cp.getSeen())
				if (c.getName().equals(d.getName()))
					hasCard += 1;
		
		assertEquals(3, hasCard);

		int mc = 0;
		int fc = 0;
		int nullCounter = 0;
		for (int i = 0; i < 100; i++){
			ComputerPlayer cp2 = new ComputerPlayer('H');
			Player p2 = new Player();
			ArrayList<Player> players2 = new ArrayList<Player>();
			plumCard = new Card(Card.CardType.SUSPECT, "Professor Plum");
			masterCard = new Card(Card.CardType.WEAPON, "Master Blaster of DOOM");
			conservCard = new Card(Card.CardType.ROOM, "Conservatory");
			ArrayList<Card> tempCheckDeck = new ArrayList<Card>();
			for (Player p3 : game.getPlayers())
				for (Card c : p3.getCards())
					tempCheckDeck.add(c);
			tempCheckDeck.add(plumCard);
			tempCheckDeck.add(masterCard);
			tempCheckDeck.add(conservCard);
			game.setCheckDeck(tempCheckDeck);
			cp2.addCard(knivesCard);
			cp2.addCard(missScarletCard);
			cp2.addCard(hallCard);
			cp2.setKnownDeck(tempCheckDeck); //Otherwise I'd have to create an instance of ClueGame within the players itself.
			p2.addCard(mustardCard);
			p2.addCard(loungeCard);
			p2.addCard(fruitcakeCard);
			Solution s = new Solution(conservCard.getName(), masterCard.getName(), plumCard.getName() );
			game.setSolution(s);
			players2.add(cp2);
			players2.add(p2);
			game.setPlayers(players2);
			Suggestion sug = cp2.makeSuggestion(board.getRooms().get('H'));
			Card c = game.handleSuggestions(sug.getRoom(), sug.getWeapon(), sug.getSuspect(), cp2);
			if (c != null){
				if (c.equals(mustardCard))
					mc+=1;
				else if (c.equals(fruitcakeCard))
					fc+=1;
			}
			else
				nullCounter+=1;
			
			
		}

		assertTrue(mc > 10); //mustardCard
		assertTrue(fc > 10); //fruityCakeCrd
		assertTrue(nullCounter > 10); //nullCard
			
		
		
		//Test suggester's location is suggested and person and weapon that are part of the suggestion
	}

}
