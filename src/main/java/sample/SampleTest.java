package sample;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SampleTest {

    public static volatile boolean isDone = false;

    static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws Exception {


        executorService.schedule(() -> {
            SampleTest.isDone = true;
        }, 10L, TimeUnit.SECONDS);

        ThreadUtil.spinWait(() -> isDone == true);

        executorService.shutdown();

    }

}
