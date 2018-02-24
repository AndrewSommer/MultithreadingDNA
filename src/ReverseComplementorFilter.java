import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings of
 * base pairs, and outputting the original string, and the reverse complement of the string
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class ReverseComplementorFilter extends Filter {

    /**
     * Constructor for the ReverseComplementorFilter
     *
     * @param inputBuffer  reference to the buffer to input strings, which should be a string of
     *                     base pairs
     * @param outputBuffer reference to the arrayBlockingQueue where the output strings should be
     *                     placed
     */
    public ReverseComplementorFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, outputBuffer);
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * This method inputs the raw text sequence from the input buffer, returns the string to the
     * output buffer, as well as the reverse complement of the read
     */
    @Override
    public final void run() {
        try {
            System.out.println("Reverse Filter Began");
            while (!(inputBuffer.size() == 0) || !(queueFinished[1])) {
                String forward = inputBuffer.take();
                char[] forwardArray = forward.toCharArray();
                String reverseComplement = "";
                for (int index = forwardArray.length - 1; index >= 0; index--) {
                    if (forwardArray[index] == 'A') {
                        reverseComplement += 'T';
                    } else if (forwardArray[index] == 'T') {
                        reverseComplement += 'A';
                    } else if (forwardArray[index] == 'C') {
                        reverseComplement += 'G';
                    } else if (forwardArray[index] == 'G') {
                        reverseComplement += 'C';
                    }
                }
                outputBuffer.put(reverseComplement);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queueFinished[2] = true;
        System.out.println("ReverseFilter Finished");
    }

}

