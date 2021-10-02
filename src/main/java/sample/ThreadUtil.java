package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.time.ZonedDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;


public class ThreadUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadUtil.class);

    /**
     *
     * @param callable
     * @throws Exception
     */
    public static void spinWait(Callable<Boolean> callable) throws Exception {
        ThreadUtil.spinWait(callable, 5L, SECONDS);
    }

    /**
     *
     * @param callable
     * @param wait
     * @param timeUnit
     * @throws Exception
     */
    public static void spinWait(Callable<Boolean> callable, long wait, TimeUnit timeUnit) throws Exception {
        long startTime = now().toInstant().toEpochMilli();
        long endTime = wait <= 0 ? wait : startTime + timeUnit.toMillis(wait);

        LOG.info("Beginning spinWait...");
        do {
            LOG.trace("Spinning...");
            if (now().toInstant().toEpochMilli() > endTime) {
                LOG.error("Timed out waiting...");
            }
        } while ((now().toInstant().toEpochMilli() <= endTime) && (!callable.call()));
    }
}
