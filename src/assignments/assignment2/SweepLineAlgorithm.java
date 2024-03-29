package assignments.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// Uncomment it to use junit tests.
/*
import org.junit.Test;
import static org.junit.Assert.*;
*/

/**
 * @author Roman Soldatov BS19-02.
 * Submission number: 74222898
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/272963/submission/74222898
 * <p>
 * 2.4 Sweep line algorithm.
 * This class contains the main method to launch it on Codeforces and the sweepLineAlgorithm.
 * <p>
 * Also, this class contains junit methods for testing the MergeSorter and AVLTree classes, because it is a public class,
 * and we are not allowed to provide a program in separate files. It requires to import the junit library.
 * Maybe your IDE does not import this library automatically. So, then this class won't compile because of errors.
 * So, just in case I commented it out.
 * Well, you can uncomment the code inside this class and import junit library to check tests.
 * Tests are at the very bottom of this class.
 */
public class SweepLineAlgorithm {

    public static void main(String[] args) throws IOException {
        // Input points.
        Point[] points = input();

        // Run the Sweep Line Algorithm.
        LineSegment[] lines = sweepLineAlgorithm(points);

        // Output.
        PrintWriter printWriter = new PrintWriter(System.out);
        if (lines == null) {
            printWriter.write("NO INTERSECTIONS\n");
        } else {
            printWriter.write("INTERSECTION\n");
            printWriter.write(lines[0].toString() + "\n");
            printWriter.write(lines[1].toString() + "\n");
        }

        printWriter.close();
    }

    /**
     * Input points.
     * O(n).
     *
     * @return array of points.
     */
    public static Point[] input() throws IOException {

        int n = nextInt(); // number of line segments.
        Point[] points = new Point[2 * n];

        for (int i = 0; i < 2 * n; i += 2) {
            int xP = nextInt();
            int yP = nextInt();
            int xQ = nextInt();
            int yQ = nextInt();

            // Add the left end of a line segment.
            points[i] = new Point(xP, yP, Point.Order.FIRST);
            // Add the right end of a line segment.
            points[i + 1] = new Point(xQ, yQ, Point.Order.SECOND);

            // Connect these two points with each other because they belong to the same segment.
            points[i].neighbour = points[i + 1];
            points[i + 1].neighbour = points[i];
        }

        return points;
    }

    /**
     * The main Sweep line algorithm.
     * It scans all line segments and determines if any two segments intersect.
     * It takes points as an input
     * O(n*log(n)).
     *
     * @param points - points of line segments.
     * @return an array of two LineSegment objects which intersect with each other; null - if there are no any intersect segments.
     */
    public static LineSegment[] sweepLineAlgorithm(Point[] points) {

        // Sort points by x coordinate using the merge-sort.
        // If x coordinates of two points are equal,
        // then the left point of a segment comes firstly.
        // The comparator is written as the lambda expression.
        MergeSorter.sort(points, (o1, o2) -> {
            if (o1.x == o2.x) {
                if (o1.isLeftPoint()) {
                    return -1;
                }
                return 1;
            }
            return o1.x - o2.x;
        });

        // The tree will be saving all left points, comparing them with their predecessor and successor.
        // If we meet the right point, then we compare its predecessor and successor,
        // and then we delete this line segment from a tree.
        AVLTree<Point, Point> tree = new AVLTree<>();

        // Observe each point.
        for (Point point : points) {

            // In case it is the start point of a line segment.
            if (point.isLeftPoint()) {
                tree.add(point, point);

                Point predecessor = tree.getPredecessorByKey(point);
                Point successor = tree.getSuccessorByKey(point);

                LineSegment line = new LineSegment(point);

                // Compare the current point with its predecessor.
                if (predecessor != null) {
                    LineSegment line1 = new LineSegment(predecessor);
                    if (SegmentIntersection.areIntersect(line, line1)) {
                        return new LineSegment[]{line, line1};
                    }
                }

                // Compare the current point with its successor.
                if (successor != null) {
                    LineSegment line2 = new LineSegment(successor);
                    if (SegmentIntersection.areIntersect(line, line2)) {
                        return new LineSegment[]{line, line2};
                    }
                }

            } // In case it is the end point of a line segment.
            else {

                Point predecessor = tree.getPredecessorByKey(point);
                Point successor = tree.getSuccessorByKey(point);

                // Compare a predecessor and a successor of a current point.
                if (predecessor != null && successor != null) {
                    LineSegment line1 = new LineSegment(predecessor);
                    LineSegment line2 = new LineSegment(successor);
                    if (SegmentIntersection.areIntersect(line1, line2)) {
                        return new LineSegment[]{line1, line2};
                    }
                }

                // Delete the left point of current line segment.
                tree.delete(point.neighbour);
            }
        }

        return null;
    }

