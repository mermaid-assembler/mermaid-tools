import java.util.*;
import java.io.*;

public class FastqConv {
  /* Convert Illumina's format to fastq */

  static long inputLength;
  static final String extension = "fq";
  static BufferedWriter out;
  static long runningSize;
  
  public static void parse(String filename) throws IOException{
    /* Parse through .txt file and convert to .fq
     * Open filename
     */
    File in = new File(filename);
    Scanner scan = new Scanner(in);
    inputLength = in.length();
    /* Create a file replacing .txt with .fq */
    String outName = filename.substring(0, filename.length()-3) + extension;
    /* Open file do IO stuff, then start iterating */
    out = new BufferedWriter(new FileWriter(outName));
    runningSize = 0;
    while (scan.hasNextLine()){
      doLine(scan.nextLine());
    }
    out.close();
    System.out.println("\r" + 100 + "%");
  }
  
  public static void doLine(String line) throws IOException{
    /* Split a line around semicolons
     * Write necessary stuff to output
     * Then print percentage processed
     */
    String[] split = line.split(":");
    runningSize += line.length();
    out.write("@ "+ split[0] + split[1] + split[2] + split[3] + split[4] + "\n");
    out.write(split[5]+"\n");
    out.write("+\n");
    out.write(split[6]);
    out.write("\n");
    /* Print the percentage */
    System.out.print("\r" + ((runningSize*100)/inputLength) + "%");
    
  }

  public static void main(String[] args) throws IOException{
    /* Feed in the file name as the argument */
    String input = args[0];
    parse(input);
  }
}
