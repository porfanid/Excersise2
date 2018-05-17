import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.io.File;
class DownloadFile
{
    private static String fileName="file.pavlos";

    public static void downloadLink()
    {
        try
        {
            String urlstring="https://1drv.ms/u/s!AsLE0SvRzwkVgd1933rnyQlKik9tMw";
            URL website = new URL(urlstring);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
        catch(Exception e)
        {

        }
        System.out.println("The file has been created");
    }






    public static void showProcedure(){
        File f = new File(fileName);
        //System.out.println("f.exists: "+f.exists());
        //System.out.println("f.isDirectory: "+f.isDirectory());
        try{
            Thread.sleep(10000);
        }
        catch(Exception e){}
        
        if(f.exists() || f.isDirectory())
        { 
            System.out.println("The file already exists");
            return;
        }

        Thread Download = new Thread(){
            public void run(){
                downloadLink();
                return;
            }
        };


        Download.start();
        SlowPrint Slow=new SlowPrint();
        Slow.Print("Downloading resources");
        while(Download.isAlive()){
            System.out.print(".");
            try{
                Thread.sleep(500);
            }
            catch(Exception e){}
        }
    }
}