## CS 122B Project 2 Session example

This example shows how session works, including saving data to session and retrieve data from session.

### To run this example: 
1. clone this repository using `git clone https://github.com/UCI-Chenli-teaching/project2-session-example.git`
2. open Eclipse -> File -> import -> under "Maven" -> "Existing Maven Projects" -> Click "Finish".
3. For "Root Directory", click "Browse" and select this repository's folder. Click "Finish".
4. You can run this project on Tomcat now.
5. Access session with `http://localhost:8080/project2-session-example/session?myname=Michael`
6. Access items with `http://localhost:8080/project2-session-example/items?newItem=DVD`

### Brief Explanation
`Session.java` is a Java servlet that keeps tracking user information. It creates a new session for each new coming user, it also recognize the previous-visited user and count for visit times.
Try use different names in the url and see the difference.

`Items.java` is a Java servlet that keeps tracking items. It maintains a session for all the previous items that loged on to it.
Try use different items names in the url and see the difference.