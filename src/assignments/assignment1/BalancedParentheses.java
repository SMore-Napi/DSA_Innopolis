package assignments.assignment1;

import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * @author Roman Soldatov B19-02
 * 2.2 Balanced parentheses
 * Submission number: 71180759
 */

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        String exceptionMessage = "";
        boolean isError = false;
        int line = 0;
        int column = 0;
        int stringLength = 0;

        // Input
        int lines = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < lines; i++) {
            String string = scanner.nextLine();
            stringLength = string.length();

            // Analyze the string if a mistake has not been found yet.
            if (!isError) {
                // Check each symbol of a string.
                for (int j = 0; j < stringLength; j++) {
                    // Check symbol if a mistake has not been found yet.
                    if (!isError){
                        char symbol = string.charAt(j);
                        // In case it is an opening bracket.
                        if (symbol == '(' || symbol == '[' || symbol == '{') {
                            stack.push(symbol);
                        } // In case it is a closing bracket.
                        else if (symbol == ')' || symbol == ']' || symbol == '}') {
                            // unexpected closing <symbol> mistake.
                            if (stack.isEmpty()) {
                                isError = true;
                                exceptionMessage = "unexpected closing '" + symbol + "'.";
                                line = i + 1;
                                column = j + 1;
                            } // expected <symbol>, but got <symbol> mistake.
                            else {
                                char lastSymbol = stack.pop();
                                switch (symbol) {
                                    case ')':
                                        if (!(lastSymbol == '(')) {
                                            isError = true;
                                            line = i + 1;
                                            column = j + 1;
                                            switch (lastSymbol){
                                                case '[':
                                                    exceptionMessage = "expected ']', but got '" + symbol + "'.";
                                                    break;
                                                case '{':
                                                    exceptionMessage = "expected '}', but got '" + symbol + "'.";
                                                    break;
                                            }
                                        }
                                        break;
                                    case ']':
                                        if (!(lastSymbol == '[')) {
                                            isError = true;
                                            line = i + 1;
                                            column = j + 1;
                                            switch (lastSymbol){
                                                case '(':
                                                    exceptionMessage = "expected ')', but got '" + symbol + "'.";
                                                    break;
                                                case '{':
                                                    exceptionMessage = "expected '}', but got '" + symbol + "'.";
                                                    break;
                                            }
                                        }
                                        break;
                                    case '}':
                                        if (!(lastSymbol == '{')) {
                                            isError = true;
                                            line = i + 1;
                                            column = j + 1;
                                            switch (lastSymbol){
                                                case '(':
                                                    exceptionMessage = "expected ')', but got '" + symbol + "'.";
                                                    break;
                                                case '[':
                                                    exceptionMessage = "expected ']', but got '" + symbol + "'.";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }

        // expected <symbol>, but got end of input mistake.
        if (!isError) {
            if (!stack.isEmpty()) {
                char symbol = stack.pop();
                switch (symbol) {
                    case '(':
                        isError = true;
                        exceptionMessage = "expected ')', but got end of input.";
                        line = lines;
                        column = stringLength;
                        break;
                    case '[':
                        isError = true;
                        exceptionMessage = "expected ']', but got end of input.";
                        line = lines;
                        column = stringLength;
                        break;
                    case '{':
                        isError = true;
                        exceptionMessage = "expected '}', but got end of input.";
                        line = lines;
                        column = stringLength;
                        break;
                }
            }
        }

        if (!isError) {
            System.out.println("Input is properly balanced.");
        } else {
            System.out.println("Error in line " + line + ", column " + column + ": " + exceptionMessage);
        }
    }
}

/**
 * Stack implementation
 *
 * @param <T> - type of elements
 */
class Stack<T> {

    T[] array;
    int size;

    public Stack() {
        size = 0;
        array = (T[]) new Object[16];
    }

    void doubleCapacity() {
        T[] newStorage = (T[]) new Object[2 * array.length];
        System.arraycopy(array, 0, newStorage, 0, array.length);
        array = newStorage;
    }

    public void push(T value) {
        if (size == array.length) {
            doubleCapacity();
        }
        array[size++] = value;
    }

    public T pop() throws EmptyStackException {
        T value = peek();
        size--;
        return value;
    }

    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}

