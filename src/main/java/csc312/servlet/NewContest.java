package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewContest extends HttpServlet {
	
	public static ArrayList<Integer> contestList = new ArrayList<Integer>(); // list of contests
	
	
    // generates random number, can be duplicate
    public static int getRandomNumber() {
		Random rand = new Random();
		return (rand.nextInt(1000) + 1);
	}
	
    // checks if the random number is duplicate
	public static boolean checkUnique(int num, ArrayList<Integer> list) {
		if ( !( list.contains(num) ) )
			return true;
		return false;
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        ServletOutputStream out = resp.getOutputStream();
        
        int randNumber = getRandomNumber();
	    if (checkUnique(randNumber, contestList)) {
	    		resp.setStatus( HttpServletResponse.SC_OK );
	    		int cID = randNumber;
	    		contestList.add(cID);
	        out.write(( Integer.toString(cID) ).getBytes());
	    } else {
	    		resp.setStatus( HttpServletResponse.SC_BAD_REQUEST );
	    		out.write("No unique contest ID generated".getBytes());
	    }
        
        
        
        out.flush();
        out.close();
        
       
        
    }

}