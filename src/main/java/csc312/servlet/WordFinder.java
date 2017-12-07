package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.servlet.*;

public class WordFinder extends HttpServlet {
	
	public static char[][] board1 = {
			{'a', 'B', 'B', 'E', 'a'},
			{'B', 'b', 'B', 'b', 'E'},
			{'C', 'B', 'b', 'Z', 'E'},
			{'D', 'b', 'b', 'e', 'e'},
			{'a', 'E', 'B', 'E', 'Z'}
	};
	
	public static char[][] board2 = {
			{'a', 'B', 'e', 'E', 'a'},
			{'B', 'B', 'Z', 'E', 'E'},
			{'c', 'B', 'c', 'd', 'y'},
			{'D', 'c', 'B', 'b', 'a'},
			{'e', 'E', 'a', 'E', 'k'}
	};
	
	public static char[][] board3 = {
			{'a', 'B', 'a', 'E', 'a'},
			{'B', 'z', 'Z', 'E', 'E'},
			{'c', 'o', 'c', 'z', 'e'},
			{'D', 'o', 'B', 'E', 'A'},
			{'e', 'E', 'e', 'E', 'e'}
	};
	
	// checks input is right
	public static boolean checkInput(
			ArrayList<Integer> cl,
			String contestID, 
			String game, 
			String position,
			int row[],
			char column[]
			) {
		boolean b1 = cl.contains(Integer.parseInt(contestID));
		boolean b2 = Integer.parseInt(game) >= 1  && Integer.parseInt(game) <= 3;
		boolean b3 = false;
		char p[] = position.toCharArray();
		for (int i = 0; i < 5; i++) {
			if ( (column[i] == p[0]) ) {
				for (int j = 0; j < 5; j++) {
					if ( (row[j] == Character.getNumericValue(p[1]) )) {
						b3 = true;
					}
				}
			}
		}
		if ( b1 && b2 && b3 ) {
			return true;
		}
		return false;
	}
	
	// returns the letter at specific row and column
	public static char getLetter(char[][] board, String gameIndex, String posIndex, char column[]) {
		char r = 'c'; // random value
		int a = 1; // random value
		char[] c = posIndex.toCharArray();
		
		int ri = Character.getNumericValue(c[1]) - 1;
		for (int i = 0; i < 5; i++) {
			if (column[i] == c[0])
				a = i;
		}
		int ci = a;
		r = board[ri][ci];
		return r;
	}

	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        ServletOutputStream out = resp.getOutputStream();
       
        NewContest nc = new NewContest();
        ArrayList<Integer> cl = nc.contestList;
        
        String contestID = req.getParameter("contest");
		String gameIndex = req.getParameter("game");
		String posIndex = req.getParameter("pos");
        
		
        int[] game = {1, 2, 3};
        char column[] = {'a', 'b', 'c', 'd', 'e'};
        int row[] = {1, 2, 3, 4, 5};
        if ( checkInput(cl, contestID, gameIndex, posIndex, row, column) ) {
        		resp.setStatus( HttpServletResponse.SC_OK );
        		char r = 'c';
        		if ( Integer.parseInt(gameIndex) == 1 ) {
        			r = getLetter(board1, gameIndex, posIndex, column);
        		} else if ( Integer.parseInt(gameIndex) == 2 ) {
        			r = getLetter(board2, gameIndex, posIndex, column);
        		} else if ( Integer.parseInt(gameIndex) == 3 ) {
        			r = getLetter(board3, gameIndex, posIndex, column);
        		}
        		
        		out.write(Character.toString(r).getBytes());
        } else {
        		out.write("Invalid Input".getBytes());
        		resp.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }
        
        
        out.flush();
        out.close();
        
       
        
    }

}