package csc312;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import csc312.servlet.GoodBye;
import csc312.servlet.HelloWorld;
import csc312.servlet.NewContest;
import csc312.servlet.Records;
import csc312.servlet.Reset;
import csc312.servlet.ListOfContest;
import csc312.servlet.Solution;
import csc312.servlet.Words;

public class Main {
	
	public static String solution;
	public static ArrayList<Integer> list;
	
    
    
    // MAIN METHOD
	public static void main(String[] args)
    		  throws LifecycleException, InterruptedException, ServletException {
		
		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(8080);
	
	    // Context ctx = tomcat.addContext("/wordfinder-001.appspot.com", new File(".").getAbsolutePath());
	    Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
	    
	    // SERVLETS
	    Tomcat.addServlet(ctx, "hello", new HelloWorld() );
	    Tomcat.addServlet(ctx, "bye", new GoodBye() );
	    Tomcat.addServlet(ctx, "newcontest", new NewContest() );
	    Tomcat.addServlet(ctx, "listofcontest", new ListOfContest() );
	    Tomcat.addServlet(ctx, "solution", new Solution() );
	    Tomcat.addServlet(ctx, "reset", new Reset() );
	    Tomcat.addServlet(ctx, "words", new Words() );
	    Tomcat.addServlet(ctx, "records", new Records() );
		    
	   
	    // MAPPINGS
	    ctx.addServletMapping("/hello", "hello");
	    ctx.addServletMapping("/goodbye", "bye");
	    ctx.addServletMapping("/newcontest", "newcontest");
	    ctx.addServletMapping("/listofcontest", "listofcontest");
	    ctx.addServletMapping("/solution", "solution");
	    ctx.addServletMapping("/reset", "reset");
	    ctx.addServletMapping("/words", "words");
	    ctx.addServletMapping("/records", "records");
	    
	    // MY CODE
	    // unique id is ready,
	    // now search the grid as before
	    NewContest nc = new NewContest();
	    list = nc.contestList;
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // END MY CODE
	    
	    tomcat.start();
	    tomcat.getServer().await();
	}

	
}