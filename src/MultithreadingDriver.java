import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Instantiates 7 Runable Filters, ArrayBlockQueue buffers to transfer strings between the filters,
 * and places all Filters in a ThreadPool to be managed by an ExecutorService. If the process takes
 * >10 Seconds the threads are terminated.
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public class MultithreadingDriver {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        int size = 5;

        //Make All Buffers
        ArrayBlockingQueue<String> firstFilter_gapFinder = new ArrayBlockingQueue<>(1);
        ArrayBlockingQueue<String> gapFinder_ReverseComplementor = new ArrayBlockingQueue<>(size);
        ArrayBlockingQueue<String> reverseComplementor_FrameBuilder = new ArrayBlockingQueue<>(size = size * 2);
        ArrayBlockingQueue<String> frameBuilder_Translator = new ArrayBlockingQueue<>(size = size * 3);
        ArrayBlockingQueue<String> translator_ORFFinder = new ArrayBlockingQueue<>(size = size / 6);
        ArrayBlockingQueue<String> oRFFinder_LastFilter = new ArrayBlockingQueue<>(size);

        //Make All Buffers which have references to the buffers needed
        FirstFilter firstFilter = new FirstFilter(null, firstFilter_gapFinder);
        GapFinderFilter gapFinderFilter = new GapFinderFilter(firstFilter_gapFinder, gapFinder_ReverseComplementor);
        ReverseComplementorFilter reverseComplementorFilter = new ReverseComplementorFilter(gapFinder_ReverseComplementor, reverseComplementor_FrameBuilder);
        FrameBuilderFilter frameBuilderFilter = new FrameBuilderFilter(reverseComplementor_FrameBuilder, frameBuilder_Translator);
        TranslatorFilter translatorFilter = new TranslatorFilter(frameBuilder_Translator, translator_ORFFinder);
        ORFFinderFilter orfFinderFilter = new ORFFinderFilter(translator_ORFFinder, oRFFinder_LastFilter);
        LastFilter lastFilter = new LastFilter(oRFFinder_LastFilter, null);

        //Use ExecutorService to manage threads that execute Filter tasks
        executorService.execute(firstFilter);
        executorService.execute(gapFinderFilter);
        executorService.execute(reverseComplementorFilter);
        executorService.execute(frameBuilderFilter);
        executorService.execute(translatorFilter);
        executorService.execute(orfFinderFilter);
        executorService.execute(lastFilter);

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Tasks never finished");
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}