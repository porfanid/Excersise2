/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.ArrayList;
/*
 * This Class represents the researcher.
 */


class Researcher extends Entry{


    private String name;

    private ArrayList<Paper> paperList= new ArrayList<Paper>();
    // initialising the constructor.
    public Researcher(String name,String text){
        super(text);
        this.name=name;
    }
    // adding a new paper to the researchers collection.
    public void addPaper(Paper newPaper){
        paperList.add(newPaper);
    }
    // returning the name
    public String toString(){
        return name;
    }
    // displaying information about the researcher.
    public void display(){
        String data="Researcher: "+toString()+"\nPapers:\n";
        for (Paper i: paperList){
            data+="\t"+i.toString();
        }
        System.out.println(data);
    }

    public static void main(String[] args) {
        Researcher test=new  Researcher("Paul");
        test.display();
    }
}