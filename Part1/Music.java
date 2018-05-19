/*
NAME: PAVLOS ORFANIDIS
AM: 4134
*/
import java.io.*;
import sun.audio.*;
import java.net.*;

public class Music
{
  public void startmusic() 
  {
    // open the sound file as a Java input stream
    //String musicFile = "file.pavlos";
    InputStream in=null;
    try{
        //in= new FileInputStream(musicFile);
        URL musicURL = new URL("http://cse.uoi.gr/~cse74134/app_resources/file.pavlos");
        //InputStream temp=new InputStream(musicURL);
        in=musicURL.openStream();
    }
    catch(Exception e) {System.out.println("Cannot Play open stream now");}
    // create an audiostream from the inputstream
    AudioStream audioStream=null;
    try{
        audioStream = new AudioStream(in);
    }
    catch(Exception e) {System.out.println("Cannot view source");}
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
    try{
      Thread.sleep(3000);
    }
    catch(Exception e){
      
    }
  }
  
}