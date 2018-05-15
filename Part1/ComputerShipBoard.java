import java.util.Random;
class ComputerShipBoard extends ShipBoard{
    
    
    private Random rand = new Random();
//	private Scanner keyboard = new Scanner(System.in);
//	private int[] ShipSizes={2,3,4,5};
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
    
    private void AutomaticBetweenLimits(int size,String InputString)
	{
		checkLimits(InputString);
		// the sum eith size with coordinates. It must not be greater than 10. If it is, it will be out of bounds.
		int myLimit= size+this.Coordinate-1;
		// check if size is greater than 10.
		while(myLimit>10)
		{
			System.out.println("Out of Limits.Please try again.");
			InputString=String.valueOf((rand.nextInt(10)+1));
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
		// Check the initial position. Whether it is horizontal or vertical.
		//System.out.println("Please enter the direction of the ship(V/H):");
		//String initialDirection=keyboard.nextLine();
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

    

    public void enterAllShips()
	{
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