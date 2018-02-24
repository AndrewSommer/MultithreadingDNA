import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings of
 * base pairs, whitespace, and gap indicators and outputting the split reads as strings
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class GapFinderFilter extends Filter {

    /**
     * Constructor for the GapFinderFilter
     *
     * @param inputBuffer  reference to the buffer to input strings, which should be a string of
     *                     base pairs, gap indicators, or whitespace
     * @param outputBuffer reference to the arrayBlockingQueue where the output strings should be
     *                     placed
     */
    public GapFinderFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, outputBuffer);
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * This method inputs the raw text sequence from the input buffer, and splits the string
     * up into several reads using the gap indicator (N)
     */
    @Override
    public final void run() {
        System.out.println("Gap Finder Began");
        try {
            while (!(inputBuffer.size() == 0) || !(queueFinished[0])) {
                char[] temp = inputBuffer.take().toCharArray();
                String returnString = "";
                for (int index = 0; index < temp.length; index++) {
                    if (temp[index] == 'N' && returnString.length() > 0) {
                        outputBuffer.put(returnString);
                        returnString = "";
                    } else if (temp[index] != ' ') {
                        returnString += temp[index];
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queueFinished[1] = true;
        System.out.println("Gap Finder Finished");
    }

}
