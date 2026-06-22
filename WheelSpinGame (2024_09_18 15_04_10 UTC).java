import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.Random;

class Player
{
public String name;
public int points;
public int totalspins;
public boolean playerisout;
public int prizemoney;
public boolean isawinner;

public Player(String n) {

name = n;
Init();

}

public void Init() {
points = 0;
totalspins =0;
playerisout = false;
prizemoney = 0;
isawinner = false;
}

}

public class PriceIsRightWheelSpin
{
public static int Spin(Player currentplayer)
{
int points = 0;

if (currentplayer.playerisout == false )
{
Random rand = new Random();

int n = rand.nextInt(20) + 1;
currentplayer.totalspins = currentplayer.totalspins +1;

if (currentplayer.totalspins == 2 && currentplayer.points==100)
{
//this is a second spin which is not allowed as points=100
System.out.printf("\nPLAYER %s : SPIN NOT ALLOWED AS POINTS EQUALS 100 (Total Spins=%d): TOTAL POINTS =%d", currentplayer.name,currentplayer.totalspins,currentplayer.points);
}

points = n*5;

//add points only for first two spins
if (currentplayer.totalspins <=2 && currentplayer.points<100)
{
currentplayer.points = currentplayer.points + points;
}


if (currentplayer.points > 100 && currentplayer.totalspins <=2)
{
currentplayer.playerisout = true;
}

System.out.printf("\nPLAYER %s : SPIN NUMBER %d : TOTAL POINTS SO FAR=%d", currentplayer.name,currentplayer.totalspins,currentplayer.points);

//add prizemoney for bonus spin
if (currentplayer.totalspins == 3)
{

if (points==100)
{
currentplayer.prizemoney = currentplayer.prizemoney + 25000;
congragulate(currentplayer);
}
else if (points==5 || points==15)
{
currentplayer.prizemoney = currentplayer.prizemoney + 15000;
congragulate(currentplayer);
}
else
{
System.out.printf("\nPLAYER %s : YOU DID NOT WIN A JACKPOT. SPINNING NUMBER WAS %d", currentplayer.name,points);
}
}

}
else
{
System.out.printf("\nPLAYER %s : SPIN NOT ALLOWED AS POINTS ABOVE 100 (Total Spins=%d): TOTAL POINTS =%d", currentplayer.name,currentplayer.totalspins,currentplayer.points);
}

return points; //returns the number fetched on the spin

}

public static boolean AskPlayerIfWannaSpin(Player p)
{

boolean DidPlayerSpin = false;

System.out.printf("\n\n\n PLAYER %s. DO YOU WANT TO SPIN(type y or Y for yes)", p.name);
Scanner scanner = new Scanner(System.in);
if (scanner.next().equalsIgnoreCase("y"))
{
Spin(p);
DidPlayerSpin = true;
}

return DidPlayerSpin;
}

public static void AnnounceWinner(Player[] players)
{
int totalplayers = players.length;
int winnerplayernumber = -1;
int maxbelow100 = 0;
int points;
boolean wasThereAnyWinner = false;
for (int i=0;i<totalplayers;i++)
{
points = players[i].points;
if (points <=100 && points >=maxbelow100)
{
players[i].isawinner = true;

if (points <= 100 && i > 0)
{
if (players[i-1].points != 100)
players[i-1].isawinner = false; //last set winner is no longer a winner
}

maxbelow100 = points;
wasThereAnyWinner = true;
}
else
{
players[i].isawinner = false;
}
}

if (wasThereAnyWinner == true)
{
for (int i=0;i<totalplayers;i++)
{
if (players[i].isawinner == true)
{
System.out.printf("\nPlayer %s is a winner with total points %d and jackpot prize money $%d", players[i].name, players[i].points,players[i].prizemoney);
}
}
}
else
System.out.printf(" THERE WERE NO WINNERS AS EVERYONE SCORED ABOVE 100");

}

public static void PressAnyKeyToContinue()
{
System.out.printf("\n\n\n\nPress any key to continue");
Scanner scanner = new Scanner(System.in);
scanner.nextLine();
}

public static void congragulate(Player currentplayer)
{
System.out.printf("\n\n\nCONGRAGULATION PLAYER %s. YOU WON THE JACKPOT PRIZE OF $%d",currentplayer.name ,currentplayer.prizemoney );
}

public static void main(String args[])
{
int totalplayers = 3;

//create three players
Player[] players = new Player[totalplayers];
for (int i=0;i<totalplayers;i++)
{
players[i] = new Player(Integer.toString(i+1));
}

//PLAY MUST GO ON
while(true)
{
//make sure player is reinitialized before new game
for (int i=0;i<totalplayers;i++)
{
players[i].Init();
}

//first spin
System.out.printf("\n\n\n\n\n\n-----------------------------------------------------------");
System.out.printf("\n-----------------------------------------------------------");
System.out.printf("\n-----------------------------------------------------------");
System.out.printf("\n W E L C O M E T O T H E ");
System.out.printf("\n PRICE-IS-RIGHT WHEEL SPIN GAME");
System.out.printf("\n-----------------------------------------------------------");
System.out.printf("\n-----------------------------------------------------------");
System.out.printf("\n-----------------------------------------------------------");


//first spin
System.out.printf("\n\n HERE STARTS THE FIRST SPIN FOR ALL PLAYERS\n\n");
System.out.printf("\n\n------------------------------------------------------\n\n");

PressAnyKeyToContinue();

for (int i=0;i<totalplayers;i++)
{
Spin(players[i]);
}

PressAnyKeyToContinue();


//second spin
System.out.printf("\n\n HERE STARTS THE SECOND SPIN FOR ALL PLAYERS");
System.out.printf("\n\n------------------------------------------------------\n\n");
for (int i=0;i<totalplayers;i++)
{
//ask player if they wish to spin
AskPlayerIfWannaSpin(players[i]);
}


PressAnyKeyToContinue();

System.out.printf("\n\n NOW PLAYERS WITH 100 POINTS GET ADDITIONAL SPIN FOR JACKPOT");
System.out.printf("\n\n-----------------------------------------------------------------------");

boolean AnyPlayerEligibleForJackpotSpin = false;

for (int i=0;i<totalplayers;i++)
{
if (players[i].points == 100)
{
AnyPlayerEligibleForJackpotSpin = true;

//ask player if they wish to spin
AskPlayerIfWannaSpin(players[i]);
}

}

if (AnyPlayerEligibleForJackpotSpin == false)
System.out.printf("\n\n\n There was no player eligible for jackpot \n\n\n");

PressAnyKeyToContinue();

System.out.printf("\n\n W I N N E R");
System.out.printf("\n\n ---------------------------------------------------------\n\n");
AnnounceWinner(players);


System.out.printf("\n\n\n DO YOU WISH TO PLAY AGAIN (type y or Y for yes)?");
Scanner scanner = new Scanner(System.in);
if (!scanner.next().equalsIgnoreCase("y"))
{
break;
}

} //end while

System.out.printf("\n\n\n\n ---------------------------------------------------------------------------\n\n");
System.out.printf(" THANK YOU. UNTIL NEXT TIME. SAYONAARA !!!!");
System.out.printf("\n\n\n\n ---------------------------------------------------------------------------\n\n");

}//end main
}//end main class