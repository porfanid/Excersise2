import java.util.Random;
class ComputerPlayer extends Player{


    private Random rand = new Random();
	private String playerName;
//	private Scanner keyboard=new Scanner(System.in);
	private int[][] ShipBoardP1;
	private int myInteger;
	private int Coordinate;
	private int[][] strikeBoard=new int[10][10];


    public ComputerPlayer(){

    }


    public void createBoard() {
        ComputerShipBoard Board=new ComputerShipBoard();
        Board.enterAllShips();
    }


    public int[] nextStrike()
	{
		boolean checkHit=true;
		String ReadLine;
		int[] coordinates=new int[2];
		while(checkHit)
		{
//			System.out.println("Please enter the horizontal position to strike:");
			ReadLine=String.valueOf((rand.nextInt(10)+1));
			int Xcoord=BetweenLimits(ReadLine);
//			System.out.println("Please enter the vertical position to strike:");
			ReadLine=String.valueOf((rand.nextInt(10)+1));
			int Ycoord=BetweenLimits(ReadLine);
			//int[] coordinates={Xcoord,Ycoord};
			coordinates [0]=Xcoord;
			coordinates [1]=Ycoord;
			//check if posision has been stoke previously
			if(strikeBoard[coordinates[0]-1][coordinates[1]-1]==0)
			{
				checkHit=false;
			}
			else
			{
				System.out.println("The posision has been stoke previously. Please try again.");
			}
		}
		return coordinates;
	}




    private int isInteger(String InputString)
	{
//		System.out.println("a1 "+  " - " + InputString);
		// Check if "InputString" is text then isInteger=0
		//while(myInteger!=-1253)
		while(true)	
		{
			try
			{
				int isInteger= Integer.parseInt(InputString);
				// make isInteger public var. When using try catch, the var used has to be public.
				//Otherwise, it is treated as a local var and thus cannot be used outside of the current block.
				myInteger=isInteger;
				break;
			}
			catch (Exception e)
			{
				System.out.println("Wrong input. Please try again.");
				InputString=String.valueOf((rand.nextInt(10)+1));
//				System.out.println("a3 "+  " - " + InputString);
			}
		}
		return myInteger;
	}
	
	
	
	
	
	private int checkLimits(String InputString)
	{
		this.Coordinate=isInteger(InputString);// set the Coordinate var as the integer set.
		while(myInteger<1)
		{
//			System.out.println("Out of limits. Please try again.");
			InputString=String.valueOf((rand.nextInt(10)+1));
			this.Coordinate=isInteger(InputString);;
		}
		return this.Coordinate;
	}
	
	
	//--------------------------------------------------------------------------------------
    // Function BetweenLimits
	// check if the input with the size of the ship with the coordinates are greater than 10. 
	//--------------------------------------------------------------------------------------
	
	
	
	
	private int BetweenLimits(String InputString)
	{
		checkLimits(InputString);
		// the sum eith size with coordinates. It must not be greater than 10. If it is, it will be out of bounds.
		int myLimit= this.Coordinate;
		// check if size is greater than 10.
		while(myLimit>10)
		{
			System.out.println("Out of Limits.Please try again.");
			InputString=String.valueOf((rand.nextInt(10)+1));
			checkLimits(InputString);
			myLimit= this.Coordinate;
		}
		return myLimit;
	}

}