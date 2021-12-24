import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *  A demonstration of BstDup class output as filename: Results.txt
 *
 * @author      Brandon Dombrowsky
 * @version     2021-11-08
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // set up scanner to bring in files
        Scanner intInFile    = new Scanner(new File("intFile.txt"));
        Scanner stringInFile = new Scanner(new File("stringFile.txt"));

        // instantiate objects
        BstDup<Integer> bstInteger = new BstDup<>();
        BstDup<String>  bstString  = new BstDup<>();

        // loop through integer file and add items to tree
        while (intInFile.hasNextInt()) {
            bstInteger.add(intInFile.nextInt());
        }

        // loop through string file and add items to tree
        while (stringInFile.hasNext()) {
            bstString.add(stringInFile.next());
        }

        // output to text file
        File outputFile = new File("Results.txt");
        PrintStream ps  = new PrintStream(outputFile);

        ps.println("""
                -------------
                 Match Count
                -------------""");
        ps.println("# of times Brandon in tree, expected 5; count = " + bstString.getMatchCount("Brandon"));
        ps.println("# of times Lisa in tree, expected 2; count = " + bstString.getMatchCount("Lisa"));
        ps.println("# of times Casey in tree, expected -1; count = " + bstString.getMatchCount("Casey"));
        ps.println("# of times Xavier in tree, expected -1; count = " + bstString.getMatchCount("Xavier") + "\n");

        ps.println("# of times 7 in tree, expected 2; count = " + bstInteger.getMatchCount(7));
        ps.println("# of times 13 in tree, expected 3; count = " + bstInteger.getMatchCount(13));
        ps.println("# of times 1 in tree, expected -1; count = " + bstInteger.getMatchCount(1));
        ps.println("# of times 5 in tree, expected -1; count = " + bstInteger.getMatchCount(5) + "\n");

        ps.println("""
                -----------------
                 Match After Add
                -----------------""");
        bstString.add("Casey");
        bstString.add("Xavier");
        ps.println("# of times Casey in tree after add, expected 1; count = " + bstString.getMatchCount("Casey"));
        ps.println("# of times Xavier in tree after add, expected 1; count = " + bstString.getMatchCount("Xavier")
                + "\n");

        bstInteger.add(1);
        bstInteger.add(5);
        ps.println("# of times 1 in tree after add, expected 1; count = " + bstInteger.getMatchCount(1));
        ps.println("# of times 5 in tree after add, expected 1; count = " + bstInteger.getMatchCount(5) + "\n");

        ps.println("""
                --------------------
                 Match After Delete
                --------------------""");
        bstString.delete("Brandon");
        bstString.delete("Lisa");
        ps.println("# of times Brandon in tree after deletion, expected 4; count = "
                + bstString.getMatchCount("Brandon"));
        ps.println("# of times Lisa in tree after deletion, expected 1; count = "
                + bstString.getMatchCount("Lisa"));
        bstString.deleteAll("Brandon");
        ps.println("# of times Brandon in tree after deleteAll, expected -1; count = "
                + bstString.getMatchCount("Brandon") + "\n");

        bstInteger.delete(7);
        bstInteger.delete(13);
        ps.println("# of times 7 in tree after delete, expected 1; count = " + bstInteger.getMatchCount(7));
        ps.println("# of times 13 in tree after delete, expected 2; count = " + bstInteger.getMatchCount(13));
        bstInteger.deleteAll(13);
        ps.println("# of times 13 in tree after deleteAll, expected -1; count = "
                + bstInteger.getMatchCount(13));
    }
}