package week12.leetcode;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int H) {
        /*
        The 'minimal' number of bananas which Koko can eat is 1.
        It is necessary to eat at most 'maximum' number of bananas during an hour (the least possible time to eat all piles).
        So, the possible answer is between 'minimal' and 'maximum' values
        (because Koko wants to eat everything less than 'H' hours, i.e. less than 'maximum' but she does not want to hurry,
        i.e. we need to find the minimum suitable number of pile/hour which is greater than 'minimal').
        To find the optimal answer between 'minimal' and 'maximum' we can use the binary search.
         */

        int left = 1; // the minimal value of a range to search
        int right = Integer.MAX_VALUE; // the maximum value of a range to search

        // Do the Binary Search
        while (left < right) {
            // Find the middle number of a current range to search
            int K = (right - left) / 2 + left;

            // Calculate the required number of hours to eat all piles
            int hours = getHours(piles, K, H);

            // In case it requires more time to eat everything than we have
            if (hours > H) {
                left = K + 1;
            } // In case Koko can eat all piles less than H hours.
            // Then we try to minimize the possible number K via continuing the binary search
            else {
                right = K;
            }
        }

        return right;
    }


    // Additional method to calculate how many time does the Koko need to eat all piles, if she eats K bananas per hour.
    private int getHours(int[] piles, int K, int maxHours) {
        int hours = 0;

        for (int pile : piles) {
            // If pile <= K, then it requires 1 hour.
            // If pile > K, then it requires pile / K hours. The result after division should be rounded up.
            hours += (pile - 1) / K + 1;

            // There is no any point if the amount of time to eat piles requires more hours than the Koko has.
            if (hours > maxHours) {
                return Integer.MAX_VALUE;
            }
        }

        return hours;
    }
}