    // Some methods for fast input reading to pass the last test on Codeforces.
    // I took it from here https://pastebin.com/2y4kFUzp
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer = new StringTokenizer("");

    public static String nextToken() throws IOException {
        while (!stringTokenizer.hasMoreTokens()) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        }
        return stringTokenizer.nextToken();
    }

    public static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    //***** Tests*****/
    // Uncomment it to use junit tests. MergeSort checker.
    /*
    @Test
    public void testEmptyArray() {
        Integer[] array = {};
        MergeSorter.sort(array, Integer::compareTo);
        boolean sorted = isSorted(array, Integer::compareTo);
        assertTrue(sorted);
    }

    @Test
    public void testArrayAscendingOrder() {
        Double[] array = {1.0, 2.5, 3.3, 4.7, 5.1, 5.1, 6.54, 7.21, 8.23, 9.76, 10.74};
        MergeSorter.sort(array, Double::compareTo);
        boolean sorted = isSorted(array, Double::compareTo);
        assertTrue(sorted);
    }

    @Test
    public void testArrayDecreasingOrder() {
        String[] array = {"yte", "hrd", "bbb", "afd", "abc", "abc", "aaa"};
        MergeSorter.sort(array, String::compareTo);
        boolean sorted = isSorted(array, String::compareTo);
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

    // Uncomment it to use junit tests. AVLTree checker.
    /*
    // Test getPredecessorByKey & getSuccessorByKey methods.
    @Test
    public void testGetPredecessorMethod1Set1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "i";
        assertEquals(tree.getPredecessorByKey(5), correctResult);
    }

    @Test
    public void testGetPredecessorMethod2Set1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "a";
        assertEquals(tree.getPredecessorByKey(7), correctResult);
    }

    @Test
    public void testGetSuccessorMethod1Set1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "c";
        assertEquals(tree.getSuccessorByKey(8), correctResult);
    }

    @Test
    public void testGetSuccessorMethod2Set1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "a";
        assertEquals(tree.getSuccessorByKey(5), correctResult);
    }

    // Test add(K key, V value) method.
    @Test
    public void testAddMethodSet1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testAddMethodSet2() {
        AVLTree<Integer, String> tree = set2();

        String correctResult = "[[1, a, 0], [2, b, 1], [3, c, 0], [4, d, 3], [5, e, 0], [6, f, 1], [7, g, 0], [8, h, 2], [9, i, 0], [10, j, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testAddMethodSet3() {
        AVLTree<Integer, String> tree = set3();

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    // Test delete(K key) method.
    @Test
    public void testDeleteRootSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(9);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteNonExistNodeSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(18);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftLeafSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(7);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [8, f, 0], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightLeafSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(2);

        String correctResult = "[[1, d, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftChildSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(5);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightChildSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(10);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteTwoChildren1Set1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(3);

        String correctResult = "[[1, d, 0], [2, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteTwoChildren2Set1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(9);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftLeftCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(5);

        String correctResult = "[[3, f, 0], [7, e, 1], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftRightCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(8);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightRightCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(50);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightLeftCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(20);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteSeveralNodesSet2() {
        AVLTree<Integer, String> tree = set2();
        tree.delete(2);
        tree.delete(10);
        tree.delete(6);
        tree.delete(8);
        tree.delete(4);

        String correctResult = "[[1, b, 0], [3, d, 1], [5, f, 0], [7, h, 2], [9, j, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }
    */

    // Some helper methods for junit tests below.

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
            MergeSorter.sort(array, Integer::compareTo);

            // If the array is not sorted, then return false.
            boolean sorted = isSorted(array, Integer::compareTo);
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
     * @param array      - array to test.
     * @param comparator - comparator by which we can compare array's elements. It must be the same type as the array.
     * @param <T>        - data type which the array contains.
     * @return true if the array is sorted, false - otherwise.
     */
    private <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set1() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(6, "a");
        tree.add(3, "b");
        tree.add(9, "c");
        tree.add(1, "d");
        tree.add(5, "e");
        tree.add(8, "f");
        tree.add(10, "g");
        tree.add(2, "h");
        tree.add(4, "i");
        tree.add(7, "j");
        tree.add(11, "k");
        return tree;
    }

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set2() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(1, "a");
        tree.add(2, "b");
        tree.add(3, "c");
        tree.add(4, "d");
        tree.add(5, "e");
        tree.add(6, "f");
        tree.add(7, "g");
        tree.add(8, "h");
        tree.add(9, "i");
        tree.add(10, "j");
        tree.add(11, "k");
        return tree;
    }

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set3() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(50, "a");
        tree.add(25, "b");
        tree.add(10, "c");
        tree.add(5, "d");
        tree.add(7, "e");
        tree.add(3, "f");
        tree.add(30, "g");
        tree.add(20, "h");
        tree.add(8, "i");
        tree.add(15, "j");
        return tree;
    }
}

