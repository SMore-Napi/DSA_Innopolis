package week5;


import java.util.LinkedList;
import java.util.Scanner;

/**
 * Roman Soldatov BS19-02
 */
public class KnapsackProblem {
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

        int[][] d = new int[totalWeight + 1][n];

        // Calculating the max value
        for (int i = 0; i < totalWeight + 1; i++) {
            for (int j = 0; j < n; j++) {
                int notInclude = getValue(i, j - 1, d);
                int include = getValue(i - w[j], j - 1, d) + c[j];

                if (i - w[j] >= 0) {
                    d[i][j] = Math.max(notInclude, include);
                } else {
                    d[i][j] = notInclude;
                }
            }
        }

        // Finding items which have to be taken
        LinkedList<Integer> indices = new LinkedList<>();
        int sum = d[totalWeight][n - 1];
        int weight = totalWeight;

        for (int i = n - 1; i >= 0 && sum > 0; i--) {
            if (sum != getValue(weight, i-1, d)){
                indices.addFirst(i + 1);
                sum -= c[i];
                weight -= w[i];
            }
        }

        // Output
        System.out.println(indices.size());
        for (Integer i : indices) {
            System.out.print(i + " ");
        }
    }

    static int getValue(int i, int j, int[][] d) {
        if (i < 0 || j < 0) {
            return 0;
        } else return d[i][j];
    }
}
