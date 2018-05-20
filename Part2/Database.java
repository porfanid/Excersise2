/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.smartcardio.CardPermission;

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
        Scanner answers=new Scanner(System.in);
        Connection databaseConnection = null;
        //Statement stmt = null;
        try {
            PrintWriter FileData = new PrintWriter("temp");

            Class.forName("org.sqlite.JDBC");
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:"+database.getName());
            databaseConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");


/*            DatabaseMetaData md = databaseConnection.getMetaData();
            ResultSet rstable = md.getTables(null, null, "%", null);
            //rstable.open();
            int i=-1;
            while (rstable.next()) {
                i++;
                //System.out.println(rs.getString(3));
            }
            String[] tableList=new String[i];
            for(int j=0;j<i;j++){
                tableList[i]=rstable.getString(3);
            }
*/

            String desiredTable=null;



//          if the database has only one table, use this one.
            if(1==1){
                desiredTable="cards";
            }
//          Otherwise, let the user choose.
            else{
                desiredTable="cards";
/*
                System.out.println("Please enter the number for the desired name you want to extract the data. Please keep in mind that the tables must use the following format. All caps, all small, or only the first one Capital. All the Collumns must use the same format.");
                for (int j=0;j<tableList.length;j++){
                    System.out.println("("+j+")"+" : "+tableList[j]);
                }
                int tableNO;
                while(true){
                    String tableNoAsString=answers.nextLine();
                    try{
                        tableNO=Integer.parseInt(tableNoAsString);
                        if(tableNO<tableList.length||tableNO>0){
                            break;
                        }
                        System.out.println("Wrong input. Please try again.");
                    }
                    catch(Exception e){
                        System.out.println("Wrong input. Please try again.");
                    }
                }
*/
            }



            ResultSet rs = databaseConnection.createStatement().executeQuery( "SELECT * FROM "+desiredTable+";" );
            //rs.open();
            try{
                while ( rs.next() ) {
                int year = rs.getInt("year");
                String  title = rs.getString("title");
                String  authors = rs.getString("authors");
                String conference = rs.getString("conference");
                
                FileData.println(title);
                FileData.println(authors);
                FileData.println(conference);
                FileData.println(year);
                }
            }
            catch(Exception e){}


            try{
                while ( rs.next() ) {
                    int year = rs.getInt("Year");
                    String  title = rs.getString("Title");
                    String  authors = rs.getString("Authors");
                    String conference = rs.getString("Conference");
                    
                    FileData.println(title);
                    FileData.println(authors);
                    FileData.println(conference);
                    FileData.println(year);
                }
            }
            catch(Exception e) {}


            try{
                while ( rs.next() ) {
                    int year = rs.getInt("YEAR");
                    String  title = rs.getString("TITLE");
                    String  authors = rs.getString("AUTHORS");
                    String conference = rs.getString("CONFERENCE");
                    
                    FileData.println(title);
                    FileData.println(authors);
                    FileData.println(conference);
                    FileData.println(year);
                }
            }
            catch(Exception e) {}





            FileData.close ();
            rs.close();
            databaseConnection.close();
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }



         
         File temp =new File("temp");
         System.out.println(temp.exists());
         createDBFromTextFile(temp);
         Files.delete(temp);

         System.out.println(temp.delete());
         
         //temp.delete();
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
            createdbFromSQLite(database);
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