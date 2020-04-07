package week12.leetcode;

public class CapacityToShipPackagesWithin_D_Days {

    public int shipWithinDays(int[] weights, int D) {
        int min = 1;
        int max = 0;

        for (int weight : weights) {
            if (min < weight) {
                min = weight;
            }
            max += weight;
        }

        while (min < max) {
            int capacity = (max - min) / 2 + min;
            int days = getDays(weights, capacity, D);

            if (days > D) {
                min = capacity + 1;
            } else {
                max = capacity;
            }
        }

        return max;
    }

    private int getDays(int[] weights, int capacity, int maxDays) {
        int days = 0;
        int current = 0;

        for (int weight : weights) {
            if (current + weight > capacity) {
                days += 1;
                current = 0;

                if (days > maxDays) {
                    return Integer.MAX_VALUE;
                }
            }

            current += weight;
        }

        if (current > 0) {
            days += 1;
        }

        return days;
    }
}
