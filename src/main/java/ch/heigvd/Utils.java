package ch.heigvd;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils {

    public static int bytesToInt(byte[] data) {
        ByteBuffer wrapped= ByteBuffer.wrap(data);
        wrapped.order(ByteOrder.nativeOrder());
        return wrapped.getInt();
    }


    public static byte[] intToBytes(int value) {
        return bytesEndianConvertor(new byte[] {
                (byte)(value >> 24),
                (byte)(value >> 16),
                (byte)(value >> 8),
                (byte)value },ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN));
    }

    public static byte[] bytesEndianConvertor(byte[] data, boolean bigEndian){

        // if right format
        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) == bigEndian) {
            return data;
        } else {
            return reverseEndian(data);
        }
    }

    public static byte[] reverseEndian(byte[] data){
        byte[] newData = new byte[data.length];
        for (int i = 0; i<data.length-1; i++){
            newData[data.length-1-i]=data[i];
        }

        return newData;
    }
}
