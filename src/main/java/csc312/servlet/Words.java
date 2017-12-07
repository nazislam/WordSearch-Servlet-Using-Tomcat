package csc312.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.Main;

public class Words extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    		
    	 	ServletOutputStream out = resp.getOutputStream();
    	 	Main m = new Main();
 	 	String words[] = m.words;

 	 	
    	 	for (int i = 0; i < words.length; i++) {
    	 		out.write(words[i].getBytes());
    	 		out.write("\n".getBytes());
    	 	}
        
    	 	
    	 	
        out.flush();
        out.close();
        
       
        
    }

}