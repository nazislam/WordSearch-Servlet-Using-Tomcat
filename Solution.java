package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.Main;

public class Solution extends HttpServlet {
	
	public static boolean checkAuthority(
				ArrayList<Integer> cl,
				ArrayList<String> g1s,
				ArrayList<String> g2s,
				ArrayList<String> g3s,
				int cid,
				String s,
				String g
			) {
		
		if ( cl.contains(cid) &&  Integer.parseInt(g) == 1 ) {
			return (g1s.contains(s))? true : false ;
		} else if ( cl.contains(cid) && Integer.parseInt(g) == 2 ) {
			return (g2s.contains(s))? true : false ;
		} else if ( cl.contains(cid) && Integer.parseInt(g) == 3 ) {
			return (g3s.contains(s))? true : false ;
		}
		return false;
	}
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    		//  https://solution?contest=99&game=1&solution=bee
    	 	ServletOutputStream out = resp.getOutputStream();
         
        
         
    		NewContest nc = new NewContest();
    		ArrayList<Integer> cl = nc.contestList; 
    		ArrayList<String> g1s = nc.game1Solutions;
    		ArrayList<String> g2s = nc.game2Solutions;
    		ArrayList<String> g3s = nc.game3Solutions;
    		ArrayList<Integer> g1lc = nc.game1LetterCounts;
    		ArrayList<Integer> g2lc = nc.game2LetterCounts;
    		ArrayList<Integer> g3lc = nc.game3LetterCounts;
//    		ArrayList<Integer> g1tc = nc.game1TimeCounts;
//    		ArrayList<Integer> g2tc = nc.game2TimeCounts;
//    		ArrayList<Integer> g3tc = nc.game3TimeCounts;
    		
    		String contestID = req.getParameter("contest");  // [1.....1000]
    		String game = req.getParameter("game");  // [1, 2, 3]
    		String solution = req.getParameter("solution");  // ["bee", "yak", "zoo"]
    		
    		System.out.println( contestID );
    		System.out.println( game );
    		System.out.println( solution );
    		
    		String word = "";
    		String letters = "";
//    		String time;
    	
    		System.out.println(cl.contains(Integer.parseInt(contestID)));
		System.out.println(Integer.parseInt(game) >= 1);
		System.out.println(Integer.parseInt(game) <= 3);
		System.out.println(solution != null);
    		
    		// if (cl.contains(Integer.parseInt(contestID)) && ( Integer.parseInt(game) >= 1 && Integer.parseInt(game) <= 3) && solution != null) {
		if ( checkAuthority(cl, g1s, g2s, g3s, Integer.parseInt(contestID), solution, game) ) {
		
    			resp.setStatus( HttpServletResponse.SC_OK );
    			int index = cl.indexOf( Integer.parseInt(contestID)  );
    		
    			
    			
    			// System.out.println(index + 10); --> works / converted to integer
    			if (Integer.parseInt(game) == 1) {
    				System.out.println("runs the prev line");
    				word = g1s.get(index);
    				// time = g1tc.get(index);
    				letters = Integer.toString(g1lc.get(index));
    			} else if (Integer.parseInt(game) == 2) {
    				word = g2s.get(index);
    				// time = g2tc.get(index);
    				letters = Integer.toString(g2lc.get(index));
    			} else if (Integer.parseInt(game) == 3) {
    				word = g3s.get(index);
    				// time = g3tc.get(index);
    				letters = Integer.toString(g3lc.get(index));
    			} else {
    				System.out.println("else statement runs ");
    				out.write("else statement runs ".getBytes());
    			}
    			
    			System.out.println(word);
    			System.out.println(letters);
    			out.write(word.getBytes());
    			out.write(", ".getBytes());
    			// out.write(time.getBytes());
    			out.write(", ".getBytes());
    			out.write(letters.getBytes());
    			
    		} else {
    			out.write("invalid input".getBytes());
    			resp.setStatus( HttpServletResponse.SC_BAD_REQUEST );
    		
    		}

        
        
        
        
        
        
        
        
        
        
        
        
        out.flush();
        out.close();
        
       
        
    }

}
