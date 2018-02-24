import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings of
 * base pairs, and outputting all possible frames of base pairs for the rest of the filters
 * to leverage
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class FrameBuilderFilter extends Filter {

    /**
     * Constructor for the FrameBuilderFilter
     *
     * @param inputBuffer  reference to the buffer to input strings, which should be a string of
     *                     base pairs
     * @param outputBuffer reference to the arrayBlockingQueue where the file contents should be
     *                     placed
     */
    public FrameBuilderFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, outputBuffer);
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * The method reads strings from the input file, and outputs the possible reading frames as
     * strings to the output buffer
     */
    @Override
    public final void run() {
        System.out.println("FrameBuilderFilter Began");
        try {
            while (!(inputBuffer.size() == 0) || !(queueFinished[2])) {
                String currString = inputBuffer.take();
                if (currString.length() > 0) {
                    outputBuffer.put(currString);
                }
                if (currString.length() > 1) {
                    outputBuffer.put(currString.substring(1));
                }
                if (currString.length() > 2) {
                    outputBuffer.put(currString.substring(2));
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        queueFinished[3] = true;
        System.out.println("FrameBuilderFilter Finished");
    }
}
