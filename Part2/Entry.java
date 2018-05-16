/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
abstract/**
* Class Entry
*/
class Entry{
    HashMap<String,Integer> tokenMap=new HashMap<String,Integer>();
    abstract void display();

    public void addText(String text){
        String temp=text.lowerCase();
        String[] StringArray=temp.split(" ");
        for (String s: StringArray){
            if (!tokenMap.containsKey(s)){
                tokenMap.put(s,1);
            }
            else{
                    tokenMap.put(word,myMap.get(s)+1);
                }
        }
    }


    public String[] getTokens(){
        return tokenMap.keySet();
    }
}