/**
 * 2.1 Segment intersection.
 * <p>
 * Importance of line segment intersection.
 * First of all, line intersection is the geometrical common task to solve more complicated problems.
 * For example, if we know three points lying on a circle, we can calculate the centre of this circle using lines intersection.
 * Another example is the finding the convex hull of given points.
 * We can use the "Graham scan algorithm", "Gift wrapping algorithm" or the "Chan's algorithm".
 * These algorithms require to know about lines reflection and rotation to find the minimal convex hull.
 * Lines reflection and rotation are algorithms based on the line intersection. That's why it is really important basis algorithm.
 * What's more, finding the convex hull is the geometry task with practical usage.
 * For instance, we can build the antenna network setting receivers in some territory with the minimal number of this equipment.
 * </p>
 * <p>
 * Sweep Line algorithm summary.
 * As was mentioned in Assignment description the Sweep Line algorithm checks if two any line segments intersect each other.
 * This algorithm checks it by O(n*log(n)). Firstly, we can sort a list of points by x coordinate.
 * If x coordinate of two points is the same, then we compare them by belonging to the segment (is it a left point or a right point).
 * The sorting will be done by O(n*log(n)). Then, we consider all points from left to right (like passing a vertical line).
 * We add a line segment to the tree sorting by y's coordinate.
 * If two segments intersect, then it means that neighbour segments (according to y's sorting) in an array intersect.
 * In a tree representation, it is the predecessor and successor of the current point.
 * So, if we sort points by x coordinate, but want to find adjacent line segments by y coordinate, then we can use a balanced tree,
 * because each operation for finding the predecessor or the successor costs O(log(n)).
 * Thus, checking n segments requires O(log(n)) for each segment.
 * Therefore, checking all line segments requires O(n*log(n)). Also, it is necessary to sort points firstly.
 * So, the total time is n*log(n) + n*log(n) = O(n*log(n)).
 * </p>
 * This class contains a static method to check if two line segments have an intersection.
 * There also some private helper methods.
 * The algorithm was adopted from pseudocode in "Introduction to Algorithms", T.H. Cormen p. 1018.
 * The line intersection method was tested here:
 * https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=17&id_topic=25&id_problem=135
 */
class SegmentIntersection {

    /**
     * Check if two line segments have an intersection.
     * O(1)
     *
     * @param line1 - the first line segment.
     * @param line2 - the second line segment.
     * @return true if these line segments intersect, false - otherwise.
     */
    public static boolean areIntersect(LineSegment line1, LineSegment line2) {
        Point point1 = line1.start;
        Point point2 = line1.end;
        Point point3 = line2.start;
        Point point4 = line2.end;

        int direction1 = getDirection(point3, point4, point1);
        int direction2 = getDirection(point3, point4, point2);
        int direction3 = getDirection(point1, point2, point3);
        int direction4 = getDirection(point1, point2, point4);

        if (((direction1 > 0 && direction2 < 0) || (direction1 < 0 && direction2 > 0))
                && ((direction3 > 0 && direction4 < 0) || (direction3 < 0 && direction4 > 0))) {
            return true;
        } else if (direction1 == 0 && isBelongToSegment(point1, line2)) {
            return true;
        } else if (direction2 == 0 && isBelongToSegment(point2, line2)) {
            return true;
        } else if (direction3 == 0 && isBelongToSegment(point3, line1)) {
            return true;
        } else if (direction4 == 0 && isBelongToSegment(point4, line1)) {
            return true;
        }
        return false;
    }

