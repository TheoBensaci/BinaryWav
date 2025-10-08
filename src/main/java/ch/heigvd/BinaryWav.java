/**
 * Auteur : Theo Bensaci, Gasmin Yasser
 * Date : 06.10.2025
 * Description : Define all the command of BinaryWave
 * Source : https://picocli.info/quick-guide.html
 */
package ch.heigvd;


import picocli.CommandLine;

import java.nio.ByteOrder;
/* TODO : make commands

Encrypt <filePath> <outputFilePath>

Decrypt <filePath> <outputFilePath>


TODO : Read all the bytes of a file


TODO : Encrypte methods oder then just throwing all the bytes ?

*/

@CommandLine.Command(name = "BinaryWav", version = "BinaryWav 1.0", mixinStandardHelpOptions = true) // |1|
public class BinaryWav implements Runnable{
    public static void main(String[] args) {
        int exitCode = new CommandLine(new BinaryWav()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        //System.out.print(Utils.bytesToInt(new byte[]{ 0, 7, 83, 0 }));
        Wav.write("test.wav",        Wav.read("exampleWav.wav"));
        System.out.print("done\n");
    }
}