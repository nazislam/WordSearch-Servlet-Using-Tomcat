package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.Main;

public class Words extends HttpServlet {
	public static String[] words = {
 			"job", "jow", "zap", "bee", "zep",
 			"zip", "jib", "jam", "coz", "yak", 
 			"biz", "fez", "fiz", "wiz", "jab",
 			"zoo", "jaw", "jug", "pyx", "haj"
 			};
	// public static ArrayList<String> wordList = new ArrayList<String>();

	public static ArrayList<String> populateList(ArrayList<String> al, String[] words) {
		for (int i=0; i<words.length; i++) {
			al.add(words[i]);
		}
		return al;
		
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    		
    	 	ServletOutputStream out = resp.getOutputStream();
 	 	
    	 	// wordList = populateList(wordList, words);

    	 	for (int i = 0; i < words.length; i++) {
    	 		out.write(words[i].getBytes());
    	 		out.write("\n".getBytes());
    	 	}
        
    	 	
    	 	
        out.flush();
        out.close();
        
       
        
    }

}