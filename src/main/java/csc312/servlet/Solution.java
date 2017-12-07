package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Solution extends HttpServlet {
	
	public static ArrayList<Long> timeList = new ArrayList<Long>();
	
	public static long getRandomNumber() {
		Random rand = new Random();
		return (rand.nextInt(120000) + 1000);  // random time delay between 1s to 120s
	}
	
    public static String getBoardAsStringHorizontally(char board[][])  {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			text.append(board[i][j]);
    		}
    }
        return text.toString();
    }
    
    public static String getBoardAsStringVertically(char board[][])  {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
	    		for (int j = 0; j < board[0].length; j++) {
	    			text.append(board[j][i]);
	    		}
        }
        return text.toString();
    }
    
    public static int getNumberOfLettersCounted(String search, String word) {
 		StringBuilder sb = new StringBuilder();
 		int counter = 0;
 		char[] a = search.toCharArray();
 		char[] b = word.toCharArray();
 		for (int i=0; i<b.length; i++) {
 			counter++;
 			for (int j=0; j<a.length; j++) {
 				counter++;
 				if (b[i] == a[j]) {
 					if (b[i+1] == a[j+1]) {
 						if (b[i+2] == a[j+2]) {
 							counter++;
 							sb.append(a[j]);
 							sb.append(a[j+1]);
 							sb.append(a[j+2]);
 							return counter;
 						}
 					}
 				}
 			}
 		}
 		return counter;
 	}
    
    public static boolean checkSolutionInput(
			ArrayList<Integer> cl,
			String contestID,
			String game,
			String solution,
			String result
		) {
		boolean b1 = cl.contains(Integer.parseInt(contestID));
		boolean b2 = Integer.parseInt(game) >= 1  && Integer.parseInt(game) <= 3;
		boolean b3 = solution == result;
		
		if (b1 && b2 && b3) {
			return true;
		}
		
		return false;
	}
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
    		long start = System.currentTimeMillis();
    		long delay = getRandomNumber();
    	
        ServletOutputStream out = resp.getOutputStream();
       
        NewContest nc = new NewContest();
        WordFinder wf = new WordFinder();
        
        ArrayList<Integer> cl = nc.contestList;
        
        char board1[][] = wf.board1;
        char board2[][] = wf.board2;
        char board3[][] = wf.board3;
        
        String contestID = req.getParameter("contest");  
		String gameIndex = req.getParameter("game");  
		String solution = req.getParameter("solution"); 
        
		String hSearch = "";
        String vSearch = "";
        String result = "";
        int countedLetters = 0;
        long timeTaken = 0;
        
        
        try {
        	
        		Thread.sleep(delay);
        		
	        if ( Integer.parseInt(gameIndex) == 1 ) {
	        		
	        		hSearch = getBoardAsStringHorizontally(board1);
	        		vSearch = getBoardAsStringVertically(board1);
	        		if (hSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(hSearch, solution);
	        		} 
	        		if (vSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(vSearch, solution);
	        		}
	        }
	        if ( Integer.parseInt(gameIndex) == 2 ) {
		        	hSearch = getBoardAsStringHorizontally(board2);
		    		vSearch = getBoardAsStringVertically(board2);
		    		if (hSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(hSearch, solution);
	        		} 
	        		if (vSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(vSearch, solution);
	        		}
	        }
	        if ( Integer.parseInt(gameIndex) == 3 ) {
		        	hSearch = getBoardAsStringHorizontally(board3);
		    		vSearch = getBoardAsStringVertically(board3);
		    		if (hSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(hSearch, solution);
	        		} 
	        		if (vSearch.contains(solution)) {
	        			result = solution;
	        			countedLetters = getNumberOfLettersCounted(vSearch, solution);
	        		}
	        }
		
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        
        long end = System.currentTimeMillis();
        timeTaken = end - start;
		
        if (checkSolutionInput(cl, contestID, gameIndex, solution, result)) {
        		if (timeTaken < 120000) {
	        		resp.setStatus( HttpServletResponse.SC_OK );
	        		timeList.add(timeTaken);
		        out.write("Solution:          ".getBytes()); out.write(result.getBytes());
		        out.write("\nTime Taken:        ".getBytes()); out.write(Long.toString(timeTaken).getBytes()); 
		        out.write(" ms".getBytes());
		        out.write("\nLetters Requested: ".getBytes()); out.write(Integer.toString(countedLetters).getBytes());
        		} else {resp.setStatus( HttpServletResponse.SC_GONE ); }
        } else {
	        	resp.setStatus( HttpServletResponse.SC_BAD_REQUEST );
	        	if (cl.contains(Integer.parseInt(contestID))) {
        			cl.remove(new Integer(contestID));
        		}
        		out.write("Word not found! Invalid input !!!".getBytes());
        }
        
        out.flush();
        out.close();
        
       
        
    }

}