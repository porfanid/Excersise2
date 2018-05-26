/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.ArrayList;
class Conference extends Entry{
    // initialise the fields
    private String name;
    private ArrayList<Paper> paperList= new ArrayList<Paper>();

// initialise the constructor
    public Conference(String name,String text){
        super(text);
        this.name=name;
    }
    public Conference(String name){
        this.name=name;
    }

//  add a paper to the ArrayList
    public void addPaper(Paper newPaper){
        paperList.add(newPaper);
    }

//  return the name of the conference
    public String toString(){
        return name;
    }

//  display conference info
    public void display(){
        String data="Conference: "+toString()+"\nPapers:\n";
        for (Paper i: paperList){
            data+="\t"+i.toString();
        }
        System.out.println(data);
    }

    
    public int computeScore(String query){
        int score=super.computeScore(query);
        if(query.toLowerCase().equals(name.toLowerCase())){
            score+=100;
        }
        return score;
    }



    public static void main(String[] args) {
        Conference test=new  Conference("Paul");
        test.display();
    }
}