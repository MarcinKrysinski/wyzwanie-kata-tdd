package wyzwanie.tddkata;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Calculator {


    private static final int UPPER_LIMIT = 1000;

    Integer add(String input) {

        if (isNullOrIsEmpty(input)) {
            return 0;
        } else if (isPattern(input)) {
            return calculateWithPattern(input);
        }

        return calculateWithOutPattern(input);

    }

    private int calculateWithOutPattern(String input) {

        if (!Calculator.isNumeric(input) && !input.contains(",")) {
            throw new RuntimeException("Delimiter [,] not found");
        } else if (input.length() == 1) {
            return stringToInt(input);
        } else {
            return parseAndSumInput(input, ",");
        }
    }

    private int calculateWithPattern(String input) {

        String delimiter = input.substring(input.indexOf("["), input.indexOf("]"));

        String substring = input.substring(input.indexOf("n") + 1);
        Pattern patternSubstring = Pattern.compile("^(-?.*" + delimiter + "){1,}-?.*$");

        if (patternSubstring.matcher(substring).matches()) {
            return parseAndSumInput(substring, delimiter);
        }
        throw new RuntimeException("Delimiter doesn't match");
    }

    private int parseAndSumInput(String input, String delimiter) {
        List<String> inputList = Arrays.asList(input.split(delimiter));
        int result = 0;

        for (String s : inputList) {
            if (isNumeric(s)) {
                int value = Integer.parseInt(s);
                if (value < 0) {
                    throwNegativeNotAllowedWithMessage(inputList);
                }
                else {
                    result += value <= UPPER_LIMIT ? value : 0;
                }
            }
        }

        return result;
    }

    private void throwNegativeNotAllowedWithMessage(List<String> inputList) {
        inputList = inputList.stream()
                .filter(s -> Integer.parseInt(s) < 0)
                .collect(Collectors.toList());

        String negativesToMessage = String.join(", ", inputList);
        throw new NegativeNotAllowed(negativesToMessage);
    }

    private boolean isPattern(String input) {
        Pattern pattern = Pattern.compile("^//.*\\\\n.*$");
        return pattern.matcher(input).matches();
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private int sum(String numberOne, String numberTwo) {
        return stringToInt(numberOne) + stringToInt(numberTwo);
    }

    private int stringToInt(String input) {
        return Integer.parseInt(input);
    }

    private boolean isEmpty(String input) {
        return input.isEmpty();
    }

    private boolean isNullOrIsEmpty(String input) {
        return Objects.isNull(input) || isEmpty(input);
    }


    //Do not modify code below this line. This is just a runner

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter calculation. Ctrl+d for exit.");

        Calculator calculator = new Calculator();
        System.out.print("> ");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input + " ==> " + calculator.add(input));

            System.out.print("> ");
        }

    }
}
