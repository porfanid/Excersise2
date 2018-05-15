/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/

/*
This class is created so that I can print some thing slowly.
*/


class SlowPrint
{
	public void Print(String message) throws InterruptedException
	{
        // Get message, convert to char array
        char[] chars = message.toCharArray();

        // Print a char from the array, then sleep for 1/10 second
        for (int i = 0; i < chars.length; i++)
		{
            System.out.print(chars[i]);
            Thread.sleep(100);
        }
		System.out.print("\n");
        // Repeat for all chars
    }
}