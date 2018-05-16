import java.util.Random;
class ComputerShipBoard extends ShipBoard{
    
	//Initialising the fields
	
    private Random rand = new Random();
	private String initialVertical;
	private int Vertical;
	private int initialHorizontalPosition;
	private int initialVerticalPosition;
	private String initialDirection;
	private int myInteger=-2;
	private int Coordinate;
	private int[][] ShipBoard=new int[10][10];
	private int[][] hitBoard=new int[10][10];
	private boolean isHit;
	private int[][] initialShipBoard=new int[10][10];
	private int ShipId;
    
    
	









	// chack if the strike hit any ship
	public boolean getStrike(int x, int y)
	{
		boolean isHit;
//		boolean checkSank=true;
		int hitId=ShipBoard[x-1][y-1];
		if (hitId==0)
		{
			isHit=false;			
		}
		else
		{
			isHit=true;
			ShipBoard[x-1][y-1]=0;
			// check if all places of ship are hit
			boolean checkSank=lastStrikeSankShip(x, y, hitId);			
		}
		
		
//		System.out.print("get strike checkSank = "+checkSank);


		this.isHit=isHit;
//		System.out.print("get strike isHit= "+isHit);
		return isHit;
	}






	//	check if the last strike sank the ship
	
	public boolean lastStrikeSankShip(int x, int y, int hitId)
	{
		boolean checkSank=true;
		// check if all places of ship are hit
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(ShipBoard[i][j]==hitId)
				{
					checkSank=false;
				}
			}
		}
		return checkSank;
	}




	// return the variable isHit	
	
	public boolean returnisHit()
	{
		return isHit;
	}




	// checkif all ships have been sank
	public boolean allShipsSank()
	{
		boolean AllSank=true;
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(ShipBoard[i][j]!=0)
				{
					AllSank=false;
				}
			}
		}
		return AllSank;
	}

	public int[][] returnBoard()
	{
		return ShipBoard;
	}

	//This is a method that can print any 10x10 board
    
    private void printBoard(int[][] ShipBoard)
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
    
    
    
    
    
    
    
    //This method checks if a string can be converted to int
    private int isInteger(String InputString)
	{
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
			}
		}
		return myInteger;
	}
	

    // this method checks if the whole ship is within the board.
    private void AutomaticBetweenLimits(int size,String InputString)
	{
		checkLimits(InputString);
		// the sum eith size with coordinates. It must not be greater than 10. If it is, it will be out of bounds.
		int myLimit= size+this.Coordinate-1;
		// check if the position and size are greater than 10.
		//If they are, that means that the entire ship fits the board.
		//Otherwise, it doesn't.
		while(myLimit>10)
		{
			System.out.println("Out of Limits.Please try again.");
			InputString=String.valueOf((rand.nextInt(10)+1));
			checkLimits(InputString);
			myLimit= size+this.Coordinate-1;
		}
    }
    

	// this method checks if the position the user typed in is within the limits of the board
    private int checkLimits(String InputString)
	{
		this.Coordinate=isInteger(InputString);// set the Coordinate var as the integer set.
		while(myInteger<1)
		{
			System.out.println("Out of limits. Please try again.");
			InputString=String.valueOf((rand.nextInt(10)+1));
			this.Coordinate=isInteger(InputString);
		}
		return this.Coordinate;
    }


    //--------------------------------------------------------------------------------------
	// Function enterShipAutomatically
	// Enter All The Ship Coordinates Automatically.
	//--------------------------------------------------------------------------------------	
	private void enterShipRandomly(int ShipId)
	{
		int size=0;
		int sizeX;
		int sizeY;
		int checkplace=0;
		if (ShipId<2)
		{
			size=ShipId+2;
		}
		if (ShipId>=2)
		{
			size=ShipId+1;
		}
		// Set the initial position. Whether it is horizontal or vertical.
		int  Direction = rand.nextInt(2);
		if (Direction==0)
		{
			this.initialDirection="V";
		}
		else
		{
			this.initialDirection="H";
		}
		if(this.initialDirection.equals("H"))
		{	
			sizeX=size;
			sizeY=0;
		}
		else
		{
			sizeX=0;
			sizeY=size;
		}
		// Check Horisontal Position
//***************
		String InputString =String.valueOf((rand.nextInt(10)+1));
		AutomaticBetweenLimits(sizeY,InputString);
		this.initialHorizontalPosition=myInteger;
//		System.out.println(HorizontalPosition);
		// Check Vertical Position
//***************
		InputString =String.valueOf((rand.nextInt(10)+1));
		AutomaticBetweenLimits(sizeX,InputString);
		this.initialVerticalPosition=myInteger;
//		System.out.println(VerticalPosition);
		// initialHorizontalPosition-1 (0<x<9   +sizeY-1 (contains x)
		if(this.initialDirection.equals("V"))
		{	
			//These lines of code add all the numbers of the board that are to be used up by the ship.
			//if the sum is greater than 0, then that means that one or more fields are different than 0,
			//which means that there is a ship over there.
			for (int i=initialHorizontalPosition-1;i<initialHorizontalPosition+sizeY-1;i++)
			{
				checkplace=checkplace+ShipBoard[i][initialVerticalPosition-1];
			}

			if(checkplace==0)
			{
				for (int i=initialHorizontalPosition-1;i<initialHorizontalPosition+sizeY-1;i++)
				{
					ShipBoard[i][initialVerticalPosition-1]=ShipId+1;
				}
			}
			else
			{
				System.out.print("Occupied position. Retry\n");
				this.ShipId--;
			}
		}
		else
		{
			// initialHorizontalPosition-1 (0<x<9   +sizeY-1 (contains x)
			//These lines of code add all the numbers of the board that are to be used up by the ship.
			//if the sum is greater than 0, then that means that one or more fields are different than 0,
			//which means that there is a ship over there.
			for (int i=initialVerticalPosition-1;i<initialVerticalPosition+sizeX-1;i++)
			{
				checkplace=checkplace+ShipBoard[initialHorizontalPosition-1][i];
			}

			if(checkplace==0)
			{
				for (int i=initialVerticalPosition-1;i<initialVerticalPosition+sizeX-1;i++)
				{
					ShipBoard[initialHorizontalPosition-1][i]=ShipId+1;
				
				}
			}
			else
			{
				System.out.print("Occupied position. Retry\n");
				this.ShipId--;
			}
		}
		printBoard(ShipBoard);
	}

    
	//I have to declare this method, as it has been declared as abstract in Shipboard and ComputerShipBoard extends ShipBoard.
    public void enterAllShips()
	{
		System.out.println("This is enterAllShips method");
		for(this.ShipId=0;this.ShipId<5;this.ShipId++)
		{
			System.out.println("Ship: "+(this.ShipId+1));
			enterShipRandomly(this.ShipId);
		}
		this.initialShipBoard=this.ShipBoard;
    }
    

    public static void main(String[] args) {
        ComputerShipBoard test =new ComputerShipBoard();
        test.enterAllShips();
    }
}