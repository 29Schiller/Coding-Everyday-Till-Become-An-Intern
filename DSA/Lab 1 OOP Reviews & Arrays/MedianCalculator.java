import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianCalculator {

    public static double calculateMedian(List<? extends Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty.");
        }

        List<Double> doubleNumbers = new ArrayList<>(); // Use Double to handle both int and float
        for (Number number : numbers) {
            doubleNumbers.add(number.doubleValue());
        }

        Collections.sort(doubleNumbers); // Sort the list

        int size = doubleNumbers.size();
        if (size % 2 == 0) { // Even number of elements
            double middle1 = doubleNumbers.get(size / 2 - 1);
            double middle2 = doubleNumbers.get(size / 2);
            return (middle1 + middle2) / 2.0;
        } else { // Odd number of elements
            return doubleNumbers.get(size / 2);
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> intList1 = new ArrayList<>();
        intList1.add(1);
        intList1.add(3);
        intList1.add(5);
        double median1 = calculateMedian(intList1);
        System.out.println("Median of " + intList1 + ": " + median1); // Output: 3.0

        List<Double> doubleList1 = new ArrayList<>();
        doubleList1.add(1.5);
        doubleList1.add(2.7);
        doubleList1.add(4.2);
        double median2 = calculateMedian(doubleList1);
        System.out.println("Median of " + doubleList1 + ": " + median2); // Output: 2.7

        List<Integer> intList2 = new ArrayList<>();
        intList2.add(1);
        intList2.add(2);
        intList2.add(3);
        intList2.add(4);
        double median3 = calculateMedian(intList2);
        System.out.println("Median of " + intList2 + ": " + median3); // Output: 2.5

        List<Double> doubleList2 = new ArrayList<>();
        doubleList2.add(1.0);
        doubleList2.add(2.0);
        doubleList2.add(3.0);
        doubleList2.add(4.0);
        double median4 = calculateMedian(doubleList2);
        System.out.println("Median of " + doubleList2 + ": " + median4); // Output: 2.5

        List<Integer> emptyList = new ArrayList<>();
        try {
            calculateMedian(emptyList);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Output: Error: Input list cannot be null or empty.
        }

        List<Number> mixedList = new ArrayList<>();
        mixedList.add(1);
        mixedList.add(2.5);
        mixedList.add(3);
        double median5 = calculateMedian(mixedList);
        System.out.println("Median of " + mixedList + ": " + median5); // Output: 2.5

    }
}