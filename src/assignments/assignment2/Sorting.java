package assignments.assignment2;

import java.util.Arrays;

/**
 * @author Roman Soldatov BS19-02.
 * 2.2 Sorting.
 * Implementing the "Merge Sort" algorithm.
 * The time complexity of this algorithm is O(nlogn).
 * It is the out-of-place implementation.
 * It is stable.
 * //todo check is it a correct description
 */
public class Sorting {
    public static void main(String[] args) {
        int [] array = getRandomArray(100, 15);
        System.out.println(Arrays.toString(array));
        MergeSort.mergeSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static int[] getRandomArray(int maxLength, int maxValue) {

        int length = (int) (Math.random() * maxLength);
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }

        return array;
    }
}

class MergeSort {
    private static int[] helper;

    public static void mergeSort(int[] arr) {
        helper = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2; // граница разбиения

        sort(arr, low, mid); // сортируем левую часть
        sort(arr, mid + 1, high); // сортируем правую часть

        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            helper[k] = arr[k];
        }
        int k = low;
        while (i <= mid && j <= high) {
            if (helper[i] < helper[j]) {
                arr[k++] = helper[i++];
            } else {
                arr[k++] = helper[j++];
            }
        }
        // выписываем подряд из левой части, если остались
        while (i <= mid) {
            arr[k++] = helper[i++];
        }
        while (j <= high) {
            arr[k++] = helper[j++];
        }
    }
}

