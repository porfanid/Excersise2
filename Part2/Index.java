import java.util.HashMap;
import java.util.Scanner;

class Index{
    HashMap<String,Entry> index =new HashMap<String,Entry>();
    public void indexDB(Database newEntry){
        //initialising the Scanner. Here, there is a FileNotFoundException, which must be caught. This is possible by using try catch.
        Scanner input;
        try{
            //creating a Scanner that reads data from file with name filename.
            //Importing java.io.File is important for this step to work.
            input = new Scanner (new File(filename));
        }
        catch(Exception e){
            // I must declare a null Scanner. Otherwise it returns an error saying that input might not have been initialised.
            input=null;
        }
        // while there are lines in the textfile...
        while (input.hasNextLine()){
            // reading the necessary values

            //the title
            String title=input.nextLine();
            // the writers
            String writersAsString=input.nextLine();
            //the conference
            String conference=input.nextLine();
            //the year
            String yearAsString=input.nextLine();

            //make year from String to int
            int year=Integer.parseInt(yearAsString);
    }
}