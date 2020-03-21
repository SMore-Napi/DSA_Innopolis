package assignments.assignment2;

// Uncomment it to use junit tests.
/*
import org.junit.Test;
import static org.junit.Assert.*;
 */

/**
 * @author Roman Soldatov BS19-02.
 * 2.2 Sorting. Implementing the "Merge Sort" algorithm.
 * <p>
 * Class for testing merge sort algorithm.
 * It requires to import the junit library.
 * <p>
 * Maybe your IDE does not import this library automatically. So, then this class won't compile because of errors.
 * So, just in case I commented it out.
 * Well, you can uncomment the code inside this class and import junit library to check tests.
 */
public class Sorting {

    // Uncomment it to use junit tests.
    /*
    @Test
    public void testEmptyArray() {
        Integer[] array = {};
        MergeSorter.sort(array);
        boolean sorted = isSorted(array);
        assertTrue(sorted);
    }

    @Test
    public void testArrayAscendingOrder() {
        Double[] array = {1.0, 2.5, 3.3, 4.7, 5.1, 5.1, 6.54, 7.21, 8.23, 9.76, 10.74};
        MergeSorter.sort(array);
        boolean sorted = isSorted(array);
        assertTrue(sorted);
    }

    @Test
    public void testArrayDecreasingOrder() {
        String[] array = {"yte", "hrd", "bbb", "afd", "abc", "abc", "aaa"};
        MergeSorter.sort(array);
        boolean sorted = isSorted(array);
        assertTrue(sorted);
    }

    @Test
    public void testArrayOnRange0_2() {
        int numberOfTest = 1000;
        int maxArrayLength = 100;
        int maxKeyValue = 2;

        boolean sorted = testMergeSort(numberOfTest, maxArrayLength, maxKeyValue);
        assertTrue(sorted);
    }

    @Test
    public void testArrayOnRange0_10() {
        int numberOfTest = 1000;
        int maxArrayLength = 100;
        int maxKeyValue = 10;

        boolean sorted = testMergeSort(numberOfTest, maxArrayLength, maxKeyValue);
        assertTrue(sorted);
    }

    @Test
    public void testArrayOnRange0_100() {
        int numberOfTest = 1000;
        int maxArrayLength = 100;
        int maxKeyValue = 100;

        boolean sorted = testMergeSort(numberOfTest, maxArrayLength, maxKeyValue);
        assertTrue(sorted);
    }

    @Test
    public void testArrayOnRange0_10000() {
        int numberOfTest = 100;
        int maxArrayLength = 100000;
        int maxKeyValue = 10000;

        boolean sorted = testMergeSort(numberOfTest, maxArrayLength, maxKeyValue);
        assertTrue(sorted);
    }
    */

    /**
     * Generate an array of type Integer, sort it, check if it is sorted.
     * Repeat several times this procedure.
     * If at least once the array has not been sorted, then return false.
     *
     * @param countOfTests   - count of tests to generate and sort the array.
     * @param maxArrayLength - max array's size.
     * @param maxKeyValue    - array's values from this range [0...maxValue].
     * @return true if all tests are passed, false - otherwise.
     */
    private boolean testMergeSort(int countOfTests, int maxArrayLength, int maxKeyValue) {
        // Repeat it several times.
        for (int i = 0; i < countOfTests; i++) {
            // Generate and sort an array
            Integer[] array = getRandomIntegerArray(maxArrayLength, maxKeyValue);
            MergeSorter.sort(array);

            // If the array is not sorted, then return false.
            boolean sorted = isSorted(array);
            if (!sorted) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generate an array.
     *
     * @param maxLength - maximum array's size.
     * @param maxValue  - maximum integer value which array can contain. It represents a value range [0...maxValue]
     * @return randomly generated array of type Integer.
     */
    private static Integer[] getRandomIntegerArray(int maxLength, int maxValue) {

        // Generate the size of an array.
        int length = (int) (Math.random() * maxLength);
        Integer[] array = new Integer[length];

        // Fill the array with numbers from the range [0...maxValue].
        for (int i = 0; i < length; i++) {
            int key = (int) (Math.random() * maxValue);
            array[i] = key;
        }

        return array;
    }

    /**
     * Check if an array is sorted in ascending order.
     *
     * @param array - array to test.
     * @param <T>   - data type which the array contains.
     * @return true if the array is sorted, false - otherwise.
     */
    private <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Class for using the "Merge Sort" algorithm.
 * It contains only one public static method to sort an array.
 * There also some private helper methods.
 * You don't need to create an instance of this class as all methods are static.
 * <p>
 * The time complexity of this algorithm is O(n*log(n)) in worst-case, best-case, and average-case.
 * It is the out-of-place implementation as it uses a temporary array to merge elements from a current part.
 * It is a stable sort as elements, during the merging, are inserting in a sorted array in the same order as they were.
 */
class MergeSorter {

    /**
     * The main method to call.
     *
     * @param array - array to sort.
     * @param <T>   - it accepts arrays which has the comparable data type.
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        sortTwoParts(array, 0, array.length - 1);
    }

    /**
     * Divide the array into two parts, sort them separately, and merge this sorted parts.
     *
     * @param array      - array which part is required to sort.
     * @param leftIndex  - the start index of the part (inclusive).
     * @param rightIndex - the end index of the part (inclusive).
     * @param <T>        it accepts arrays which has the comparable data type.
     */
    private static <T extends Comparable<? super T>> void sortTwoParts(T[] array, int leftIndex, int rightIndex) {
        // If we have the part with only one element, then there is nothing to sort.
        if (leftIndex >= rightIndex) {
            return;
        }

        // Divide the array by two equal parts.
        // The first part refers to [lowIndex; middleIndex].
        // The second part refers to [middleIndex+1; rightIndex].
        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

        // Sort the left part.
        sortTwoParts(array, leftIndex, middleIndex);
        // Sort the right part.
        sortTwoParts(array, middleIndex + 1, rightIndex);

        // Merge two sorted parts.
        mergeTwoParts(array, leftIndex, middleIndex, rightIndex);
    }

    /**
     * Merges two parts inside the array.
     *
     * @param array       - array which parts are required to merge.
     * @param leftIndex   - the start index of the left part (inclusive).
     * @param middleIndex - the end index of the left part (inclusive).
     * @param rightIndex  - the end index of the right part (inclusive).
     * @param <T>         - it accepts arrays which has the comparable data type.
     */
    private static <T extends Comparable<? super T>> void mergeTwoParts(T[] array, int leftIndex, int middleIndex, int rightIndex) {
        // This array will contain the merged parts.
        T[] sortedPart = (T[]) new Comparable[rightIndex - leftIndex + 1];

        int k = 0; // iterator for the 'sortedPart' array.
        int i = leftIndex; // iterator for the left part.
        int j = middleIndex + 1; // iterator for the right part.

        // Merging two parts via comparing elements from left and right parts.
        while (i <= middleIndex && j <= rightIndex) {
            if (array[i].compareTo(array[j]) <= 0) {
                sortedPart[k++] = array[i++];
            } else {
                sortedPart[k++] = array[j++];
            }
        }

        // In case not all elements from the left part are inserted.
        while (i <= middleIndex) {
            sortedPart[k++] = array[i++];
        }

        // In case not all elements from the right part are inserted.
        while (j <= rightIndex) {
            sortedPart[k++] = array[j++];
        }

        // Assign to the origin array values after merging parts.
        k = 0;
        for (int l = leftIndex; l <= rightIndex; l++) {
            array[l] = sortedPart[k++];
        }
    }
}
