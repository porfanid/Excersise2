import java.io.*;
import sun.audio.*;

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
public class Music
{
  public void startmusic() 
  {
    // open the sound file as a Java input stream
    String musicFile = "file.pavlos";
    InputStream in=null;
    try{
        in= new FileInputStream(musicFile);
    }
    catch(Exception e) {}
    // create an audiostream from the inputstream
    AudioStream audioStream=null;
    try{
        audioStream = new AudioStream(in);
    }
    catch(Exception e) {}
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
}