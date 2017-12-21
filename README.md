# WordSearch Servlet Using Tomcat

1. **Starting a contest:** A user must start a contest, before requesting a letter, with  a url such as : `https://wordfinder-001.appspot.com/newcontest`. The response to newcontest, will return a random number ranging between 1-1000, that will be used for subsequent request as contestid.  You must make sure, you do not use a contest id which is currently in use.  
	1. For each subsequent request to the server, the contestid must be specified on the url.
	2. For each contest, a 120 seconds timer countdown must be started. When 0 is reached, subsequent request will be returned the status code `HttpServletResponse.SC_GONE`. 

2. **Accessing a letter:** To access a letter at a specific location: `https://wordfinder-001.appspot.com/wordfinder?contest=<contest id received as response to newcontest>&game=<1 to 3>&pos=<column><row>contest:the contest id receives in newcontest`.
	1. game:`1-3`    Column: `a-e`    Row: `1-5`
	2. If the user submits a request, with an invalid contest id, game or pos, you must respond with the status code: `response.SC_BAD_REQUEST`.

3. **Submitting a solution:** To submit a solution to the contest, a specific url is used: `https://wordfinder-001.appspot.com/solution?contest=<contest id received as response to newcontest>&game=<1 to 3>&solution=<word>`
	1. contest: *The contest id receives in newcontest*.  game: *1-3*  solution: *The word which is the solution*
	2. If a submission is valid, `SC_OK` is returned and in the html text, the number of seconds it took to resolve the problem and also, how many letters were requested.
	3. If a submission is invalid for a game, the contest id must be considered invalid, and further request using this contest id should return `response.SC_BAD_REQUEST`.
	4. For a valid submission, keep a list of the 5 fastest (in term of request of letters) contest.

4. **Top Score:** the url : `https://wordfinder-001.appspot.com/topscore`, will return in descending order the list of the 5 fastest time.
	1. Output: *(contest id, time in seconds)*

5. **Word list:** the url `https://wordfinder-001.appspot.com/words`, will return a list of **20**, *3* letters words.

6. **Unit tests:** Individual unit tests for each items to test:
	- Validation that the word list is correct
	- Validation of starting a new contest
	- Validation of requesting for a letter (valid and invalid values for each parameter and combination).
	- Validation of the timeout process for a contest (see [Pausing Execution with Sleep](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html))
	- Validation of submitting a solution (valid and invalid submission + management of the contest)
	- Validation of the top score