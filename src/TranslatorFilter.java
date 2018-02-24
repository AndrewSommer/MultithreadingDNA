import java.util.concurrent.ArrayBlockingQueue;

/**
 * Extends abstract class Filter, and contains the specific code for inputting strings
 * of base pairs, and converting those strings to amino acid characters which are
 * output
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class TranslatorFilter extends Filter {

    /**
     * Constructor for the Translator Filter
     *
     * @param inputBuffer  reference to the buffer to input strings, which should be a string of
     *                     base pairs to be translated into amino acid characters
     * @param outputBuffer reference to the arrayBlockingQueue where the output strings should be
     *                     placed
     */
    public TranslatorFilter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        super(inputBuffer, outputBuffer);
    }

    /**
     * This method is run automatically when an object of this class is used to create a thread.
     * This method inputs the raw text sequence from the input buffer, which is a string of base
     * pairs, and translates the strings into amino acid characters using enum Codon, which is then
     * output to the output buffer
     */
    @Override
    public final void run() {
        System.out.println("TranslatorFilter Running");
        while (!(inputBuffer.size() == 0) || !(queueFinished[3])) {
            try {
                char[] basePairs = inputBuffer.take().toCharArray();
                int basePairIndex = 0;
                String aminoAcidString = "";
                while (basePairIndex + 2 < basePairs.length) {
                    char baseOne = basePairs[basePairIndex];
                    char baseTwo = basePairs[basePairIndex + 1];
                    char baseThree = basePairs[basePairIndex + 2];
                    String temp = Character.toString(baseOne) + Character.toString(baseTwo) + Character.toString(baseThree);
                    aminoAcidString += Codon.getCodon(temp).getAminoAcid();
                    basePairIndex += 2;
                }
                outputBuffer.put(aminoAcidString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queueFinished[4] = true;
        System.out.println("TranslatorFilter Finished");
    }
}
