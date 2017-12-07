package csc312.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopScore extends HttpServlet {

	public static ArrayList<Integer> getTopFiveContest (
			ArrayList<Integer> cl,
			ArrayList<Long> tlOld,
			ArrayList<Long> tlNew,
			int counter
		) {
		ArrayList<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < counter; i++) {
			r.add(  cl.get  ( tlOld.indexOf ( tlNew.get(i) ) ) );
		}
		
		return r;
	}
	
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        ServletOutputStream out = resp.getOutputStream();
       
        Solution s = new Solution();
        NewContest nc = new NewContest();
        
        ArrayList<Long> tl = s.timeList;
        ArrayList<Integer> cl = nc.contestList;
        ArrayList<Long> tlNew = new ArrayList<Long>();
        
        for (int i = 0; i < tl.size(); i++) {
        		tlNew.add(tl.get(i));
        }
        
        Collections.sort(tlNew);
        int counter = (cl.size() > 5) ? 5 : cl.size() ;
        out.write("Top 5 Score (ID, Time taken):\n".getBytes());
        ArrayList<Integer> TopFiveContest = getTopFiveContest(cl, tl, tlNew, counter);
        
        for (int i = 0; i < TopFiveContest.size(); i++) {
        		String output1 = Integer.toString( TopFiveContest.get(i) );
        		String output2 = Long.toString( tlNew.get(i) )  ;
        		
        		out.write(output1.getBytes());
        		out.write(", ".getBytes());
        		out.write(output2.getBytes());
        		out.write(" ms\n".getBytes());
        }
        
        out.flush();
        out.close();
        
       
        
    }

}