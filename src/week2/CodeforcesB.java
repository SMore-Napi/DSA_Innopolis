package week2;

import java.util.Scanner;
import java.util.Stack;

/**
 * Roman Soldatov B19-02
 */

public class CodeforcesB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        System.out.println(calculateEquation(input));
    }

    // Calculating equation
    public static int calculateEquation(String[] input) {
        Stack<Integer> numbers = new Stack<>();

        // Get infix notation
        String[] infixNotation = getInfixNotation(input).split(" ");

        for (String token : infixNotation) {
            // In case it is a number
            if (token.length() != 1 || isDigit(token.charAt(0))) {
                numbers.push(Integer.parseInt(token));
            } // In case it is an operator
            else {
                // Two last numbers
                int b = numbers.pop();
                int a = numbers.pop();

                // Calculating result
                switch (token) {
                    case "+":
                        numbers.push(a + b);
                        break;
                    case "-":
                        numbers.push(a - b);
                        break;
                    case "*":
                        numbers.push(a * b);
                        break;
                    case "/":
                        numbers.push(a / b);
                        break;
                }

            }
        }

        return numbers.pop();
    }

    // Using the algorithm from Problem A. Shunting-yard algorithm
    public static String getInfixNotation(String[] input) {

        // The result writes into this variable
        StringBuilder output = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        // Reading each token
        for (String stringToken : input) {

            // If the length of a token is more than 1 then it is a number
            if (stringToken.length() != 1) {
                output.append(stringToken).append(' ');
            } // If it is a symbol
            else {
                char token = stringToken.charAt(0);

                // In case it is a digit
                if (isDigit(token)) {
                    output.append(token).append(' ');
                } // In case it is an operator
                else if (token == '+' || token == '-' || token == '/' || token == '*') {
                    // Pop from stack all operators wich are greater or equal precedence than the current operator from input
                    while ((!operators.isEmpty()) && (operators.peek() != '(') && (isGreaterPrecedence(operators.peek(), token))) {
                        output.append(operators.pop()).append(' ');
                    }
                    operators.push(token);
                } // In case it is a parentheses
                else if (token == '(') {
                    operators.push(token);
                } else if (token == ')') {
                    while (operators.peek() != '(') {
                        output.append(operators.pop()).append(' ');
                    }
                    operators.pop();
                }

            }
        }

        // In case some operators left in the stack
        while (!operators.isEmpty()) {
            output.append(operators.pop()).append(' ');
        }

        // Deleting last space ' '
        output.deleteCharAt(output.length() - 1);

        return output.toString();
    }

    public static boolean isDigit(char symbol) {
        final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char digit : digits) {
            if (symbol == digit) {
                return true;
            }
        }
        return false;
    }

    // Checks if an operator a is greater or equal precedence than an operator b
    public static boolean isGreaterPrecedence(char a, char b) {
        if (a == '*' || a == '/') {
            return true;
        } else if (b == '-' || b == '+') {
            return true;
        }
        return false;
    }
}
