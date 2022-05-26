class Queens2 {
    static int count = 0;

    /***************************************************************************
     * Prints n-by-n placement of queens in ASCII.
     ***************************************************************************/
    public static void printQueens(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /***************************************************************************
     * Solve the n queens problem by brute force.
     ***************************************************************************/
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // try all n! permutations, but prune useless ones
    public static void enumerate(int[] a, boolean[] diag1, boolean[] diag2, int k) {
        int n = a.length;

        // found one, so print out and stop
        if (k == 0) {
            // printQueens(a);
            // System.exit(0);
        }

        for (int i = 0; i < k; i++) {
            swap(a, i, k - 1);
            int j = k - 1;

            // if placement of new queen is ok, then enumerate
            if (!diag1[j + a[j]] && !diag2[n + j - a[j]]) {
                diag1[j + a[j]] = true;
                diag2[n + j - a[j]] = true;
                enumerate(a, diag1, diag2, k - 1);
                diag1[j + a[j]] = false;
                diag2[n + j - a[j]] = false;
            }
            swap(a, i, k - 1);
        }
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        int n = 16;
        int[] a = new int[n]; // a[i] = row of queen in ith column
        boolean[] diag1 = new boolean[2 * n]; // is ith top diagonal occupied?
        boolean[] diag2 = new boolean[2 * n]; // is ith bottom diagonal occupied?
        for (int i = 0; i < n; i++)
            a[i] = i;
        enumerate(a, diag1, diag2, n);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time: " + (endTime - startTime));
    }
}