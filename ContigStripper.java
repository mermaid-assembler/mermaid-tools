import java.util.*;
import java.io.*;

public class ContigStripper {
  /* Removes extraneous information from a contig file */

  static final String extension = ".stripped";
  static BufferedWriter out;
  
  public static void parse(String filename) throws IOException{
    /* Remove additional information from "contig" lines
     * Open filename
     */
    File in = new File(filename);
    Scanner scan = new Scanner(in);
    /* Create the output file. */
    String outName = filename.substring(0, filename.length()) + extension;
    /* Open file, do IO stuff, then start iterating */
    out = new BufferedWriter(new FileWriter(outName));
    while (scan.hasNextLine()){
      doLine(scan.nextLine());
    }
    out.close();
  }
  
  public static void doLine(String line) throws IOException{
    /* Split a line around spaces
     * Strip unnecessary information
     *
     * Check if the line begins with ">contig"
     */
    if (line.startsWith(">contig")) {
      String[] split = line.split(" ");
      out.write(">Contig" + split[1] + "\n");
    } else {
      out.write(line + "\n");
    }
  }

  public static void main(String[] args) throws IOException{
    /* Feed in the file name as the argument */
    String input = args[0];
    parse(input);
  }
}
