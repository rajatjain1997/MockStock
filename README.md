# MockStock

Working this software is simple and quite intuitive.

Server - 
 - Boot up the server and read the stocks file by clicking on "File" on the menu bar. The file should be in .csv format. (More info on this later)
 - Stats menu shows the current number of connections to the server, and other relevant info. If you want to change the starting balance of the players, just type in the value in the relevant textbox and press "Exit".
  - Now, the server is ready for connections by brokers. After the game is over, use serialize to generate a leaderboard file.
  
  Note - For WAN connections, you'll need to port-forward the server to port 4242.

Client - 
 - Connect to the server by providing its IP address in the box. The IP could be on LAN, WAN or the localhost (Read the note above for WAN connections).
 - Rest is pretty intuitive.
 
Stock File - 
 
 - It should be read before any clients connect to the server.
 - The csv format should be as follow (Use Excel to generate it) -
      
      stockname,price1,price2,price3,...
      
        - stockname can have a-z,A-Z," ",0-9 as characters
        - prices can only be long integers.