    /**
     * Calculate if the one vector is placed on the right than another.
     * It uses the cross-product of two vectors to calculate the sign of direction.
     * If the sign is positive, then the first vector appears firstly than another in counter-clockwise direction.
     * If the sign is negative - otherwise.
     * If the direction is zero, then these vectors are collinear.
     * To get two vectors we pass the origin point (regarding which we observe them)
     * and two other points (the end of vectors) as arguments.
     * O(1)
     *
     * @param origin - origin point from which two vectors start.
     * @param point1 - the end of the first vector.
     * @param point2 - the end of the second vector.
     * @return zero if two vectors are collinear; positive value if the first vector is righter than the second vector; negative value - otherwise.
     */
    private static int getDirection(Point origin, Point point1, Point point2) {
        Point vector1 = new Point(point1.x - origin.x, point1.y - origin.y, Point.Order.SECOND);
        Point vector2 = new Point(point2.x - origin.x, point2.y - origin.y, Point.Order.SECOND);

        return vector1.y * vector2.x - vector1.x * vector2.y;
    }

    /**
     * Check if some point belongs to a line segment.
     * O(1)
     *
     * @param point   - point to check.
     * @param segment - line segment.
     * @return true if this point lies on the line segment, false - otherwise.
     */
    private static boolean isBelongToSegment(Point point, LineSegment segment) {
        int startX = Math.min(segment.start.x, segment.end.x);
        int startY = Math.min(segment.start.y, segment.end.y);
        int endX = Math.max(segment.start.x, segment.end.x);
        int endY = Math.max(segment.start.y, segment.end.y);

        return startX <= point.x && point.x <= endX && startY <= point.y && point.y <= endY;
    }
}

/**
 * Class to store two points.
 * One of them - the start point of a segment, another - the end.
 */
class LineSegment {
    Point start;
    Point end;

    public LineSegment(Point point) {
        this.start = point;
        this.end = point.neighbour;
    }

    @Override
    public String toString() {
        if (start.inputOrder == Point.Order.FIRST) {
            return start.toString() + " " + end.toString();
        }
        return end.toString() + " " + start.toString();
    }
}

/**
 * Class to store x and y coordinates of some point.
 * This class is comparable. So, we can compare any two Point objects.
 * Actually, the compareTo() method contains the rule of comparing two points in Sweep Line Algorithm.
 */
class Point implements Comparable<Point> {
    int x;
    int y;
    // The point knows about another point which belongs to the same line.
    Point neighbour;
    // The order of a point represents if this point has been read as the first or the last point
    // of the current line segment. It allows us to preserve the input and output order as the same.
    Order inputOrder;

    public Point(int x, int y, Order inputOrder) {
        this.x = x;
        this.y = y;
        this.inputOrder = inputOrder;
    }

