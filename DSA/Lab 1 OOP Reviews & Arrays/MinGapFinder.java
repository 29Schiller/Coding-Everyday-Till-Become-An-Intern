public class MinGapFinder {

    public static int minGap(int[] array, int n) {
        if (array == null || n < 2) {
            return 0; // Handle cases with fewer than 2 elements or null array
        }

        int minGap = Integer.MAX_VALUE; // Initialize with the largest possible integer value

        for (int i = 0; i < n - 1; i++) {
            int gap = array[i + 1] - array[i];
            minGap = Math.min(minGap, gap); // Update minGap if a smaller gap is found
        }

        return minGap;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 6, 7, 12};
        int n1 = array1.length;
        int minGap1 = minGap(array1, n1);
        System.out.println("Min gap in array1: " + minGap1); // Output: 1

        int[] array2 = {1, 2, 3, 4, 5};
        int n2 = array2.length;
        int minGap2 = minGap(array2, n2);
        System.out.println("Min gap in array2: " + minGap2); // Output: 1

        int[] array3 = {5, 4, 3, 2, 1};
        int n3 = array3.length;
        int minGap3 = minGap(array3, n3);
        System.out.println("Min gap in array3: " + minGap3); // Output: -1

        int[] array4 = {1};
        int n4 = array4.length;
        int minGap4 = minGap(array4, n4);
        System.out.println("Min gap in array4: " + minGap4); // Output: 0

        int[] array5 = {}; // Empty array
        int n5 = array5.length;
        int minGap5 = minGap(array5, n5);
        System.out.println("Min gap in array5: " + minGap5); // Output: 0

        int[] array6 = null; // Null array
        int n6 = 0;
        int minGap6 = minGap(array6, n6);
        System.out.println("Min gap in array6: " + minGap6); // Output: 0

        int[] array7 = {1, 1, 1, 1};
        int n7 = array7.length;
        int minGap7 = minGap(array7, n7);
        System.out.println("Min gap in array7: " + minGap7); // Output: 0

    }
}