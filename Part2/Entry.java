/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.util.HashMap;
import java.util.HashSet;
abstract/**
* Class Entry
*/
class Entry{

    private HashMap<String,Integer> tokenMap=new HashMap<String,Integer>();
    
    
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


    public HashSet<String> getKeys(){
        return new HashSet<String>(tokenMap.keySet());
    }


    public int computeScore(String query){
//      "\\W+" is used to include any other separators between words (like commas and semicolons) while spliting the sentence.  
        String[] StringArray=temp.split("\\W+");
        int  score=0;
        for(int i=0;i<StringArray.length;i++){
            for(int j=0;j<StringArray.length;j++){
                if(i!=j && StringArray[i].equals(StringArray[j])){
                    score++;
                }
            }
        }
        return score;
    }
}