/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.ArrayList;

class Paper extends Entry{


    private String title;
    private int year;
    private ArrayList<Researcher> authorList= new ArrayList<Researcher>();
    private Conference conf;

/*
* There are many constructors available so that it doesn't matter in which order you write the title, year, Conference.
*/
//----------------------------------------------------------------------------------------------------
    public Paper(String title, int year, Conference conf,String text){
        super(text);
        this.title=title;
        this.year=year;
        this.conf=conf;
    }
    

    public Paper(int year, String title, Conference conf,String text){
        this(title,year,conf);
    }

    public Paper(String title,Conference conf, int year,String text){
        this(title,year,conf);
    }

    public Paper(int year,Conference conf, String title,String text){
        this(title,year,conf);
    }

    public Paper(Conference conf, int year, String title,String text){
        this(title,year,conf);
    }

    public Paper(Conference conf, String title, int year,String text){
        this(title,year,conf);
    }

    public Paper(int year, String title, String conf,String text){
        this(title,year,new Conference(conf));
    }

    public Paper(String title, int year, String conf,String text){
        this(title,year,new Conference(conf));
    }

    public Paper(String title, String conf, int year,String text){
        this(title,year,new Conference(conf));
    }

// end of the instructors :)
//----------------------------------------------------------------------------------------------
    public void addAuthor(Researcher newAuthor){
        authorList.add(newAuthor);
    }


/*
* I found this online. It is being used to remove the last comma from the toString method.
*/
    public String removeLastChar(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }


/*
* Display class: exactly as asked.
*/
    public void display(){
        //the title and year...
        String data="Title: "+title+"\nyear: "+year+"\nResearchers: ";
        // adding all the researchers...
        for (Researcher i: authorList){
            System.out.println("hi");
            data+=i.toString()+",";
        }
//      removing the last comma
        data=removeLastChar(data);
        // adding the Conference details.
        data+="\nConference: "+conf.toString();
//      printing the data.
        System.out.println(data);
    }




/*
* toString class: exactly as asked.
*/
    public String toString(){
        // adding the title, a dot and a space...
        String data=title+". "+" ";
        // adding all the researchers
        for (Researcher i: authorList){
            data+=i.toString()+",";
        }
        
//      adding the conference and the year.
        data+="Conference: "+conf.toString()+year;
        return data;
    }






    public static void main(String[] args) {
        Paper test =new Paper("paul", 1999, new Conference("Hello World"));
        test.display();
    }
}