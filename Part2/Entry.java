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
        String temp=text.toLowerCase();
        String[] StringArray=temp.split(" ");
        for (String s: StringArray){
            if (!tokenMap.containsKey(s)){
                tokenMap.put(s,1);
            }
            else{
                    tokenMap.put(s,tokenMap.get(s)+1);
                }
        }
    }


    public String[] getTokens(){
        //Set<String> KeysSetCollectior=tokenMap.keySet();
        String[] keys=new String[tokenMap.size()];
        int i=0;
        for(String s: tokenMap.keySet()){
            keys[i]=s;
            i++;
        }
        return keys;
    }
}