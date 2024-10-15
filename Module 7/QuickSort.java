public class QuickSort {

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        while (true) {
            while (i <= hi && a[lo].compareTo(a[i]) > 0) i++;
            while (j > lo && a[lo].compareTo(a[j]) < 0) j--;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, j, lo);
        return j;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

}
