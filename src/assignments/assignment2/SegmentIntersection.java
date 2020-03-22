package assignments.assignment2;

/**
 * @author Roman Soldatov BS19-02.
 * 2.1 Segment intersection.
 * <p>
 * Importance of line segment intersection.
 * First of all, line intersection is the geometrical common task to solve more complicated problems.
 * For example, if we know three points lying on a circle, we can calculate the centre of this circle using lines intersection.
 * Another example is the finding the convex hull of given points.
 * We can use the "Graham scan algorithm", "Gift wrapping algorithm" or the "Chan's algorithm".
 * These algorithms require to know about lines reflection and rotation to find the minimal convex hull.
 * Lines reflection and rotation are algorithms based on line intersection. That's why it is really important basis algorithm.
 * What's more, finding the convex hull is the geometry task with practical usage.
 * For instance, we can build the antenna network setting receivers in some territory with the minimal number of this equipment.
 * </p>
 * <p>
 * Sweep Line algorithm summary.
 * As was mentioned in Assignment description the Sweep Line algorithm checks if two any line segments intersect each other.
 * This algorithm does it by O(n*log(n)). Firstly, we can sort a list of points by x coordinate.
 * It will be done by O(n*log(n)). Then, we consider all points from left to right (like passing a vertical line).
 * We add a line segment to the tree sorting by y's coordinate.
 * If two segments intersect, then it means that neighbour segments (according to y's sorting) in an array intersect.
 * In a tree representation, it is the predecessor and successor of the current point.
 * So, if we sort points by x coordinate, but want to find adjacent line segments by y coordinate, then we can use a balanced tree,
 * because each operation for finding the predecessor or the successor costs O(log(n)).
 * Thus, checking n segments requires O(log(n)) for each segment.
 * Therefore, checking all line segments requires n*log(n). Also, it is necessary to sort points firstly.
 * So, the total time is n*log(n) + n*log(n) = O(n*log(n))
 * </p>
 * This class contains a method to check if two line segments have an intersection.
 * There also some private helper methods.
 * The algorithm was adopted from pseudocode in "Introduction to Algorithms", T.H. Cormen p. 1018.
 * The line intersection method was tested here
 * https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=17&id_topic=25&id_problem=135
 */
public class SegmentIntersection {

    /**
     * Check if two line segments have an intersection.
     * O(1)
     *
     * @param line1 - the first line segment.
     * @param line2 - the second line segment.
     * @return true if these line segments intersect, false - otherwise.
     */
    static boolean areIntersect(LineSegment line1, LineSegment line2) {
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
    static int getDirection(Point origin, Point point1, Point point2) {
        Point vector1 = new Point(point1.x - origin.x, point1.y - origin.y);
        Point vector2 = new Point(point2.x - origin.x, point2.y - origin.y);

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
    static boolean isBelongToSegment(Point point, LineSegment segment) {
        int startX = Math.min(segment.start.x, segment.end.x);
        int startY = Math.min(segment.start.y, segment.end.y);
        int endX = Math.max(segment.start.x, segment.end.x);
        int endY = Math.max(segment.start.y, segment.end.y);

        return startX <= point.x && point.x <= endX && startY <= point.y && point.y <= endY;
    }
}

/**
 * Class to store two points.
 * On of them - the start point of a segment, another - the end.
 */
class LineSegment {
    Point start;
    Point end;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return start.toString() + " " + end.toString();
    }
}

/**
 * Class to store x and y coordinates of some point.
 */
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
