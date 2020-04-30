package runnableAndThread;

public class SumRunnable implements Runnable {

    private final int[] sum;

    private int result;

    public SumRunnable(int[] sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for(int a : sum) {
            result += a;
        }
    }

    public int getResult() {
        return result;
    }
}
