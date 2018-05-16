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


    public static void playGame(Player player1, Player player2){
        //Creating a Start up Animation. Just to be fancy :)
        
        //StartUpAnimation sa = new StartUpAnimation();
        //sa.createStartUpAnimation();
        
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

        boolean isCPU;
        if(versus=="CPU"){
            isCPU=true;
        }
        else{
            isCPU=false;
        }




        player1=new ComputerPlayer(playerName1);
        if(isCPU){
            player2=new ComputerPlayer(playerName2);
        }
        else{
            player2=new HumanPlayer(playerName2);
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




    public static void main(String[] args) {
        Player player1=null;
        Player player2=null;
        playGame(player1,player2);
    }
}