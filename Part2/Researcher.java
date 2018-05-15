import java.util.ArrayList;
class Researcher extends Entry{
    private String name;
    private ArrayList<Paper> paperList= new ArrayList<Paper>();
    public Researcher(String name){
        this.name=name;
    }
    public void addPaper(Paper newPaper){
        paperList.add(newPaper);
    }
    public String toString(){
        return name;
    }
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