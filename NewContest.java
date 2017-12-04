package csc312.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.servlet.Words;

public class NewContest extends HttpServlet {
	
	
	// ArrayList<String> wordFixedList = w.wordList;

	ExecutorService service = Executors.newSingleThreadExecutor();
	//public static ArrayList<Integer> numOfLetters = new ArrayList<Integer>(); // list of results (bee, yak, zoo)
    public static ArrayList<Integer> contestList = new ArrayList<Integer>(); // list of contests
    //public static ArrayList<String> foundWords = new ArrayList<String>();
    public static ArrayList<String> game1Solutions = new ArrayList<String>();
    public static ArrayList<String> game2Solutions = new ArrayList<String>();
    public static ArrayList<String> game3Solutions = new ArrayList<String>();
    public static ArrayList<Integer> game1LetterCounts = new ArrayList<Integer>();
    public static ArrayList<Integer> game2LetterCounts = new ArrayList<Integer>();
    public static ArrayList<Integer> game3LetterCounts = new ArrayList<Integer>();
    

    
    
    // gets all words of the website into an array
    public static String[] getWordsInArray(String url) throws Exception {
	 	URL myURL = new URL( url );
	    HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
	    InputStream  is = urlConnection.getInputStream();
	    StringBuilder output = new StringBuilder();
	    int c;
	    while ( (c = is.read() ) != -1 ) {
    			output.append((char)c );
	    }
	    
	    String output_as_string = output.toString();
	    String[] output_as_array = output_as_string.split("\\r?\\n");
	    is.close();
	    return output_as_array;	
            
    }
	
	// This function returns the letter in any given url.
	public static char getContent(String url) throws Exception {
		int maxTries = 10;
        int count = 0;
        while (true) {
            try {
                URL myURL = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
                InputStream is = urlConnection.getInputStream();
                StringBuilder output = new StringBuilder();
                int c;
                while ( (c = is.read()) != -1 ) {
                    output.append((char)c);
                }
                String bn = output.toString();
                char ch = bn.charAt(0);
                return ch;
            } catch (Exception e) {
                if (++count == maxTries) throw e;
            }
        }
    }
	  
    // generates random number, can be duplicate
    public static int getRandomNumber() {
		Random rand = new Random();
		return (rand.nextInt(1000) + 1);
	}
	
    // checks if the random number is duplicate
	public static boolean checkUnique(int num, ArrayList<Integer> list) {
		if (!( list.contains(num) ))
			return true;
		return false;
	}
	
	// This function creates url with query
	public static String makeURL (String contestID, String game_num, String column_num, String row_num) throws Exception {
        String url = "https://wordfinder-001.appspot.com/wordfinder?contest=" + URLEncoder.encode(contestID, "UTF-8") + "&game=" + URLEncoder.encode(game_num, "UTF-8") + "&pos=" + URLEncoder.encode(column_num, "UTF-8")+URLEncoder.encode(row_num, "UTF-8");   
        return url;
    }
	
	// Returns string of horizontal-search letters
    public static String getBoardAsStringHorizontally(int id, int g, char[] column, int[] row) throws Exception {
        StringBuilder text = new StringBuilder();
        int i = 0;
        while (i < 5) {
            String r = Integer.toString(row[i]);
            for (int j = 0; j < 5; j++) {
                String c = Character.toString(column[j]);
                String wordURL = makeURL(Integer.toString(id), Integer.toString(g), c, r);
                char output = getContent(wordURL);
                text.append(output); // This gives all the letters horizontaly in a string
            }
            i++;
        }
        return text.toString();
    }

    // Returns string of vertical-search letters
    public static String getBoardAsStringVertically (int id, int g, char column[], int row[]) throws Exception {
        StringBuilder text = new StringBuilder();
        int i = 0;
        while (i < 5) {
            String c = Character.toString(column[i]);
            for (int j = 0; j < 5; j++) {
                String r = Integer.toString(row[j]);
                String wordURL = makeURL(Integer.toString(id), Integer.toString(g), c, r);
                char output = getContent(wordURL);
                text.append(output); // this gives all the letters vertically in a string
            }
            i++;
        }
        return text.toString();
    }
    
    // this function returns the number of letter counted
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
 							//return sb.toString();
 						}
 					}
 				}
 			}
 		}
 		return counter;
 	}
	
    
 	
 	
 	
    
// 	long startTime = System.currentTimeMillis();
// 	long elapsedTime = 0L;
 	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
	 	
		resp.setStatus( HttpServletResponse.SC_OK);
		
		
		ServletOutputStream out = resp.getOutputStream();
		
		int[] game = {1, 2, 3};
        // int[] game = {1, 2, 3};
        char column[] = {'A', 'B', 'C', 'D', 'E'};
        int row[] = {1, 2, 3, 4, 5};
        
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//            public void run() {
//                
//
//            }
//        }, 1000, 1000);
        
    	    int randNumber = getRandomNumber();
    	    if (checkUnique(randNumber, contestList)) {
    	    		int cID = randNumber;
    	    		contestList.add(cID);
    	        out.write(( Integer.toString(cID) ).getBytes());
    	        
    	        try {
    	        		Words w = new Words();
	    	        	String[] wordList = w.words;
                for (int g: game) {
                		for (String word: wordList) {
                        String hSearch = getBoardAsStringHorizontally (cID, g, column, row);
                        String vSearch = getBoardAsStringVertically (cID, g, column, row);
                        if (hSearch.contains(word)) {
                        		if (g == 1) {
                        			game1Solutions.add(word);
                            		game1LetterCounts.add(getNumberOfLettersCounted(hSearch, word));
                            		//game1LetterCounts.add(getNumberOfLettersCounted(vSearch, word));
                        		} else if (g == 2) {
                        			game2Solutions.add(word);
                            		game2LetterCounts.add(getNumberOfLettersCounted(hSearch, word));
                            		//game2LetterCounts.add(getNumberOfLettersCounted(vSearch, word));
                        		} else if (g == 3) {
                        			game3Solutions.add(word);
                        			game3LetterCounts.add(getNumberOfLettersCounted(hSearch, word));
                        			//game3LetterCounts.add(getNumberOfLettersCounted(vSearch, word));
                        		}
                        }
                        if (vSearch.contains(word)) {
	                        	if (g == 1) {
	                    			game1Solutions.add(word);
	                        		game1LetterCounts.add(getNumberOfLettersCounted(hSearch, word));
	                    		} else if (g == 2) {
	                    			game2Solutions.add(word);
	                        		game2LetterCounts.add(getNumberOfLettersCounted(vSearch, word));
	                    		} else if (g == 3) {
	                    			game3Solutions.add(word);
	                    			game3LetterCounts.add(getNumberOfLettersCounted(vSearch, word));
	                    		}
                        }
                		}
                }
            } catch (Exception e) {
            		System.err.println("There was an error");
            } 
    	        
    	    } else {
    	    		out.write("No unique contestID generated!!".getBytes());
    	    }

    	    
    	    
    	    
    	    
    	    
        out.flush();
        out.close();    
    }
	
}