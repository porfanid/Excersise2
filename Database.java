import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
class Database{
    HashMap<Researcher, String> researchers = new HashMap<Researcher, String>();
    HashMap<Conference, String> conferences = new HashMap<Conference, String>();
    ArrayList<Entry> objects=new ArrayList<Entry>();
    



    public void createDB(String filename) {
        Scanner input;
        try{
            input = new Scanner (new File(filename));
        }
        catch(Exception e){
            input=null;
        }
        while (input.hasNextLine()){
            String title=input.nextLine();
            String writersAsString=input.nextLine();
            String conference=input.nextLine();
            String yearAsString=input.nextLine();
            int year=Integer.parseInt(yearAsString);
            
            Paper newPaper=new Paper(title,year);
            objects.add(newPaper);
            String[] writers=writersAsString.split(",");
            for (String writer:writers){
                objects.add(new Researcher(writer));
                researchers.put(new Researcher(writer),writer);
            }
            objects.add(new Conference(conference));
            conferences.put(new Conference(conference),conference);
            //System.out.println(input.hasNextLine());
        }
    }


    public void printDB(){
        for (Entry i : objects){
            System.out.println("Hello");
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