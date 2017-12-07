package csc312;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import csc312.servlet.*;

public class TestProject2 {

	@Test
	public void testWordList() {
		String[] wordList = {
	 			"job", "jow", "zap", "bee", "zep",
	 			"zip", "jib", "jam", "coz", "yak", 
	 			"biz", "fez", "fiz", "wiz", "jab",
	 			"zoo", "jaw", "jug", "pyx", "haj"
	 			};
		String words[] = Main.words;
		
		assertEquals(20, words.length);
		assertTrue(Arrays.equals(wordList, words));
	}
	
	@Test
	public void testNewContestUniqueID() {
		ArrayList<Integer> cl = new ArrayList<Integer>(); cl.add(44);
		int newRandomID = NewContest.getRandomNumber();
		boolean b1 = (cl.get(0) == newRandomID) ? false : true;
		if ( NewContest.checkUnique(newRandomID, cl) ) {
			cl.add(newRandomID);
			assertEquals( 2, cl.size() );
		} else {
			assertEquals( 0, cl.size() );
		}
	}
	
	@Test
	public void testCorrectLetter() {
		
		char[][] board1 = WordFinder.board1;
		char[][] board2 = WordFinder.board2;
		char[][] board3 = WordFinder.board3;
		
        char column[] = {'a', 'b', 'c', 'd', 'e'};
		
		assertEquals('C', WordFinder.getLetter(board1, "1", "a3", column));
		assertEquals('Z', WordFinder.getLetter(board2, "2", "c2", column));
		assertEquals('a', WordFinder.getLetter(board3, "3", "e1", column));
	}
	
	@Test
	public void testWordFinderInputCheck() {
		ArrayList<Integer> cl = new ArrayList<Integer>(); cl.add(44);

        char column[] = {'a', 'b', 'c', 'd', 'e'};
        int row[] = {1, 2, 3, 4, 5};
        
        assertTrue(WordFinder.checkInput(cl, "44", "2", "a4", row, column));  // all of them right
        assertFalse(WordFinder.checkInput(cl, "77", "2", "a4", row, column)); // contest id wrong 
        assertFalse(WordFinder.checkInput(cl, "44", "4", "a4", row, column)); // game id wrong
        assertFalse(WordFinder.checkInput(cl, "44", "2", "f4", row, column)); // column wrong
        assertFalse(WordFinder.checkInput(cl, "44", "2", "e6", row, column)); // row wrong
        assertFalse(WordFinder.checkInput(cl, "77", "4", "f6", row, column)); // all of them wrong
	}
	
	@Test
	public void testSolutionInput() {
		ArrayList<Integer> cl = new ArrayList<Integer>(); cl.add(44);
		
		assertTrue(Solution.checkSolutionInput(cl, "44", "1", "bee", "bee"));
		assertTrue(Solution.checkSolutionInput(cl, "44", "2", "yak", "yak"));
		assertTrue(Solution.checkSolutionInput(cl, "44", "3", "zoo", "zoo"));
		assertFalse(Solution.checkSolutionInput(cl, "44", "2", "bee", "yak"));
	}

	@Test
	public void testTopFiveScore() {
		
		ArrayList<Integer> cl = new ArrayList<Integer>();
		ArrayList<Long> tlOld = new ArrayList<Long>();
		ArrayList<Long> tlNew = new ArrayList<Long>();
		
		cl.add(11); tlOld.add(4L);
		cl.add(22); tlOld.add(3L);
		cl.add(33); tlOld.add(1L);
		cl.add(44); tlOld.add(2L);
		cl.add(55); tlOld.add(6L);
		cl.add(66); tlOld.add(5L);
		
		for (int i = 0; i < tlOld.size(); i++) {
			tlNew.add(tlOld.get(i));
		}
		Collections.sort(tlNew);
		ArrayList<Integer> TopFive = TopScore.getTopFiveContest(cl, tlOld, tlNew, 5 );
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(33); al.add(44); al.add(22); al.add(11); al.add(66); 
		
		
		assertEquals(TopFive, al);
	}
	
	@Test
	public void testTimeOut() {
		
		String gameIndex = "1";
		String hSearch = "", vSearch = "";
		String solution = "bee", result = "bee";
		char[][] board1 = WordFinder.board1;
		long delay = 5000;
		boolean timeOK = true;
		
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(delay);
			
	        if ( Integer.parseInt(gameIndex) == 1 ) {
	        		
	        		hSearch = Solution.getBoardAsStringHorizontally(board1);
	        		vSearch = Solution.getBoardAsStringVertically(board1);
	        		if (hSearch.contains(solution)) {
	        			result = solution;
	        		} 
	        		if (vSearch.contains(solution)) {
	        			result = solution;
	        		}
	        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long totalTime = end - start;
		
		timeOK = (totalTime > delay) ? false : true;
		
		assertTrue(totalTime > delay);
		assertFalse(timeOK);
	}

}
