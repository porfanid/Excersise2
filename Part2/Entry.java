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
    
    
    public Entry(String text){
        addText(text);
    }

    public Entry(){
    }

    
    abstract void display();

    public void addText(String text){
        String temp=text.toLowerCase();
//      "\\W+" is used to include any other separators between words (like commas and semicolons) while spliting the sentence.  
        String[] StringArray=temp.split("\\W+");
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