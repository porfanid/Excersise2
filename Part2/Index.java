/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
class Index{
    private HashMap<String,HashSet> index =new HashMap<String,HashSet>();


    public void indexDB(Database newEntry){
        HashMap<Entry, String> index=newEntry.getIndex();
        for(Entry e:index.keySet()){
            HashSet keyList=e.getKeys();
            String value=index.get(e);
            this.index.put(value,keyList);
        }
    }


    public void printIndex(){
        System.out.println(index);
    }



    public String[] retreive(String query){
        HashSet<String> result=new HashSet<String>();
        for (String s: index.keySet()){
            if(s.toLowerCase().contains(query.toLowerCase())){
                result.add(s);
            }
        }
        return result.toArray();
    }


    public static void main(String[] args) {
        Database test=new Database();
        test.createDB("toy.txt");
        Index newTest=new Index();
        newTest.indexDB(test);
        newTest.printIndex();
    }
}