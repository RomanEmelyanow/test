package callable;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String... args) {
        testArray(new Integer[0], 0);
        testArray(new Integer[]{1, 4, 10, 2}, 10);
        testArray(new Integer[]{1, 4, -10, 2}, 4);
        testArray(new Integer[]{1}, 1);
    }

    private static void testArray(final Integer[] inputArray, final int expectedResult) {
        final int actualResult = new MaxFinder(inputArray).call();
        if (actualResult != expectedResult) {
            throw new RuntimeException(
                    String.format(
                            "Actual max: %d, expected max: %d",
                            actualResult,
                            expectedResult));
        }
    }

    // BEGIN (write your solution here)
    public static class MaxFinder {

        private final Integer [] array;

        private MaxFinder(Integer [] array) {
            this.array = array;
        }

        public Integer call() {

            if(array .length == 0) return 0;

            int max = Integer.MIN_VALUE ;

            for(int i = 0; i < array.length; i++) {

                if(max < array[i]) max = array[i];

            }

        return max;

        }

    }
    // END
}