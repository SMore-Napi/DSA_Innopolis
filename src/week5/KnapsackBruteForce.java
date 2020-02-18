package week5;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Roman Soldatov BS19-02.
 */
public class KnapsackBruteForce {
    public static void main(String[] args) {

        // Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int totalWeight = scanner.nextInt();

        int[] w = new int[n];
        int[] c = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextInt();
        }

        int maxSum = 0;
        int currentSum;
        int currentWeight;
        ArrayList<Integer> itemsToPut = new ArrayList<>();

        // Consider all combinations
        for (int i = 0; i < Math.pow(2, n); i++) {
            // Current binary representation
            ArrayList<Integer> binaryRepresentation = representInBinary(i);
            currentSum = 0;
            currentWeight = 0;

            // Calculating currentSum according to the binary representation
            for (int j = 0; j < binaryRepresentation.size(); j++) {
                if (binaryRepresentation.get(j) == 1) {
                    currentWeight += w[j];
                    currentSum += c[j];
                }
            }

            // If we found a sum which is bigger than we knew.
            // Then we save this items combination.
            if (totalWeight >= currentWeight && currentSum > maxSum) {
                maxSum = currentSum;
                itemsToPut.clear();
                for (int j = 0; j < binaryRepresentation.size(); j++) {
                    if (binaryRepresentation.get(j) == 1) {
                        itemsToPut.add(j);
                    }
                }
            }
        }

        // Output
        System.out.println(itemsToPut.size());
        for (Integer index : itemsToPut) {
            System.out.print((index + 1) + " ");
        }
    }

    static ArrayList<Integer> representInBinary(int x) {
        ArrayList<Integer> binaryRepresentation = new ArrayList<>();
        if (x == 0) {
            binaryRepresentation.add(0);
        } else {
            while (x > 0) {
                if (x % 2 != 0) {
                    binaryRepresentation.add(1);
                } else {
                    binaryRepresentation.add(0);
                }
                x /= 2;
            }
        }

        return binaryRepresentation;
    }
}
