import java.util.Scanner;
class HumanShipBoard extends ShipBoard {
    // initialising the fields
    private Scanner keyboard = new Scanner(System.in);
    private int ShipId;
    private int[][] initialShipBoard=new int[10][10];
    private int[][] ShipBoard=new int[10][10];
    private String initialVertical;
	private int Vertical;
	private int initialHorizontalPosition;
	private int initialVerticalPosition;
	private String initialDirection;
	private int myInteger=-2;
	private int Coordinate;
    private boolean isHit;



    //--------------------------------------------------------------------------------------
	// Function isInteger
	// check if the input is integer
	//--------------------------------------------------------------------------------------	
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
                // if the input cannot be converted to integer...
				System.out.println("Wrong input. Please try again.");
				InputString=keyboard.nextLine();
			}
		}
		return myInteger;
	}
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------
	// Function checkLimits
	// check the limits (0<coordinates<10)
	//--------------------------------------------------------------------------------------	
	private int checkLimits(String InputString)
	{
		this.Coordinate=isInteger(InputString);// set the Coordinate var as the integer set.
        // my Integer is initialised to -2. So it is bellow 1.
        //If the input is correct, it must be greater than 0 in order for it to place the ship properly
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
	private void BetweenLimits(int size,String InputString)
	{
		checkLimits(InputString);
		// the sum eith size with coordinates. It must not be greater than 10. If it is, it will be out of bounds.
		int myLimit= size+this.Coordinate-1;
		// check if position and size is greater than 10, as this means that it is out of bounds.
		while(myLimit>10)
		{
            // if it is, it requests new input.
			System.out.println("Out of Limits.Please try again.");
			InputString=keyboard.nextLine();
			checkLimits(InputString);
			myLimit= size+this.Coordinate-1;
//			System.out.println("this.Coordinate in while = "+this.Coordinate);
//			System.out.println("My Limit in while = "+myLimit);
		}
	}




// this is a method created to print any 10x10 board.
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
    





    // I have to create a methon named enterAllShips, as it is an abstract method in ShipBoard.
    public void enterAllShips(){
        //Using a field in for loop is risky, and some programming languages do not allow that.
        //Fortunately, java is not one of them. However, it could be useful if used correctly.
        for(this.ShipId=0;this.ShipId<5;this.ShipId++)
            {
                System.out.println("Ship: "+(this.ShipId+1));
                enterShipManually(this.ShipId);
            }
            this.initialShipBoard=this.ShipBoard;
    }





    // 	check if the strike hit any ship
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

    public int[][] returnBoard()
	{
		return ShipBoard;
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



    private void enterShipManually(int ShipId)
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
        while(true)
        {
            System.out.println("Please enter the direction of the ship(V/H):");
            String initialDirection=keyboard.nextLine();
            if (initialDirection.equals("V") || initialDirection.equals("H"))
            {
                this.initialDirection=initialDirection;
                break;
            }
            else
            {
                System.out.println("Wrong direction. Please try again.");
            }
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
        System.out.println("Please enter the initial horizontal position of the ship:");
        String InputString=keyboard.nextLine();
//***************		
        BetweenLimits(sizeY,InputString);
        this.initialHorizontalPosition=myInteger;
        // Check Vertical Position
        System.out.println("Please enter the initial vertical position of the ship:");
        InputString=keyboard.nextLine();
    //***************			
        BetweenLimits(sizeX,InputString);
        this.initialVerticalPosition=myInteger;
        // initialHorizontalPosition-1 (0<x<9   +sizeY-1 (contains x)
        if(this.initialDirection.equals("V"))
        {
            // this is adding all the numbers in the board. If all the places are blank it should be 0.
            //Otherwise, it means that there is a ship there and it's id has been added to checkplace.
            //So the position is not free.
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
                // if the ship has not been placed on the board, it reduces the ShipId by 1, so that the next time it tries again.
                System.out.print("Occupied position. Retry\n");
                this.ShipId--;
            }
        }
        else
        {
            // initialHorizontalPosition-1 (0<x<9   +sizeY-1 (contains x)

            // this is adding all the numbers in the board. If all the places are blank it should be 0.
            //Otherwise, it means that there is a ship there and it's id has been added to checkplace.
            //So the position is not free.
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
                // if the ship has not been placed on the board, it reduces the ShipId by 1, so that the next time it tries again.
                System.out.print("Occupied position. Retry\n");
                this.ShipId--;
            }
        }
            printBoard(ShipBoard);
    }



    public static void main(String[] args) {
        HumanShipBoard test =new HumanShipBoard();
        test.enterAllShips();
    }
}