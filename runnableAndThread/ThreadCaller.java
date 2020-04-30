package runnableAndThread;

public class ThreadCaller {

    public static SumThread getResultFromSumThread (int [] mass) throws InterruptedException {

        SumThread sumThread = new SumThread(mass);

        sumThread.start();

        sumThread.join();

        return sumThread;

    }

}
