package runnableAndThread;

public class MaxRunnable implements Runnable {

    private final int [] mass;

    private int max;

    public MaxRunnable(int [] mass) {
        this.mass = mass;
    }

    @Override
    public void run() {
        max = mass[0];
        for (int current : mass) {
            if(max < current) max = current;
        }
    }

    public int getResult() {
        return max;
    }
}
