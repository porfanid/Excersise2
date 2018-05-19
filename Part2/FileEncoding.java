import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/** 
 * File encoding has a good understanding of how files are put together and is therefore 
 * in the pound seat to answer the often asked question, namely, is this file encoded in 
 * binary or in plain ASCII text. 
 */ 
public enum FileEncoding { 
    /** 
     * Use this handle to grab the sole instance of this FileEncoding class. 
     */ 
    SINGLE_INSTANCE; 
    public boolean contentIsText(final File file, boolean acceptUTF8encoding) throws IOException 
    { 
        final int BUFFER_SIZE = 10*1024; 
        boolean isText = true; 
        byte[] buffer = new byte[BUFFER_SIZE]; 
 
        final RandomAccessFile fis = new RandomAccessFile(file, "r"); 
        try 
        { 
            fis.seek(0); 
            final int read = fis.read(buffer); 
            int lastByteTranslated = 0; 
            for (int i = 0; i < read && isText; i++) 
            { 
                final byte singleByte = buffer[i]; 
                int unsignedByte = singleByte & (0xff);  // unsigned 
                int utf8value = lastByteTranslated + unsignedByte; 
                lastByteTranslated = (unsignedByte) << 8; 
 
                isText = isCharacterTextOrBinary( acceptUTF8encoding, unsignedByte, utf8value ); 
            } 
        } 
        finally { 
            try { 
                fis.close(); 
            } 
            catch (final Throwable th) 
            { 
                System.out.println( 
                        "Failure closing the file input stream or logging the file absolute path." + "\n" + 
                                th.toString() + "\n" 
                ); 
            } 
        } 
        return isText; 
    } 
 
    private boolean isCharacterTextOrBinary ( boolean acceptUTF8encoding, int unsignedByte, int utf8value ) { 
 
        final int ASCII_TEXT_SYMBOLS_LOWER_BOUND = 0x20; 
        final int ASCII_TEXT_SYMBOLS_UPPER_BOUND = 0x7E; 
 
        final int LATIN_CHARSET_LOWER_BOUND = 0xA0; 
        final int LATIN_CHARSET_UPPER_BOUND = 0xEE; 
 
        final int LATIN_IN_UTF_8_LOWER_BOUND = 0x2E2E; 
        final int LATIN_IN_UTF_8_UPPER_BOUND = 0xC3BF; 
 
        final int CARRIAGE_RETURN_CHARACTER = 0x0D; 
        final int TAB_CHARACTER = 0x09; 
        final int LINE_FEED_CHARACTER = 0x0A; 
        final int FORM_FEED_CHARACTER = 0x0C; 
         
        return 
                unsignedByte == TAB_CHARACTER 
                        || unsignedByte == LINE_FEED_CHARACTER 
                        || unsignedByte == FORM_FEED_CHARACTER 
                        || unsignedByte == CARRIAGE_RETURN_CHARACTER 
                        || (unsignedByte >= ASCII_TEXT_SYMBOLS_LOWER_BOUND && unsignedByte <= ASCII_TEXT_SYMBOLS_UPPER_BOUND) 
                        || (unsignedByte >= LATIN_CHARSET_LOWER_BOUND && unsignedByte <= LATIN_CHARSET_UPPER_BOUND) 
                        || (acceptUTF8encoding && (utf8value >= LATIN_IN_UTF_8_LOWER_BOUND && utf8value <= LATIN_IN_UTF_8_UPPER_BOUND)) 
                ; 
    } 
} 