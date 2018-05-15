import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.lang.*;



class Battleship{
    static Scanner keyboard=new Scanner(System.in);


    //	print the board
	private static void printBoard(int[][] ShipBoard)
	{
		// print board
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.print(ShipBoard[i][j]+" ");
			}
			System.out.print("\n");
		}
	}


    private static void clearConsole()
	{
		String osName=System.getProperty("os.name");
		if(osName.equals("Linux"))
		{
			try
			{
				System.out.print("\033[H\033[2J");
			}
			catch(Exception e)
			{
//				System.out.println("Didn't work 2");
			}
			return;
		}
		
		
//****************************************************************************		
		osName=System.getProperty("sun.desktop");
		if(osName.equals("windows"))
		{
			try
			{
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			catch(Exception e)
			{
//				System.out.println("Didn't work 1");
			}
			return;
		}
	}


    public static void playGame(){
        //Creating a Start up Animation. Just to be fancy :)
        StartUpAnimation sa = new StartUpAnimation();
        sa.createStartUpAnimation();
        //Creating a new Scanner for the user's answers
        Scanner answer=new Scanner(System.in);

        // get a response, whether he will play against cpu or another human.
        System.out.println("Do you want to play against computer or CPU(player/CPU).");
        String versus=answer.nextLine();
        // check if answer is correct.
		while (!versus.equals("player")&&!versus.equals("CPU"))
		{
			System.out.println("Wrong input. Please try again.");
			versus=answer.nextLine();
        }
        
        
        //		Player enter the names
		System.out.println("Please enter the name for player 1: ");
		String playerName1=keyboard.nextLine();
		System.out.println("Please enter the name for player 2: ");
		String playerName2=keyboard.nextLine();
		System.out.println("\n");



        /*
        *   Creating 2 players that will be initialized according to the choice that the user will make later.
        *   It is not clear the answer that the user will make. So, I have to initialise the fields later.
        */
        RandomStrategy pcStrategy= new RandomStrategy();
        Player player1=new HumanPlayer(playerName1);

        
        clearConsole();


        boolean isCPU=false;


        Player player2;
        if(versus.equals("CPU"))
		{
            isCPU=true;
            // if the person wants to battle against CPU then the second player is defined as a ComputerPlayer
			player2=new ComputerPlayer(playerName2);
		}
		if(versus.equals("player"))
		{
            // if the person wants to battle against Human then the second player is defined as a HumanPlayer
			player2=new HumanPlayer(playerName2);
        }
        else
        {
//          This is needed so that the compiler realises that the field has been initialised succesfully.
//          Otherwise, it doesn't know that these are the only options available.
            player2=null;
        }

        //****************************************************************************		
        ShipBoard boardPlayer1=new HumanShipBoard();
        ShipBoard boardPlayer2;
        
        if(isCPU){
            boardplayer2=new ComputerShipBoard();
        }
        else{
            boardPlayer2=new HumanShipBoard();
        }
//****************************************************************************		
		
		
//		print the ship board
		System.out.println("The ship board for player: "+playerName2+" is :\n");
		printBoard(ShipBoardPlayer2);
//****************************************************************************


        /*
        *    Both HumanPlayer and CompputerPlayer have the method createBoard
        *    as it is an abstract method of Player which is the one that both 
        *    of these classes extend. So, no matter whether it is a HumanPlayer
        *    or a ComputerPlayer, I can use the createBoard method
        */
        System.out.println("Player1 Please enter your ships");
        //player1.createBoard();
        System.out.println("Player2 please enter your ships");
        //player2.createBoard();

        
        int[][] ShipBoardPlayer1=boardPlayer1.returnBoard();
        System.out.println("The ship board for player: "+playerName1+" is :\n");
		printBoard(ShipBoardPlayer1);
        int[][] StrikeBoardPlayer1=player1.returnStrikeBoard();
        //		return the ship and strike board of player 2
		int[][] ShipBoardPlayer2=boardPlayer2.returnBoard();
		int[][] StrikeBoardPlayer2=player2.returnStrikeBoard();














        boolean allSank=false;
		while(!allSank)
		{
//****************************************************************************			
//			Player1
			
			System.out.println("Hit point for: "+playerName1);
			
			int[] coordinates=pcStrategy.nextStrike();
//			System.out.println( coordinates[0]+" , "+coordinates[1]);
			boolean isHit=boardPlayer1.getStrike(coordinates[0],coordinates[1]);
//			System.out.print("Main isHit= "+isHit);
			int[][] strikeBoardPlayer1=player1.update(coordinates,isHit);
			System.out.println("The strike board for player: "+playerName1+" is :\n");
			printBoard(strikeBoardPlayer1);
			boolean allSank1=boardPlayer1.allShipsSank();			
			
			
//			Check if All of Player 1 Ships have been Sank.
			
			if(allSank1)
			{
				System.out.println(playerName2+" is the winner!!!");
				allSank=allSank1;
				break;
			}
			
//			Delay for 1 second

			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(Exception e)
			{
				continue;
			}
//****************************************************************************			
//****************************************************************************			
//			Player2
		
			System.out.println("Hit point for: "+playerName2);
			coordinates=player2.nextStrike();
//			System.out.println( coordinates[0]+" , "+coordinates[1]);
			isHit=boardPlayer2.getStrike(coordinates[0],coordinates[1]);
//			System.out.print("Main isHit= "+isHit);
			int[][] strikeBoardPlayer2=player2.update(coordinates,isHit);
			System.out.println("The strike board for player: "+playerName2+" is :\n");
			printBoard(strikeBoardPlayer2);
			boolean allSank2=boardPlayer2.allShipsSank();
	//****************************************************************************		
//****************************************************************************			
			if(allSank2)
			{
				System.out.println(playerName1+" is the winner!!!");
				allSank=allSank2;
				break;
            }
            System.out.println("Thanks for playing. This was a really nice game.");
            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch(Exception e)
            {
                
            }
            System.exit(0);
        }
    }




    public static void main(String[] args) {
        playGame();
    }
}