package ch.heigvd;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wav {


    /**
     * Read data from a wav file
     * @param path source path
     * @return data
     */
    public static byte[] read(String path){
        DataInputStream file = null;
        byte[] data = null;
        try{
            file = new DataInputStream(Files.newInputStream(Paths.get(path)));

            // skip the majority of header (header total size = 44 bytes)
            file.skipBytes(40);
            // read the data size
            byte [] buffer = new byte[4];
            file.read(buffer);
            int dataSize = Utils.bytesToInt(buffer);

            // read data
            data = new byte[dataSize];
            file.read(data);
            file.close();

        } catch (Exception e) {
            System.out.print("ERROR : "+e+"\n");
        }


        return data;
    }

    /**
     * Write data in to a wav file (source : https://en.wikipedia.org/wiki/WAV)
     * @param path target path
     * @param data data
     */
    public static void write(String path, byte[] data){
        try{
            DataOutputStream outFile = new DataOutputStream(
                    Files.newOutputStream(
                            Paths.get(path+((path.endsWith(".wav"))?"":".wav"))
                    )
            );

            // write a default header

            outFile.write(Utils.bytesEndianConvertor(new byte[]{82, 73, 70, 70},false));    // write identifier "RIFF"
            outFile.write(Utils.bytesEndianConvertor(new byte[]{36, 83, 7, 0},false));      // write file size
            outFile.write(Utils.bytesEndianConvertor(new byte[]{87, 65, 86, 69},false));    // write file formatID

            outFile.write(Utils.bytesEndianConvertor(new byte[]{102, 109, 116, 32},false)); // write identifier "fmt_"
            outFile.write(Utils.bytesEndianConvertor(new byte[]{16, 0, 0, 0},false));       // write chunk size
            outFile.write(Utils.bytesEndianConvertor(new byte[]{1, 0},false));              // write audio format
            outFile.write(Utils.bytesEndianConvertor(new byte[]{1, 0},false));              // write number of channels
            outFile.write(Utils.bytesEndianConvertor(new byte[]{64, 31, 0, 0},false));      // write sample rate (hertz)
            outFile.write(Utils.bytesEndianConvertor(new byte[]{-128, 62, 0, 0},false));    // write number of bytes to read per second (Frequency)

            outFile.write(Utils.bytesEndianConvertor(new byte[]{2, 0},false));              // write number of bytes per block

            outFile.write(Utils.bytesEndianConvertor(new byte[]{16, 0},false));             // write number of bytes per sample

            outFile.write(Utils.bytesEndianConvertor(new byte[]{100, 97, 116, 97},false));  // write identifier "data"

            // write data length
            outFile.write(Utils.intToBytes(data.length));

            // write data
            outFile.write(data);

        } catch (Exception e) {
            System.out.print("ERROR : "+e+"\n");
        }
    }

}
