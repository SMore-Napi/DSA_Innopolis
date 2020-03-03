package week7;

import java.util.Scanner;

/**
 * Roman Soldatov BS19-02
 */
public class OnlineItemBids {
    public static void main(String[] args) {
        // Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Filling the array
        Item[] array = new Item[n];
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            array[i] = new Item(i, x, y);
        }

        // Sort by max bid.
        array = countingSort(array, Field.MAX, true);

        // Sort by low bid.
        array = countingSort(array, Field.LOW, false);

        // Output
        for (Item item : array) {
            System.out.print((item.index + 1) + " ");
        }
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

        // Count amount of different numbers
        int[] c = new int[range];
        for (Item item : array) {
            c[item.get(field)]++;
        }

        // Count prefix or suffix
        if (ascendingOrder) {
            for (int i = 0; i < c.length - 1; i++) {
                c[i + 1] += c[i];
            }
        } else {
            for (int i = c.length - 1; i > 0; i--) {
                c[i - 1] += c[i];
            }
        }

        // Fill new array with sorted numbers
        Item[] newArray = new Item[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--c[array[i].get(field)]] = new Item(array[i]);
        }

        return newArray;
    }
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
}

enum Field {
    INDEX, LOW, MAX;
}
