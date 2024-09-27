public class BruteCollinearPoints {
    private Point[] pointsReplacement;
    private int numOfSegments;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Argument is null.");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException("Argument is null.");

        }
        pointsReplacement = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            pointsReplacement[i] = points[i];
        sort(pointsReplacement);
        for (int j = 0; j < points.length - 1; j++)
            if (pointsReplacement[j].compareTo(pointsReplacement[j + 1]) == 0)
                throw new java.lang.IllegalArgumentException("Argument is null.");
        numOfSegments = 0;
        LineSegment[] tempSegments = new LineSegment[pointsReplacement.length];
        double slope1;
        double slope2;
        double slope3;
        for (int i = 0; i < pointsReplacement.length; i++) {
            for (int j = i + 1; j < pointsReplacement.length; j++)
                for (int k = j + 1; k < pointsReplacement.length; k++)
                    for (int m = k + 1; m < pointsReplacement.length; m++) {
                        slope1 = pointsReplacement[i].slopeTo(pointsReplacement[j]);
                        slope2 = pointsReplacement[i].slopeTo(pointsReplacement[k]);
                        slope3 = pointsReplacement[i].slopeTo(pointsReplacement[m]);
                        if (slope1 == slope2 && slope2 == slope3) {
                            tempSegments[numOfSegments++] = new LineSegment(pointsReplacement[i], pointsReplacement[m]);
                        }
                    }
        }
        lineSegments = new LineSegment[numOfSegments];
        for (int i = 0; i < lineSegments.length; i++)
            lineSegments[i] = tempSegments[i];
    }

    public int numberOfSegments() {
        return numOfSegments;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    private void sort(Comparable[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
                else break;
            }
        }
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void exch(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}
