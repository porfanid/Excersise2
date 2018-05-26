import java.util.Scanner;
class QueryProcessor
{
    Index newIndex;
    public QueryProcessor(Index newIndex){
        this.newIndex=newIndex;
    }

    public void newQuery(){
        Scanner keyboard=new Scanner(System.in);
        System.out.println("Search: ");
        String query=keyboard.nextLine();
        keyboard.close();
    }
}