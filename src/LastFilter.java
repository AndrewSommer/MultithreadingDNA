import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings
 * to be print to the command line
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class LastFilter extends Filter {

    /**
     * Constructor for the LastFilter
     *
     * @param inputBuffer  reference to the buffer to input strings which should be
     *                     strings to print
     * @param outputBuffer should be null, there is no output for the last filter
     */
    public LastFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, null);
        if (outputBuffer != null) {
            System.out.println("Last Filter Ignored outputBuffer Parameter, Should Have Been Null");
        }
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * This method inputs the raw text sequence from the input buffer, prints the text to the
     * command line
     */
    @Override
    public final void run() {
        String printString = "";
        System.out.println("Last Filter Running");
        while (!(inputBuffer.size() == 0) || !(queueFinished[5])) {
            try {
                printString += inputBuffer.take();
                printString += "\n";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Last Filter Finished");
        System.out.println("Output:");
        System.out.println(printString);
    }

}
