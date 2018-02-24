import java.util.concurrent.ArrayBlockingQueue;

/**
 * Abstract class to for specific filters to extend, and override the run method
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public abstract class Filter implements Runnable {

    /**
     * Allows filters to communicate when they have finished running, so that subsequent
     * filters know not to expect anything else added to the buffer
     */
    public static boolean[] queueFinished = {false, false, false, false, false, false};

    /**
     * Specifies the reference to the buffer to input data from, if the filter is the first
     * filter in a sequence the reference should be null
     */
    public final ArrayBlockingQueue<String> inputBuffer;

    /**
     * Specifies the reference to the buffer to output data from, if the filter is the last
     * filter in a sequence the reference should be null
     */
    public final ArrayBlockingQueue<String> outputBuffer;

    /**
     * Constructor for the abstract class
     *
     * @param inputBuffer  reference to the buffer to output data from, if the filter is the last
     *                     filter in a sequence the reference should be null
     * @param outputBuffer reference to the buffer to output data from, if the filter is the last
     *                     filter in a sequence the reference should be null
     */
    public Filter(ArrayBlockingQueue<String> inputBuffer, ArrayBlockingQueue<String> outputBuffer) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    /**
     * When an object extending abstract Filter is used
     * to create a thread this method runs implicitly
     */
    @Override
    public abstract void run();

}
