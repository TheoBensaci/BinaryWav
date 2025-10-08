package ch.heigvd;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wav {


    public static byte[] read(String path){
        DataInputStream file = null;
        byte[] data = null;
        try{
            file = new DataInputStream(Files.newInputStream(Paths.get(path)));
            file.skipBytes(40);
            byte [] buffer = new byte[4];
            file.read(buffer);
            int dataSize = Utils.bytesToInt(buffer);

            data = new byte[dataSize];
            file.read(data);

            System.out.print("Size : "+data.length+" | "+dataSize);
            file.close();
        } catch (Exception e) {
            System.out.print("ERROR : "+e+"\n");
        }


        return data;
    }

    public static void write(String path, byte[] data){
        try{
            DataOutputStream outFile = new DataOutputStream(Files.newOutputStream(Paths.get(path+".wav")));

            // write a default header

            outFile.write(Utils.bytesEndianConvertor(new byte[]{82, 73, 70, 70},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{36, 83, 7, 0},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{87, 65, 86, 69},false));

            outFile.write(Utils.bytesEndianConvertor(new byte[]{102, 109, 116, 32},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{16, 0, 0, 0},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{1, 0},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{1, 0},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{64, 31, 0, 0},false));
            outFile.write(Utils.bytesEndianConvertor(new byte[]{-128, 62, 0, 0},false));

            outFile.write(Utils.bytesEndianConvertor(new byte[]{2, 0},false));

            outFile.write(Utils.bytesEndianConvertor(new byte[]{16, 0},false));

            outFile.write(Utils.bytesEndianConvertor(new byte[]{100, 97, 116, 97},false));

            // add the data
            outFile.write(Utils.intToBytes(data.length));
            outFile.write(data);

        } catch (Exception e) {
            System.out.print("ERROR : "+e+"\n");
        }
    }

}
