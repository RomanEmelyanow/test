package runnableAndThread;

public class SumThread extends Thread{

    private final int[] sum;

    private int result;

    public SumThread(int[] sum) {
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
