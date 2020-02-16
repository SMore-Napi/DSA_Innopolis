package assignments.assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Roman Soldatov BS19-02
 * 2.3 Phonebook
 * Submission number: 71183820
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/269072/submission/71183820
 */

public class Phonebook {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> contacts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            executeCommand(command, contacts);
        }
    }

    // Separate string by command, contactName and phone. Then call handleCommand() method.
    static void executeCommand(String string, HashMap<String, ArrayList<String>> contacts) {
        String command;
        String contactName;
        String phone;
        boolean indexUpdated = false;

        StringBuilder token = new StringBuilder();
        int index = 0;

        // Command
        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (symbol == ' ') {
                indexUpdated = true;
                index = i + 1;
                break;
            }
            token.append(symbol);
        }
        if (!indexUpdated) {
            index = string.length();
        }
        indexUpdated = false;
        command = token.toString();
        token = new StringBuilder();

        // Contact Name
        for (int i = index; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (symbol == ',') {
                indexUpdated = true;
                index = i + 1;
                break;
            }
            token.append(symbol);
        }
        if (!indexUpdated) {
            index = string.length();
        }
        contactName = token.toString();
        token = new StringBuilder();

        // Phone
        for (int i = index; i < string.length(); i++) {
            char symbol = string.charAt(i);
            token.append(symbol);
        }
        phone = token.toString();

        handleCommand(command, contactName, phone, contacts);
    }

    // Call current input command.
    static void handleCommand(String command, String contactName, String phone, HashMap<String, ArrayList<String>> contacts) {
        switch (command.toUpperCase()) {
            case "ADD":
                add(contactName, phone, contacts);
                break;
            case "DELETE":
                delete(contactName, phone, contacts);
                break;
            case "FIND":
                find(contactName, contacts);
                break;
        }
    }

    // Add a phone number
    static void add(String contactName, String phone, HashMap<String, ArrayList<String>> contacts) {
        boolean isNewPhone = true;

        ArrayList<String> phones = contacts.get(contactName);

        // If it is a new contact.
        if (phones == null) {
            phones = new ArrayList<>();
        } // Search if this phone has been already added.
        else {
            for (String phoneNumber : phones) {
                if (phoneNumber.equals(phone)) {
                    isNewPhone = false;
                    break;
                }
            }
        }

        if (isNewPhone) {
            phones.add(phone);
        }

        contacts.put(contactName, phones);
    }

    // Delete a contact or a phone
    static void delete(String contactName, String phone, HashMap<String, ArrayList<String>> contacts) {
        if (phone.length() == 0) {
            deleteContact(contactName, contacts);
        } else {
            deletePhone(contactName, phone, contacts);
        }
    }

    // Delete a contact from the dictionary
    static void deleteContact(String contactName, HashMap<String, ArrayList<String>> contacts) {
        contacts.remove(contactName);
    }

    // Delete a phone number
    static void deletePhone(String contactName, String phone, HashMap<String, ArrayList<String>> contacts) {
        ArrayList<String> phones = contacts.get(contactName);
        if (phones != null) {
            for (int i = 0; i < phones.size(); i++) {
                if (phones.get(i).equals(phone)) {
                    phones.remove(phone);
                    break;
                }
            }

            if (!phones.isEmpty()) {
                contacts.put(contactName, phones);
            } else {
                contacts.remove(contactName);
            }
        }
    }

    // Output contact's name and his/her phone numbers
    static void find(String contactName, HashMap<String, ArrayList<String>> contacts) {
        ArrayList<String> phones = contacts.get(contactName);
        if (phones == null) {
            System.out.println("No contact info found for " + contactName);
        } else {
            System.out.print("Found " + phones.size() + " phone numbers for " + contactName + ": ");
            for (int i = 0; i < phones.size() - 1; i++) {
                System.out.print(phones.get(i) + " ");
            }
            System.out.println(phones.get(phones.size() - 1));
        }
    }
}
