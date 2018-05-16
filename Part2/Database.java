/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
class Database{
    //initialising the fields

    HashMap<Researcher, String> researchers = new HashMap<Researcher, String>();
    HashMap<Conference, String> conferences = new HashMap<Conference, String>();
    ArrayList<Entry> objects=new ArrayList<Entry>();
    
    //Reading the info from file.
    public void createDB(String filename) {
        //initialising the Scanner. Here, there is a FileNotFoundException, which must be caught. This is possible by using try catch.
        Scanner input;
        try{
            //creating a Scanner that reads data from file with name filename.
            //Importing java.io.File is important for this step to work.
            input = new Scanner (new File(filename));
        }
        catch(Exception e){
            // I must declare a null Scanner. Otherwise it returns an error saqying that input might not have been initialised.
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
            
            // creating the paper
            Paper newPaper=new Paper(title,year,new Conference(conference));
            
            //adding the paper to ArrayList objects.
            objects.add(newPaper);

            //splitting the writers according to comma
            String[] writers=writersAsString.split(",");

            //Creating a Researcher for every writer available and add them to ArrayList objects.
            for (String writer:writers){
                objects.add(new Researcher(writer));
                researchers.put(new Researcher(writer),writer);
            }

            //creating new conference and adding it to hashmap
            objects.add(new Conference(conference));
            conferences.put(new Conference(conference),conference);
            //System.out.println(input.hasNextLine());
        }
    }


    public void printDB(){
        for (Entry i : objects){
            try{
                i.display();
            }
            catch(Exception e)
            {
                System.out.println("Doesn't work");
            }
        }
    }


    public static void main(String[] args) {
        Database test=new Database();
        test.createDB("toy.txt");
        test.printDB();
    }
}