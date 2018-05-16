import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.io.File;
class test
{
    public static void downloadLink()
    {
        File f = new File("javax.mail.jar");
        if(f.exists() && !f.isDirectory())
        { 
            System.out.println("The file already exists");
            return;
        }
        try
        {
            String urlstring="https://github.com/javaee/javamail/releases/download/JAVAMAIL-1_6_1/javax.mail.jar";
            URL website = new URL(urlstring);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("javax.mail.jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
        catch(Exception e)
        {

        }
        System.out.println("The file has been created");
    }






    public static void main(String[] args)
    {
        downloadLink();
    //    copyFile();
    }
}