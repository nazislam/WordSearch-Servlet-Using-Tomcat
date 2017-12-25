package csc312;

/*
 * Naz-Al Islam
 * 12/05/2017
 *
 */

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import csc312.servlet.*;

public class Main {
	
	public static String[] words = {
 			"job", "jow", "zap", "bee", "zep",
 			"zip", "jib", "jam", "coz", "yak", 
 			"biz", "fez", "fiz", "wiz", "jab",
 			"zoo", "jaw", "jug", "pyx", "haj"
 			};
	
	public static void main(String[] args)
    		  throws LifecycleException, InterruptedException, ServletException {

		
		
		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(8080);
	
	    Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
	

	    Tomcat.addServlet(ctx, "words", new Words() );
	    Tomcat.addServlet(ctx, "newcontest", new NewContest() );
	    Tomcat.addServlet(ctx, "wordfinder", new WordFinder() );
	    Tomcat.addServlet(ctx, "solution", new Solution() );
	    Tomcat.addServlet(ctx, "topscore", new TopScore() );
		    
	    


	    ctx.addServletMapping("/words", "words");
	    ctx.addServletMapping("/newcontest", "newcontest");
	    ctx.addServletMapping("/wordfinder", "wordfinder");
	    ctx.addServletMapping("/solution", "solution");
	    ctx.addServletMapping("/topscore", "topscore");
	    
	    
	    
	    tomcat.start();
	    tomcat.getServer().await();
	}
	
}