    /**
     * Determines if this point is placed to the left than it's neighbour in a geometrical representation.
     *
     * @return true if this point is to the left; false - otherwise; null - if it is a single point (does not belong to any line segment)
     */
    public Boolean isLeftPoint() {
        if (neighbour == null) {
            return null;
        }

        if (x == neighbour.x) {
            return inputOrder == Order.FIRST;
        }

        return x < neighbour.x;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    /**
     * Compares two points by the rule of the Sweep Line Algorithm
     *
     * @param o - another point.
     * @return positive value if this point is higher than another, or to the left of another if the ordinates are the same.
     */
    @Override
    public int compareTo(Point o) {
        if (this.y == o.y) {
            return this.x - o.x;
        }
        return this.y - o.y;
    }

    /**
     * Enumeration to define if this point is the start or the end of a line
     */
    enum Order {
        FIRST, SECOND
    }
}

/**
 * 2.2 Sorting. Implementing the "Merge Sort" algorithm.
 * <p>
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
     * @param array      - array to sort.
     * @param comparator - the comparator by which values will be compared. It must be the same data type as the array.
     * @param <T>        - it accepts arrays with generic data type.
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        sortTwoParts(array, 0, array.length - 1, comparator);
    }

    /**
     * Divide the array into two parts, sort them separately, and merge this sorted parts.
     *
     * @param array      - array which part is required to sort.
     * @param leftIndex  - the start index of the part (inclusive).
     * @param rightIndex - the end index of the part (inclusive).
     * @param comparator - the comparator by which values will be compared. It must be the same data type as the array.
     * @param <T>        - it accepts arrays with generic data type.
     */
    private static <T> void sortTwoParts(T[] array, int leftIndex, int rightIndex, Comparator<T> comparator) {
        // If we have the part with only one element, then there is nothing to sort.
        if (leftIndex >= rightIndex) {
            return;
        }

        // Divide the array by two equal parts.
        // The first part refers to [lowIndex; middleIndex].
        // The second part refers to [middleIndex+1; rightIndex].
        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

        // Sort the left part.
        sortTwoParts(array, leftIndex, middleIndex, comparator);
        // Sort the right part.
        sortTwoParts(array, middleIndex + 1, rightIndex, comparator);

        // Merge two sorted parts.
        mergeTwoParts(array, leftIndex, middleIndex, rightIndex, comparator);
    }

