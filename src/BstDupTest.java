import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BstDup testing class
 *
 * @author      Brandon Dombrowsky
 * @version     2021-11-18
 */
class BstDupTest {
    // declare and instantiate test cases for use throughout test class
    BstDupTest() throws FileNotFoundException {}

    BstDup<Integer> bstIntTest;

    BstDup<String>  bstStringTest = new BstDup<>();

    @BeforeEach
    void setUp() {
    // integer test
    try {
        Scanner intTest = new Scanner(new File("intTestFile.txt"));
        bstIntTest = new BstDup<>();
        while (intTest.hasNextInt()) {
            bstIntTest.add(intTest.nextInt());
        }
    } catch (Exception e) {
        // do nothing
    }

    // string test
        try {
            Scanner stringTest = new Scanner(new File("stringTestFile.txt"));
            bstStringTest = new BstDup<>();
            while (stringTest.hasNext()) {
                bstStringTest.add(stringTest.next());
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    @AfterEach
    void tearDown() {
    }
    //-----------------------------------------------------------------------------------
    //      HAPPY PATHS
    //-----------------------------------------------------------------------------------
    @Test
    void constructorTest() {
        assertEquals(9, bstStringTest.getSize());

        assertEquals(9, bstIntTest.getSize());
    }

    @Test
    void add() {
        bstStringTest.add("Brandon");
        assertEquals(3, bstStringTest.getMatchCount("Brandon"));

        bstIntTest.add(7);
        assertEquals(3, bstIntTest.getMatchCount(7));
    }

    @Test
    void delete() {
        bstStringTest.delete("Hickory");
        assertEquals(-1, bstStringTest.getMatchCount("Hickory"));

        bstIntTest.delete(10);
        assertEquals(-1, bstIntTest.getMatchCount(10));
    }

    @Test
        void deleteAll() {
        bstStringTest.deleteAll("Brandon");
        assertEquals(-1, bstStringTest.getMatchCount("Brandon"));

        bstIntTest.deleteAll(7);
        assertEquals(-1, bstIntTest.getMatchCount(7));
    }

    @Test
    void getMatchCount() {
        assertEquals(2, bstStringTest.getMatchCount("Brandon"));

        assertEquals(2, bstIntTest.getMatchCount(7));
    }

//    @Test
//    void getAllDataInOrder() {
//    }
    //-----------------------------------------------------------------------------------
    //      UNHAPPY PATHS
    //-----------------------------------------------------------------------------------
    // throws test1

    // throws test2
}