package week7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Roman Soldatov BS19-02
 */


/*
9
3 20
2 25
8 17
5 50
3 30
2 30
8 70
2 20
5 50
 */
public class OnlineItemBids {
    public static void main(String[] args) throws FileNotFoundException {
        // Input

        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();

        int n = 100000;

        Item[] array = new Item[n];
        for (int i = 0; i < n; i++) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
            int x = getRandom(101);
            int y = getRandom(100001);

            array[i] = new Item(i, x, y);
        }

        // Sort by low bid.
        //array = countingSort(array, Field.INDEX, true);
        array = countingSort(array, Field.MAX, true);
        array = countingSort(array, Field.LOW, false);

        //quickSort(array, 0, array.length - 1);

        /*
        for (Item item : array) {
            System.out.println(item.toString());
        }
        System.out.println();

         */


        PrintWriter printWriter = new PrintWriter(new File("output.txt"));

        for (Item item : array) {
            System.out.println(item.toString());
            printWriter.println(item.toString());
        }
        System.out.println();

        printWriter.close();

        /*
        // Sort by max bid if items are equal
        int l = 0;
        int r = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].low != array[i + 1].low) {
                if (l < r) {
                    System.out.println("l = " + l + ", r = " + r);
                }
                l = i + 1;
            }
            r = i + 1;
        }
        if (l < r) {
            System.out.println("l = " + l + ", r = " + r);
        }

         */

        // Output
        /*
        for (Item item : array) {
            System.out.print((item.index + 1) + " ");
        }

         */


    }


    public static Item[] countingSort(Item[] array, Field field, boolean ascendingOrder) {
        int range = 0;
        switch (field) {
            case INDEX:
                range = array.length + 1;
                break;
            case LOW:
                range = 101;
                break;
            case MAX:
                range = 100001;
                break;
        }

        int[] c = new int[range];
        for (Item item : array) {
            c[item.get(field)]++;
        }

        if (ascendingOrder) {
            for (int i = 0; i < c.length - 1; i++) {
                c[i + 1] += c[i];
            }
        } else {
            for (int i = c.length - 1; i > 0; i--) {
                c[i - 1] += c[i];
            }
        }

        Item[] newArray = new Item[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--c[array[i].get(field)]] = new Item(array[i]);
        }

        return newArray;
    }

    public static int getRandom(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public static void quickSort(Item[] array, int low, int high) {
        if (array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        Item pivot = array[low + (high - low) / 2];
        int i = low;
        int j = high;

        while (i <= j) {
            while (array[i].max < pivot.max) {
                i++;
            }

            while (array[j].max > pivot.max) {
                j--;
            }

            if (i <= j) {
                Item temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }
    }
}

enum Field {
    INDEX, LOW, MAX;
}


class Item {
    int index;
    int low;
    int max;

    Item(int index, int low, int max) {
        this.index = index;
        this.low = low;
        this.max = max;
    }

    Item(Item x) {
        this.index = x.index;
        this.low = x.low;
        this.max = x.max;
    }

    void copy(Item x) {
        this.index = x.index;
        this.low = x.low;
        this.max = x.max;
    }

    int get(Field field) {
        switch (field) {
            case INDEX:
                return index;
            case LOW:
                return low;
            case MAX:
                return max;
        }
        return low;
    }

    @Override
    public String toString() {
        return index + " " + low + " " + max;
    }
}
