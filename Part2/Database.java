/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

class Database
{
    //initialising the fields

    private HashMap<Entry, String> researchers = new HashMap<Entry, String>();
//    HashMap<Conference, String> conferences = new HashMap<Conference, String>();
    
    
    private ArrayList<Entry> objects=new ArrayList<Entry>();


    //Reading the info from file.
    public void createDBFromTextFile(File database) {
        //initialising the Scanner. Here, there is a FileNotFoundException, which must be caught. This is possible by using try catch.
        Scanner input;
        try{
            //creating a Scanner that reads data from file with name filename.
            //Importing java.io.File is important for this step to work.
            input = new Scanner (database);
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
            researchers.put(new Conference(conference),conference);
            //System.out.println(input.hasNextLine());
        }
    }
    
    public void createDB(String filename){
//      testing if the file provided is a text file
        Scanner keyboard=new Scanner(System.in);
        File database=new File(filename);
        boolean isText;
        try{
            isText=FileEncoding.contentIsText(database,true);
        }catch(Exception e){
            isText=false;
        }



        while(!isText){
            System.out.println("This file is binary. Please try again.");
            System.out.println("Please enter the name of the file: ");
            filename=keyboard.nextLine();
            database=new File(filename);
            try{
                isText=FileEncoding.contentIsText(database,true);
            }catch(Exception e){
                isText=false;
            }
        }

//      Now that I am sure that it is a text file, I will call the method createDBFromTexxtFile.
        createDBFromTextFile(database);
    }



    public void printDB(){
        for (Entry i : objects){
            try{
                i.display();
            }
            catch(Exception e)
            {
                System.out.println("Something went wrong.");
            }
        }
    }

    public HashMap<Entry, String> getIndex(){
        return researchers;
    }


    public static void main(String[] args) {
        Database test=new Database();
        test.createDB("toy.txt");
        test.printDB();
    }
}