/**
 * Auteur : Theo Bensaci, Gasmin Yasser Date : 06.10.2025 Description : Define all the command of
 * BinaryWave Source : https://picocli.info/quick-guide.html
 */
package ch.heigvd;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import picocli.CommandLine;

@CommandLine.Command(name = "BinaryWav", version = "BinaryWav 1.0", mixinStandardHelpOptions = true)
public class BinaryWav implements Runnable {
  public static void main(String[] args) {
    int exitCode = new CommandLine(new BinaryWav()).execute(args);
    System.exit(exitCode);
  }

  @CommandLine.Command(name = "help", description = "show the help message of the app")
  public void help() {
    System.out.println(
        "\n"
            + "\n"
            + "██████╗ ██╗███╗   ██╗ █████╗ ██████╗ ██╗   ██╗██╗    ██╗ █████╗ ██╗   ██╗\n"
            + "██╔══██╗██║████╗  ██║██╔══██╗██╔══██╗╚██╗ ██╔╝██║    ██║██╔══██╗██║   ██║\n"
            + "██████╔╝██║██╔██╗ ██║███████║██████╔╝ ╚████╔╝ ██║ █╗ ██║███████║██║   ██║\n"
            + "██╔══██╗██║██║╚██╗██║██╔══██║██╔══██╗  ╚██╔╝  ██║███╗██║██╔══██║╚██╗ ██╔╝\n"
            + "██████╔╝██║██║ ╚████║██║  ██║██║  ██║   ██║   ╚███╔███╔╝██║  ██║ ╚████╔╝ \n"
            + "╚═════╝ ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚══╝╚══╝ ╚═╝  ╚═╝  ╚═══╝  \n");
    System.out.println("\n\u001B[36mDescription : \u001B[0m");
    System.out.println(
        "BinaryWav is cli project aiming to hidde binary file in to .wav and being able to recover them");
    System.out.println("\n\u001B[34mUsage : \u001B[0m");
    System.out.println("\t - \u001B[33mhelp\u001B[0m : show the help message of the app");
    System.out.println(
        "\t - \u001B[33mhide \u001B[32m<source file Path> <target file path> \u001B[0m: hide \u001B[32m<source file>\u001B[0m  into a .wav \u001B[32m<traget file>\u001B[0m");
    System.out.println(
        "\t - \u001B[33mget \u001B[32m<source file Path> <target file path> \u001B[0m: recover data from the .wav \u001B[32m<source file>\u001B[0m and put it in to \u001B[32m<traget file>\u001B[0m");
  }

  @CommandLine.Command(name = "hide", description = "hide <source file> into a .wav <target file>")
  public void hide(
      @CommandLine.Parameters(
              arity = "1",
              paramLabel = "<source file Path>",
              description = "source file path")
          String sourcePath,
      @CommandLine.Parameters(
              arity = "1",
              paramLabel = "<target file Path>",
              description = "target file path")
          String targetPath) {
    System.out.print(
        "Hiding '"
            + sourcePath
            + "' in to '"
            + targetPath
            + ((targetPath.endsWith(".wav")) ? "" : ".wav")
            + "' =====> ");
    try {
      Wav.write(targetPath, Files.readAllBytes(Paths.get(sourcePath)));
      System.out.println("\u001B[32m[DONE]\u001B[0m");
    } catch (IOException e) {
      System.out.print("\u001B[31m[FAIL]\u001B[0m : ");
      System.out.println(e.toString());
    }
  }

  @CommandLine.Command(
      name = "get",
      description = "recover data from the .wav <source file> and put it in to <target file>")
  public void get(
      @CommandLine.Parameters(
              arity = "1",
              paramLabel = "<source file Path>",
              description = "source file path")
          String sourcePath,
      @CommandLine.Parameters(
              arity = "1",
              paramLabel = "<target file Path>",
              description = "target file path")
          String targetPath) {

    System.out.print(
        "Get data from '" + sourcePath + "' and put it in to '" + targetPath + "' =====> ");
    try {
      DataOutputStream outFile = new DataOutputStream(Files.newOutputStream(Paths.get(targetPath)));
      outFile.write(Wav.read(sourcePath));
      System.out.println("\u001B[32m[DONE]\u001B[0m");
    } catch (IOException e) {
      System.out.print("\u001B[31m[FAIL]\u001B[0m : ");
      System.out.println(e.toString());
    }
  }

  @Override
  public void run() {
    help();
  }
}