    /**
     * Merges two parts inside the array.
     *
     * @param array       - array which parts are required to merge.
     * @param leftIndex   - the start index of the left part (inclusive).
     * @param middleIndex - the end index of the left part (inclusive).
     * @param rightIndex  - the end index of the right part (inclusive).
     * @param comparator  - the comparator by which values will be compared. It must be the same data type as the array.
     * @param <T>         - it accepts arrays with generic data type.
     */
    private static <T> void mergeTwoParts(T[] array, int leftIndex, int middleIndex, int rightIndex, Comparator<T> comparator) {
        // This array will contain the merged parts.
        T[] sortedPart = (T[]) new Object[rightIndex - leftIndex + 1];

        int k = 0; // iterator for the 'sortedPart' array.
        int i = leftIndex; // iterator for the left part.
        int j = middleIndex + 1; // iterator for the right part.

        // Merging two parts via comparing elements from left and right parts.
        while (i <= middleIndex && j <= rightIndex) {
            if (comparator.compare(array[i], array[j]) <= 0) {
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

/**
 * 2.3 Storage.
 * Implementing the AVL tree.
 * <p>
 * Class with AVL tree implementation.
 *
 * @param <K> key by which nodes will be sorted.
 * @param <V> value of a node to store.
 */
class AVLTree<K extends Comparable<K>, V> {
    private Node root;

    /**
     * Insertion of a new node by its key and value.
     * It compares by its key.
     * If we insert the node with the same key, then it goes to the right subtree.
     * The insertion is the same as an insertion in BST.
     * However, we do the trinode restructuring after insertion.
     * As we insert the node into balanced tree and the rebalancing takes the constant time,
     * then the method add(K key, V value) takes O(log(n)) time complexity, where n - count of all nodes in a tree.
     *
     * @param key   - key by which the node will be compared.
     * @param value - value to store in a node.
     */
    public void add(K key, V value) {
        // If the tree is empty.
        if (root == null) {
            root = new Node(key, value, null);
        } // Reassign the reference to the node after insertion and rebalancing.
        else {
            root = add(key, value, root);
        }
    }

    /**
     * Deletion of a node by its key.
     * If the node with such key doesn't exist then don't change the tree.
     * The deletion is the same as a deletion in BST.
     * However, we do the trinode restructuring after deletion.
     * As we search the node to delete in a balanced tree and the rebalancing takes the constant time,
     * then the method delete(K key) takes O(log(n)) time complexity, where n - count of all nodes in a tree.
     *
     * @param key - key which the node to delete has.
     */
    public void delete(K key) {
        root = delete(key, root);
    }

    /**
     * Return the value which is followed after the current key in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param key - key of a node which successor is required to find.
     * @return the successor's value.
     */
    public V getSuccessorByKey(K key) {
        Node successor = getSuccessor(getNodeByKey(key));
        if (successor == null) {
            return null;
        }
        return successor.value;
    }

    /**
     * Return the node which is followed before the current key in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param key - key of a node which predecessor is required to find.
     * @return the predecessor's value.
     */
    public V getPredecessorByKey(K key) {
        Node predecessor = getPredecessor(getNodeByKey(key));
        if (predecessor == null) {
            return null;
        }
        return predecessor.value;
    }

    /**
     * Return the nodes of a tree in a preorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesPreorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.PREORDER);
        return list;
    }

    /**
     * Return the nodes of a tree in a inorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesInorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.INORDER);
        return list;
    }

    /**
     * Return the nodes of a tree in a postorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesPostorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.POSTORDER);
        return list;
    }

    /**
     * Insert a node inside the current subtree.
     * O(log(n))
     *
     * @param key     - key by which the node will be compared.
     * @param value   - value to store inside the node.
     * @param subTree - the start node of the subtree where the node will be inserted.
     * @return the new head-node of this subtree after the rebalancing.
     */
    private Node add(K key, V value, Node subTree) {

        // If the key is less than the node's key.
        if (key.compareTo(subTree.key) < 0) {
            // Insert a new node as a new left subtree.
            if (subTree.leftNode == null) {
                subTree.leftNode = new Node(key, value, subTree);
            } else {
                // Go to the left subtree.
                subTree.leftNode = add(key, value, subTree.leftNode);
            }
        } // If the key is bigger or equal than the node's key.
        else {
            // Insert a new node as a new right subtree.
            if (subTree.rightNode == null) {
                subTree.rightNode = new Node(key, value, subTree);
            } else {
                // Go to the right subtree.
                subTree.rightNode = add(key, value, subTree.rightNode);
            }
        }

        // Reassign the reference to the new root of this subtree.
        subTree = restoreBalance(subTree);
        return subTree;
    }

    /**
     * Delete a node inside the current subtree.
     * O(log(n))
     *
     * @param key     - key by which the node will be compared.
     * @param subTree - the start node of the subtree where the node will be deleted.
     * @return the new head-node of this subtree after the rebalancing.
     */
    private Node delete(K key, Node subTree) {
        // If such node with this key doesn't exist in a subtree.
        if (subTree == null) {
            return null;
        }

        // If the key is less than the subtree's key.
        if (key.compareTo(subTree.key) < 0) {
            // Delete and reassign the head-node in a left subtree.
            subTree.leftNode = delete(key, subTree.leftNode);
        } // If the key is bigger than the subtree's key.
        else if (key.compareTo(subTree.key) > 0) {
            // Delete and reassign the head-node in a right subtree.
            subTree.rightNode = delete(key, subTree.rightNode);
        } // If we found the node with this key.
        else {
            // If the node has only one child or it's a leaf.
            if (subTree.leftNode == null || subTree.rightNode == null) {
                if (subTree.leftNode != null) {
                    subTree = subTree.leftNode;
                } else {
                    subTree = subTree.rightNode;
                }
            } // If the node has two children.
            else {
                // Find the predecessor.
                Node predecessor = getPredecessor(subTree);
                // Swap it with the node.
                subTree.key = predecessor.key;
                // Delete the node.
                subTree.leftNode = delete(subTree.key, subTree.leftNode);

                // Alternative way.
                /*
                // Find the successor.
                Node successor = getSuccessor(subTree);
                // Swap it with the node.
                subTree.key = successor.key;
                // Delete the node.
                subTree.rightNode = delete(subTree.key, subTree.rightNode);
                 */
            }
        }

        // Do the rebalancing after the deletion.
        if (subTree != null) {
            subTree = restoreBalance(subTree);
        }

        // Reassign the reference to the new root of this subtree.
        return subTree;
    }

    /**
     * Return the node which is followed after the 'subTree' node in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param subTree - the node which succsessor is required to find.
     * @return - the successor of the 'subTree' node.
     */
    private Node getSuccessor(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;

        // Find the minimum child-node in a right subtree.
        if (x.rightNode != null) {
            return getMinimumNode(x.rightNode);
        }

        // If the node doesn't have the right child,
        // then the successor is a node such as it's left child is a subtree in which our node is placed.
        Node y = x.parentNode;
        while (y != null && x.equals(y.rightNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    /**
     * Return the node which is followed before the 'subTree' node in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param subTree - the node which predecessor is required to find.
     * @return - the predecessor of the 'subTree' node.
     */
    private Node getPredecessor(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;

        // Find the maximum child-node in a right subtree.
        if (x.leftNode != null) {
            return getMaximumNode(x.leftNode);
        }

        // If the node doesn't have the left child,
        // then the predecessor is a node such as it's right child is a subtree in which our node is placed.
        Node y = x.parentNode;
        while (y != null && x.equals(y.leftNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    /**
     * Return the node which is minimum comparing with other nodes of this subtree.
     * O(log(n)).
     *
     * @param subTree - subtree in which the minimum node is required to find.
     * @return the left most child of this subtree.
     */
    private Node getMinimumNode(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;
        while (x.leftNode != null) {
            x = x.leftNode;
        }
        return x;
    }

    /**
     * Return the node which is maximum comparing with other nodes of this subtree.
     * O(log(n)).
     *
     * @param subTree - subtree in which the maximum node is required to find.
     * @return the right most child of this subtree.
     */
    private Node getMaximumNode(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;
        while (x.rightNode != null) {
            x = x.rightNode;
        }
        return x;
    }

    /**
     * Traversal of all nodes in a specific order.
     * Fill the ArrayList of nodes which have already been visited.
     * O(n) as we visit all nodes.
     *
     * @param node  - the start node to travel.
     * @param list  - ArrayList to write the nodes.
     * @param order - specific rule by which the traversal will be done.
     */
    private void traversalNodes(Node node, ArrayList<Node> list, AVLTree.TraversalOrder order) {
        if (node != null) {
            switch (order) {
                // Visit the node, Preorder Left, Preorder Right.
                case PREORDER:
                    list.add(node);
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    break;
                // Inorder Left, Visit the node, Inorder Right.
                case INORDER:
                    traversalNodes(node.leftNode, list, order);
                    list.add(node);
                    traversalNodes(node.rightNode, list, order);
                    break;
                // Postorder Left, Postorder Right, Visit the node.
                case POSTORDER:
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    list.add(node);
                    break;
            }
        }
    }

    /**
     * Return the height of a node.
     * O(1)
     *
     * @param node - node which height is required to know.
     * @return node's height if it exists, -1 - otherwise.
     */
    private int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    /**
     * Update the height of the node according to it's right and left children heights.
     * O(1)
     *
     * @param node - node which height is required to recalculate.
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.leftNode), getHeight(node.rightNode));
    }

    /**
     * Calculate the difference between the right-child height and left-child height.
     * O(1)
     *
     * @param parentNode - node which balance between two children nodes is required to find.
     * @return the balance of the 'parentNode'.
     */
    private int getBalanceBetweenNodes(Node parentNode) {
        if (parentNode == null) {
            return 0;
        }
        return getHeight(parentNode.rightNode) - getHeight(parentNode.leftNode);
    }

    /*
     *              (rootNode)
     *              /         \
     *      (newRootNode)     (subtree3)
     *     /            \
     * (subtree1) (subtree2)
     *
     * Becomes to...
     *
     *        (newRootNode)
     *         /       \
     * (subtree1)     (rootNode)
     *               /         \
     *          (subtree2) (subtree3)
     */

    /**
     * The right rotation.
     * O(1)
     *
     * @param rootNode - the root of a subtree which nodes are unbalanced.
     * @return the reference to the new root of this subtree.
     */
    private Node rightRotate(Node rootNode) {
        // Reassign the references.
        Node newRootNode = rootNode.leftNode;
        rootNode.leftNode = newRootNode.rightNode;
        newRootNode.rightNode = rootNode;

        // Swap the parents as the newRootNode now is a parent of rootNode.
        newRootNode.parentNode = rootNode.parentNode;
        rootNode.parentNode = newRootNode;

        // Update heights after rotation.
        updateHeight(rootNode);
        updateHeight(newRootNode);

        return newRootNode;
    }

    /*
     *        (rootNode)
     *         /       \
     * (subtree1)     (newRootNode)
     *               /         \
     *          (subtree2) (subtree3)
     *
     *
     *
     * Becomes to...
     *              (newRootNode)
     *              /            \
     *      (rootNode)     (subtree3)
     *     /         \
     * (subtree1) (subtree2)
     *
     *
     */

    /**
     * The left rotation.
     * O(1)
     *
     * @param rootNode - the root of a subtree which nodes are unbalanced.
     * @return the reference to the new root of this subtree.
     */
    private Node leftRotate(Node rootNode) {
        // Reassign the references.
        Node newRootNode = rootNode.rightNode;
        rootNode.rightNode = newRootNode.leftNode;
        newRootNode.leftNode = rootNode;

        // Swap the parents as the newRootNode now is a parent of rootNode.
        newRootNode.parentNode = rootNode.parentNode;
        rootNode.parentNode = newRootNode;

        // Update heights after rotation.
        updateHeight(rootNode);
        updateHeight(newRootNode);

        return newRootNode;
    }

    /**
     * Check if the balance (difference between right children's height and left children's height)
     * of the subtree is correct (the difference is -1, 0 or 1)
     * and do rebalancing if it's not.
     * O(1)
     *
     * @param subTree - the subtree which balance is required to restore
     * @return the reference to the new root of this subtree.
     */
    private Node restoreBalance(Node subTree) {
        // Get the balance between subtree's children.
        updateHeight(subTree);
        int balance = getBalanceBetweenNodes(subTree);

        // Do the rebalancing if the difference of heights is more than 1.
        if (balance < -1) {
            // left-right case.
            if (getHeight(subTree.leftNode.leftNode) <= getHeight(subTree.leftNode.rightNode)) {
                subTree.leftNode = leftRotate(subTree.leftNode);
            }
            // left-left case or the continuation of the left-right case (if the condition below was true)
            subTree = rightRotate(subTree);
        } else if (balance > 1) {
            // right-left case.
            if (getHeight(subTree.rightNode.rightNode) <= getHeight(subTree.rightNode.leftNode)) {
                subTree.rightNode = rightRotate(subTree.rightNode);
            }
            // right-right case or the continuation of the right-left case (if the condition below was true)
            subTree = leftRotate(subTree);
        }

        // Reassign the reference to the new root of this subtree.
        return subTree;
    }

    /**
     * Find the node by it's key.
     * O(log(n)) in a worst case.
     *
     * @param key - key of this node.
     * @return the node if it's found, null - otherwise.
     */
    private Node getNodeByKey(K key) {
        return getNodeByKey(key, root);
    }

    /**
     * Find the node by it's key.
     * O(log(n)) in a worst case.
     *
     * @param key     - key of this node.
     * @param subTree - subtree in which the node is required to find.
     * @return the node if it's found, null - otherwise.
     */
    private Node getNodeByKey(K key, Node subTree) {
        if (subTree == null) {
            return null;
        }
        if (subTree.key.equals(key)) {
            return subTree;
        }
        if (key.compareTo(subTree.key) < 0) {
            if (subTree.leftNode == null) {
                return null;
            }
            return getNodeByKey(key, subTree.leftNode);
        } else {
            if (subTree.rightNode == null) {
                return null;
            }
            return getNodeByKey(key, subTree.rightNode);
        }
    }

    /**
     * Inner class to store nodes in a tree.
     */
    class Node {
        Node parentNode; // a node by which the current node is connected.
        Node leftNode; // a left child of the current node.
        Node rightNode; // a right child of the current node.

        K key; // a comparable key.
        V value; // a value to store
        int height; // a current height. It says how many heirs this node has.

        Node(K key, V value, Node parentNode) {
            this.key = key;
            this.value = value;
            this.parentNode = parentNode;
        }

        @Override
        public String toString() {
            return "[" + key.toString() + ", " + value.toString() + ", " + height + "]";
        }
    }

    /**
     * Enumeration of cases how to get around all the nodes
     */
    private enum TraversalOrder {
        PREORDER, INORDER, POSTORDER
    }
}
