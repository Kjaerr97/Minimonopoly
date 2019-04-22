CREATE database pisd19_gruppeF;
USE pisd19_gruppeF;
create table Game
   (gameID int (8) NOT NULL UNIQUE AUTO_INCREMENT, primary key (gameID),
   gameName varchar (20),
   currentplayer int (8)
	);

create table Player 
(gameID int NOT NULL, 
playerID int NOT NULL,

name varchar (20),
currentPosition int(8),
inPrison boolean, 
isBroke boolean,
balance int(11),
colour int(8), 

PRIMARY KEY (gameID, playerID),
FOREIGN KEY (gameID) references Game(gameID)
);
