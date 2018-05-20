/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import java.sql.*;

class Database{
    //initialising the fields

    HashMap<Researcher, String> researchers = new HashMap<Researcher, String>();
    HashMap<Conference, String> conferences = new HashMap<Conference, String>();
    ArrayList<Entry> objects=new ArrayList<Entry>();
    //File database=new File(filename);
    
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
            conferences.put(new Conference(conference),conference);
            //System.out.println(input.hasNextLine());
        }
    }

    public void createdbFromSQLite(File database){
        
        Connection c = null;
        Statement stmt = null;
        try {
            PrintWriter FileData = new PrintWriter("temp");

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+database.getName());
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
      
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            
            while ( rs.next() ) {
               int year = rs.getInt("year");
               String  title = rs.getString("title");
               String  authors = rs.getString("authors");
               String conference = rs.getString("Conference");
               
               FileData.println(title);
               FileData.println(authors);
               FileData.println(conference);
               FileData.println(year);
            }
            FileData.close ();
            rs.close();
            stmt.close();
            c.close();
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
    }


    public void createDB(String filename){
        Scanner keyboard=new Scanner(System.in);
        File database=new File(filename);
        boolean isText;
        try{
            isText=FileEncoding.contentIsText(database,true);
        }catch(Exception e){
            isText=false;
        }
        //FileEncoding getEncoding=null;
        //getEncoding=new FileEncoding();
        //System.out.println("isText: "+isText);
        if(isText){
            //System.out.println("It's text");
            createDBFromTextFile(database);
        }
        if(!isText){
            //System.out.println("It's binary");
            createdbFromSQLite(filename);
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
        test.createDB("database.ycd");
        test.printDB();
    }
}