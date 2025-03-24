import java.util.Arrays;
import java.util.Scanner;

public class ArrayToNumberConverter {

    public static int convertArrayToNumber(int[] array) {
        return convertArrayToNumber(array, false); // Default to positive
    }

    public static int convertArrayToNumber(int[] array, boolean isNegative) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }

        int number = 0;
        for (int i = 0; i < array.length; i++) {
            int digit = array[i];

            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("Invalid digit: " + digit + " at index " + i + ". Digits must be between 0 and 9.");
            }
            number = number * 10 + digit;

        }
        return isNegative ? -number : number;

    }



    public static int convertHexArrayToNumber(char[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }

        int number = 0;
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            int digit;

            if (Character.isDigit(c)) {
                digit = Character.getNumericValue(c);
            } else if (c >= 'A' && c <= 'F') {
                digit = c - 'A' + 10;
            } else if (c >= 'a' && c <= 'f') {
                digit = c - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Invalid hexadecimal character: " + c + " at index " + i);
            }

            number = number * 16 + digit;
        }
        return number;
    }


    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("The size of array digits[]: ");
            int size = sc.nextInt();
            int[] decimalArray = new int[size];
            char[] hexArray = new char[size]; // For hex input

            System.out.println("Enter decimal array elements (0-9): ");
            for (int i = 0; i < size; i++) {
                decimalArray[i] = sc.nextInt();
            }

            System.out.println("Enter hexadecimal array elements (0-9, A-F): ");
            for (int i = 0; i < size; i++) {
                String input = sc.next(); // Read as String to handle A-F
                if (input.length() != 1) {
                    throw new IllegalArgumentException("Invalid hex input. Enter single character (0-9, A-F)");
                }
                hexArray[i] = input.toUpperCase().charAt(0); // Store as char, convert to uppercase
            }



            try {
                int decimalNumber = convertArrayToNumber(decimalArray);
                System.out.println("Decimal Conversion: " + Arrays.toString(decimalArray) + " -> " + decimalNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("Decimal Conversion Error: " + e.getMessage());
            }

            try {
                int hexNumber = convertHexArrayToNumber(hexArray);
                System.out.println("Hexadecimal Conversion: " + Arrays.toString(hexArray) + " -> " + Integer.toHexString(hexNumber).toUpperCase() + " (Decimal: " + hexNumber + ")"); // Print hex value
            } catch (IllegalArgumentException e) {
                System.out.println("Hexadecimal Conversion Error: " + e.getMessage());
            }

        }

        // Test cases for decimal conversion
        testDecimalConversion(new int[]{2, 0, 2, 5}, 2025);
        testDecimalConversion(new int[]{1, 2, 3, 4, 5}, 12345);
        testDecimalConversion(new int[]{0, 0, 0, 7}, 7);
        testDecimalConversion(new int[]{}, 0); //edge case
        testDecimalConversion(new int[]{-1, 2, 3}, -123); //negative number

        // Test cases for hexadecimal conversion
        testHexConversion(new char[]{'2', 'A', 'F'}, 687); //2*16^2 + 10*16^1 + 15*16^0 = 512 + 160 + 15 = 687
        testHexConversion(new char[]{'1', '0'}, 16);
        testHexConversion(new char[]{'A'}, 10);
        testHexConversion(new char[]{'f'}, 15);
        testHexConversion(new char[]{}, 0); //edge case

        // Test cases for invalid input (decimal)
        testInvalidDecimalInput(new int[]{2, 0, 2, 5, 10}); // Invalid digit
        testInvalidDecimalInput(null); // Null array

        // Test cases for invalid input (hexadecimal)
        testInvalidHexInput(new char[]{'2', 'G', 'F'}); // Invalid hex character
        testInvalidHexInput(null); // Null array

    }


    private static void testDecimalConversion(int[] array, int expected) {
        try {
            int actual = convertArrayToNumber(array);
            System.out.println("Decimal Conversion: " + Arrays.toString(array) + " -> " + actual + " (Expected: " + expected + ") " + (actual == expected ? "PASSED" : "FAILED"));

        } catch (IllegalArgumentException e) {
            System.out.println("Decimal Conversion: " + Arrays.toString(array) + " -> Exception: " + e.getMessage());
        }
    }

    private static void testHexConversion(char[] array, int expected) {
        try {
            int actual = convertHexArrayToNumber(array);
            System.out.println("Hex Conversion: " + Arrays.toString(array) + " -> " + actual + " (Expected: " + expected + ") " + (actual == expected ? "PASSED" : "FAILED"));
        } catch (IllegalArgumentException e) {
            System.out.println("Hex Conversion: " + Arrays.toString(array) + " -> Exception: " + e.getMessage());
        }
    }


    private static void testInvalidDecimalInput(int[] array) {
        try {
            convertArrayToNumber(array);
            System.out.println("Invalid Decimal Input Test FAILED. Expected exception for: " + Arrays.toString(array));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Decimal Input Test PASSED. Exception: " + e.getMessage());
        }
    }

    private static void testInvalidHexInput(char[] array) {
        try {
            convertHexArrayToNumber(array);
            System.out.println("Invalid Hex Input Test FAILED. Expected exception for: " + Arrays.toString(array));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Hex Input Test PASSED. Exception: " + e.getMessage());
        }
    }
}