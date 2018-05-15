import java.util.Scanner;
class Battleship{


    private void clearConsole()
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
        

        /*
        *   Creating 2 players that will be initialized according to the choice that the user will make later.
        *   It is not clear the answer that the user will make. So, I have to initialise the fields later.
        */
        Player player1=new HumanPlayer();
        Player player2;
        if(versus.equals("CPU"))
		{
            // if the person wants to battle against CPU then the second player is defined as a ComputerPlayer
			player2=new ComputerPlayer();
		}
		if(versus.equals("player"))
		{
            // if the person wants to battle against Human then the second player is defined as a HumanPlayer
			player2=new HumanPlayer();
        }
        else
        {
//          This is needed so that the compiler realises that the field has been initialised succesfully.
//          Otherwise, it doesn't know that these are the only options available.
            player2=null;
        }
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
    }




    public static void main(String[] args) {
        playGame();
    }
}