import java.util.ArrayList;
class Conference extends Entry{
    String name;
    private ArrayList<Paper> paperList= new ArrayList<Paper>();


    public Conference(String name){
        this.name=name;
    }

    public void addPaper(Paper newPaper){
        paperList.add(newPaper);
    }

    public String toString(){
        return name;
    }

    public void display(){
        String data="Conference: "+toString()+"\nPapers:\n";
        for (Paper i: paperList){
            data+="\t"+i.toString();
        }
        System.out.println(data);
    }

    public static void main(String[] args) {
        Conference test=new  Conference("Paul");
        test.display();
    }
}