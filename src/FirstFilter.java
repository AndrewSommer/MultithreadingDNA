import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for scanning in a text file
 * and adding the contents to the output buffer
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class FirstFilter extends Filter {

    /**
     * Constructor for the FirstFilter
     *
     * @param inputBuffer  should be null, because this is the first filter
     * @param outputBuffer reference to the arrayBlockingQueue where the file contents should be
     *                     placed
     */
    public FirstFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(null, outputBuffer);
        if (inputBuffer != null) {
            System.out.println("First Filter Ignored inputBuffer Parameter, Should Have Been Null");
        }
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * The method scans the file exampleGenome.txt in the project folder, and places the contents
     * as a string into the output buffer
     */
    @Override
    public final void run() {
        //INPUT FILE MUST BE LOCATED IN DIRECTORY NAMED exampleGenome.txt IN 1 SINGLE LINE
        System.out.println("First Filter Running");
        try {
            //File file = new File(System.getProperty("user.dir") + "/Desktop/MultithreadingDNA/exampleGenome.txt");
            File file = new File("exampleGenome.txt");
            Scanner scanner = new Scanner(file);
            outputBuffer.put(scanner.nextLine()); //Add all content to buffer
            scanner.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        queueFinished[0] = true;
        System.out.println("First Filter Finished");
    }

}
