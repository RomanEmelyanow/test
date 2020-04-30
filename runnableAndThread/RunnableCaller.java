package runnableAndThread;

public class RunnableCaller {

    public static SumRunnable getResultFromSumRunnable (int [] mass) throws InterruptedException {

        SumRunnable sumRunnable = new SumRunnable(mass);

        Thread threadSumRunnable = new Thread(sumRunnable);

        threadSumRunnable.start();

        threadSumRunnable.join();

        return sumRunnable;
    }
}
