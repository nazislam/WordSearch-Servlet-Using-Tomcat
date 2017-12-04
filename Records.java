package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Records extends HttpServlet {


	
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        ServletOutputStream out = resp.getOutputStream();
       
        NewContest nc = new NewContest();
        
        ArrayList<Integer> cl = nc.contestList;
        ArrayList<String> g1s = nc.game1Solutions;
        ArrayList<String> g2s = nc.game2Solutions;
        ArrayList<String> g3s = nc.game3Solutions;
        ArrayList<Integer> g1lc = nc.game1LetterCounts;
        ArrayList<Integer> g2lc = nc.game2LetterCounts;
        ArrayList<Integer> g3lc = nc.game3LetterCounts;
        
        
        out.write("CONTEST LIST: \n".getBytes());
        for (int i : cl) {
        		out.write(Integer.toString(i).getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 1 SOLUTION: \n".getBytes());
        for (String s : g1s) {
        		out.write(s.getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 2 SOLUTION: \n".getBytes());
        for (String s : g2s) {
        		out.write(s.getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 3 SOLUTION: \n".getBytes());
        for (String s : g3s) {
        		out.write(s.getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 1 LETTERS COUNTED: \n".getBytes());
        for (int i : g1lc) {
        		out.write(Integer.toString(i).getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 2 LETTERS COUNTED: \n".getBytes());
        for (int i : g2lc) {
        		out.write(Integer.toString(i).getBytes());
        		out.write("\n".getBytes());
        }
        
        out.write("GAME 3 LETTERS COUNTED: \n".getBytes());
        for (int i : g3lc) {
        		out.write(Integer.toString(i).getBytes());
        		out.write("\n".getBytes());
        }
        
        
        
        out.flush();
        out.close();
        
       
        
    }

}