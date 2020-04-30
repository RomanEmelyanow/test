package fibonachi;

public class ThreadHelper {

    public static int fib(final int numberToCalculate) throws Exception {
        final FibCalculator calculator = new FibCalculator(numberToCalculate);
        final FibCalculator calculator2 = new FibCalculator(numberToCalculate - 1);
        final FibCalculator calculator3 = new FibCalculator(numberToCalculate - 2);
        if(numberToCalculate > 2) {
            calculator2.start();
            calculator3.start();
            calculator2.join();
            calculator3.join();
            return calculator2.getResult()+calculator3.getResult();
        } else {
            calculator.start();
            calculator.join();
            return calculator.getResult();
        }

    }

    private static class FibCalculator extends Thread {
        // BEGIN (write your solution here)
        private int numberToCalculate;

        public FibCalculator(final int numberToCalculate) {
            this.numberToCalculate = numberToCalculate;
        }

        @Override
        public void run() {

            int prev = 0;

            int cur = 1;

            int next = 1;

            if(numberToCalculate > 1) {

                for (int i = 1; i < numberToCalculate; i++) {
                    next = prev + cur;
                    prev = cur;
                    cur = next;
                }
                numberToCalculate = next;
            }


        }

        public int getResult(){
            return numberToCalculate;
        }
        // END
    }

}
