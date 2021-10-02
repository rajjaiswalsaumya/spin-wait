package sample;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ThreadUtilTest {

    private static boolean isDone = false;

    @Test
    void spinWait() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            isDone = true;
        }, 2L, TimeUnit.SECONDS);

        ThreadUtil.spinWait(() -> isDone == true);
        executorService.shutdownNow();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
}