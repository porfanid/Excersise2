import java.util.Scanner;
class Battleship{


    public static void playGame(){
        StartUpAnimation sa = new StartUpAnimation();
        sa.createStartUpAnimation();
        Scanner answer=new Scanner(System.in);
        System.out.println("Do you want to play against computer or CPU(player/CPU).");
		String versus=answer.nextLine();
		while (!versus.equals("player")&&!versus.equals("CPU"))
		{
			System.out.println("Wrong input. Please try again.");
			versus=answer.nextLine();
        }
        


//        System.out.println("Please enter the name for player 1: ");
//		String playerName1=answer.nextLine();
//		System.out.println("Please enter the name for player 2: ");
//		String playerName2=answer.nextLine();
//        System.out.println("\n");
        


        Player player1=new HumanPlayer();
        Player player2;
        if(versus.equals("CPU"))
		{
			player2=new ComputerPlayer();
		}
		if(versus.equals("player"))
		{
			player2=new HumanPlayer();
        }
        else
        {
//          This is needed so that the compiler realises that the field has been initialised succesfully.
//          Otherwise, it doesn't know that these are the only options available.
            player2=null;
        }
        System.out.println("Player1 Please enter your ships");
        player1.createBoard();
        System.out.println("Player2 please enter your ships");
        player2.createBoard();
    }




    public static void main(String[] args) {
        playGame();
    }
}