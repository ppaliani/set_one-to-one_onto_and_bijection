import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part_2 {

  public static void main(String[] args) throws IOException {

    // Declaring File objects
    File file = null;
    File outputFile = null;

    // File input and output names
    file = new File("C:\\Users\\Paul\\Desktop\\Program1\\Part2\\file4.txt");
    outputFile = new File("C:\\Users\\Paul\\Desktop\\Program1\\Part2\\Output.txt");

    // Declaring input and output objects
    FileWriter fw = null;
    PrintWriter pw = null;

    FileInputStream fis = null;

    try {
      fw = new FileWriter(outputFile);
    } catch (IOException e1) {
      System.out.println("Could not find file");
      e1.printStackTrace();
    }

    pw = new PrintWriter(fw);

    try {
      fis = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      System.out.println("Could not find file");
      e.printStackTrace();
    }

    Scanner input = new Scanner(fis);

    // Declaring rest of required variables
    int m = 0, n = 0, p = 0, q = 0, x[], y[], u[], v[];
    boolean hasDuplicate = false, inV = false, isOneToOne = false;

    // m is the length of set 1 and x is size m
    m = input.nextInt();
    x = new int[m];

    // Enter values of set 1 into array x
    for (int i = 0; i < m; i++) {
      int temp = input.nextInt();
      x[i] = temp;
    }

    // n is the length of set 2 and y is size n
    n = input.nextInt();
    y = new int[n];

    // Enter values of set 2 into array y
    for (int i = 0; i < n; i++) {
      int temp = input.nextInt();
      y[i] = temp;
    }

    // Read the number of pairs and set p to that number, then initialize arrays u,v of size p
    p = input.nextInt();
    u = new int[p];
    v = new int[p];

    // Loop and read p values from the file into arrays u,v
    for (int i = 0; i < p; i++) {
      u[i] = input.nextInt();
      v[i] = input.nextInt();
    }

    // ====================Deciding one to one====================
    // Loop through v
    for (int i = 0; i < p; i++) {
      // for every value in v, loop through the remainder of v
      for (int j = i + 1; j < p; j++) {
        // if the original value is the same as any other value in v, then v has a duplicate
        if (v[i] == v[j]) {
          hasDuplicate = true;
          break;
        }
      }
    }

    // If there are duplicates, then not one to one, otherwise it is. Write result to file
    if (hasDuplicate) {
      pw.print("Not one to one\n");
    } else {
      pw.print("One to one\n");

      // it is one to one, set isOneToOne to true
      isOneToOne = true;
    }

    // ====================Deciding onto====================
    // Loop through y
    for (int i = 0; i < n; i++) {
      // For every value in y, loop through v
      for (int j = 0; j < p; j++) {
        // if the value in y is in v then set inV to true
        if (y[i] == v[j]) {
          inV = true;
          break;
        }
      }

      // if after looping through v, and y was never found to be inV, break from the original loop
      if (!inV)
        break;

      if (i == (n - 1)) {
        break;
      }

      if (i < n) {
        inV = false;
      }
    }

    // if inV is true then each value of y was in v so it is onto, otherwise it's not. Write
    // result to output file
    if (inV) {
      pw.print("Onto\n");
    } else {
      pw.print("Not onto\n");
    }

    // ====================Deciding bijective====================
    // If its one to one, its bijective. Write results to output file
    if (isOneToOne && inV) {
      pw.print("Bijective\n");
    } else {
      pw.print("Not bijective");
    }

    input.close();
    pw.close();

  }

}
