import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings of
 * base pairs, and outputting all possible frames of base pairs for the rest of the filters
 * to leverage
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class ORFFinderFilter extends Filter {

    /**
     * The minimum length of animo acids for the filter to return
     */
    private int minReadLength = 21;

    /**
     * Constructor for the ORFFinderFilter
     *
     * @param inputBuffer  reference to the buffer to input strings, which should be a string of
     *                     base pairs
     * @param outputBuffer reference to the arrayBlockingQueue where the output strings should be
     *                     placed
     */
    public ORFFinderFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, outputBuffer);
    }

    /**
     * This method is used to set the minimum length of amino acids for the filter to return
     * if this method is not called the minReadLength defaults to 21
     *
     * @param minReadLength minimum read length wanted for the filter to return
     */
    public final void setMinReadLength(int minReadLength) {
        this.minReadLength = minReadLength;
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * This method inputs the raw text sequence from the input buffer, splits up reads by stop
     * codons, and returns only reads greater than the minReadLength
     */
    @Override
    public void run() {
        System.out.println("ORF Finder Running");
        while (!(inputBuffer.size() == 0) || !(queueFinished[4])) {
            try {
                String inputString = inputBuffer.take();
                char[] inputChars = inputString.toCharArray();
                String aminoAcidString = "";
                for (int charIndex = 0; charIndex < inputChars.length; charIndex++) {
                    if (inputChars[charIndex] == '*') {
                        if (aminoAcidString.length() > minReadLength) {
                            outputBuffer.put(aminoAcidString);
                            aminoAcidString = "";
                        }
                    } else {
                        aminoAcidString += inputChars[charIndex];
                        if (charIndex == inputChars.length - 1 && aminoAcidString.length() > minReadLength) {
                            outputBuffer.put(aminoAcidString);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queueFinished[5] = true;
        System.out.println("ORF Finder Finished");
    }
}
