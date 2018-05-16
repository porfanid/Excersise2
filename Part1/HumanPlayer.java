import java.util.Scanner;
class HumanPlayer extends Player{

//	initialising the fields
	private String playerName;
	private Scanner keyboard=new Scanner(System.in);
	private int[][] ShipBoardP1;
	private int myInteger;
	private int Coordinate;
	private int[][] strikeBoard=new int[10][10];
	

	// initialising the constructor.
    public HumanPlayer(String playerName){
		super();
		this.playerName=playerName;
	}

	// return the players name	
	public String toString()
	{
		return playerName;
	}
	

	public int[][] returnBoard()
	{
		
		return ShipBoardP1;
	}


    public int[][] returnStrikeBoard()
	{
		return strikeBoard;
    }



//	This method is being used to create the board.
    public void createBoard() {
		//System.out.println("This is createBoard method in HumanPlayer.");
		HumanShipBoard Board=new HumanShipBoard();
		Board.enterAllShips();
		ShipBoardP1=Board.returnBoard();
	}
	

	// update the strikeboard with the hit	
	public int[][] update(int[] coordinates, boolean isHit)
	{
//		System.out.println("Update Function: ");
//		System.out.println("coords are: " +coordinates[0]+" , "+coordinates[1]);
//		System.out.println("isHit= "+ isHit);
		if(isHit)
		{
			strikeBoard[coordinates[0]-1][coordinates[1]-1]=1;
		}
		else
		{
			strikeBoard[coordinates[0]-1][coordinates[1]-1]=-1;
		}
		return strikeBoard;
	}

	


// this method enables the player to achieve the next strike.
    public int[] nextStrike() {

		boolean checkHit=true;
		String ReadLine;
		int[] coordinates=new int[2];
//******************************************************************************************
		// This part of the code checks if the position has been stroke previously. 
		while(checkHit)
		{
			System.out.println("Please enter the horizontal position to strike:");
			ReadLine=keyboard.nextLine();
			int Xcoord=BetweenLimits(ReadLine);
			System.out.println("Please enter the vertical position to strike:");
			ReadLine=keyboard.nextLine();
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
				System.out.println("The posision has been stroke previously. Please try again.");
			}
		}
//*****************************************************************************************************
//		if it hasn't it returns an int[] with the coordinates of the next strike.
		return coordinates;
	}
	



//	This method checks if a string can be converted to int
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
				InputString=keyboard.nextLine();
//				System.out.println("a3 "+  " - " + InputString);
			}
		}
		return myInteger;
	}
	
	//--------------------------------------------------------------------------------------
    // Function BetweenLimits
	// check if the input with the size of the ship with the coordinates are greater than 10. 
	//--------------------------------------------------------------------------------------
	private void BetweenLimits(int size,String InputString)
	{
		checkLimits(InputString);
		// the sum of size with coordinates. It must not be greater than 10. If it is, it will be out of bounds.
		int myLimit= size+this.Coordinate-1;
		// check if size is greater than 10. If it is, the ship is out of the bounds of the board.
		while(myLimit>10)
		{
			System.out.println("Out of Limits.Please try again.");
			InputString=keyboard.nextLine();
			checkLimits(InputString);
			myLimit= size+this.Coordinate-1;
		}
	}
	
	
	
	
	private int checkLimits(String InputString)
	{
		this.Coordinate=isInteger(InputString);// set the Coordinate var as the integer set.
		while(myInteger<1)
		{
			System.out.println("Out of limits. Please try again.");
			InputString=keyboard.nextLine();
			this.Coordinate=isInteger(InputString);
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
			InputString=keyboard.nextLine();
			checkLimits(InputString);
			myLimit= this.Coordinate;
		}
		return myLimit;
	}
}