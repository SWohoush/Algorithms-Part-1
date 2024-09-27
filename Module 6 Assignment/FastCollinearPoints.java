import java.util.Arrays;
import java.util.Objects;

public class FastCollinearPoints {
    private Point[] points;
    private Double[] slopes;
    private int numOfSegments;
    private String[] segmentEnds;
    private Point[] pointSlopes;

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null) throw new java.lang.IllegalArgumentException("Argument is null.");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException("Argument is null.");
        }
        pointSlopes = new Point[points.length];
        this.points = new Point[points.length];
        slopes = new Double[points.length];
        segmentEnds = new String[points.length * 4];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
            pointSlopes[i] = points[i];
        }
        for (int i = 0; i < points.length * 4; i++) {
            segmentEnds[i] = "\uFFFF";
        }
        Arrays.sort(this.points);
        Arrays.sort(pointSlopes);
        for (int j = 0; j < points.length - 1; j++)
            if (this.points[j].compareTo(this.points[j + 1]) == 0) throw new java.lang.IllegalArgumentException("Argument is null.");
        numOfSegments = 0;
    }

    public int numberOfSegments()        // the number of line segments
    {
        return numOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] tempSegments = new LineSegment[points.length];
        int segmentLength;
        for (int i = 0; i < points.length; i++) {
            exch(points, i, 0);
            for (int k = 0; k < points.length; k++) {
                pointSlopes[k] = points[k];
            }
            Arrays.sort(pointSlopes, points[0].slopeOrder());
            calcSlope(points[0]);
            Arrays.sort(segmentEnds);
            segmentLength = 2;
            for (int j = 1; j < points.length; j++) {
                if (Objects.equals(slopes[j], slopes[j - 1])) {
                    segmentLength++;
                    if (j == points.length - 1) {
                        checkSegmentLength(segmentLength, j + 1, tempSegments);
                        segmentLength = 2;
                    }
                } else {
                    if (segmentLength >= 4) {
                        checkSegmentLength(segmentLength, j, tempSegments);
                        segmentLength = 2;
                    }
                }
            }
        }
        LineSegment[] lineSegments = new LineSegment[numOfSegments];
        for (int i = 0; i < lineSegments.length; i++)
            lineSegments[i] = tempSegments[i];
        return lineSegments;
    }

    private void checkSegmentLength(int segmentLength, int j, LineSegment[] tempSegments) {
        if (segmentLength >= 4) {
            Point startP = pointSlopes[0];
            Point endP = pointSlopes[j - 1];
            if (numOfSegments == 0) addSegment(tempSegments, startP, endP, j);
            else if (binarySearch(segmentEnds, startP.toString() + slopes[j - 1]) == -1
                    && binarySearch(segmentEnds, endP.toString() + slopes[j - 1]) == -1
                    && binarySearch(segmentEnds, Double.toString(slopes[j - 1])) == -1) {
                addSegment(tempSegments, startP, endP, j);

            }
        }
    }

    private void addSegment(LineSegment[] tempSegments, Point startP, Point endP, int j) {
        tempSegments[numOfSegments] = new LineSegment(startP, endP);
        numOfSegments++;
        segmentEnds[numOfSegments * 3 - 1] = startP.toString() + slopes[j - 1];
        segmentEnds[numOfSegments * 3 - 2] = endP.toString() + slopes[j - 1];
        if (slopes[j - 1] != 0.0 && slopes[j - 1] != Double.POSITIVE_INFINITY)
            segmentEnds[numOfSegments * 3 - 3] = slopes[j - 1] + "";
    }

    private void exch(Object[] arr, int a, int b) {
        Object swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
    }

    private void calcSlope(Point point) {
        for (int i = 0; i < points.length; i++) {
            slopes[i] = point.slopeTo(pointSlopes[i]);
        }
    }

    private int binarySearch(String[] arr, String key) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int res = key.compareTo(arr[mid]);
            if (res == 0) {
                return mid;
            }
            if (res > 0) {
                lo = mid + 1;
            } else
                hi = mid - 1;

        }
        return -1;
    }
}
