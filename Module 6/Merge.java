public class Merge {

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int r = lo;
        int j = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (r > mid) a[i] = aux[j++];
            else if (j > hi) a[i] = aux[r++];
            else if (less(aux[j], aux[r])) a[i] = aux[j++];
            else a[i] = aux[r++];
        }
        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            if (!less(a[i], a[i + 1]))
                return false;
        return true;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

}
