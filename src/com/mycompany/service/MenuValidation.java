package com.mycompany.service;

/**
 * A class to support the operation of the menu, to validate commands entered by the user
 */
public class MenuValidation {

    /** String containing all the operations supported by the application that is output to the user */
    public static final String MENU = "\n\nEnter: \"create <name>\" to create a new survey; " +
            "\n\"list\" to view surveys that have already been created; " +
            "\n\"view <name>\" to see the survey; " +
            "\n\"delete <name>\" to delete the survey; " +
            "\n\"exit\" to end application execution.";

    /**
     * Method for validating commands entered by the user. The command can be entered in any register
     *
     * @param expression the expression entered by the user
     * @return true if the expression is a supported command, false if not supported
     */
    public static boolean expressionCanBeValid(String expression) {
        if (expression.isEmpty()) return false;
        String[] words = expression.trim().split("\\s+");
        int length = words.length;
        if (length > 2) return false;
        if (length==1) switch (words[0].toLowerCase()) {
            case "list":
            case "exit":
                return true;
            default: return false;
        } else switch (words[0].toLowerCase()) {
            case "create":
            case "view":
            case "delete":
                return true;
            default: return false;
        }
    }

    /**
     * A method for extracting a command from an expression entered by the user
     *
     * @param expression the expression entered by the user
     * @return name of action in lower case
     */
    public static String getAction(String expression) {
        if (!expressionCanBeValid(expression)) return null;
        String[] words = expression.trim().split("\\s+");
        return words[0].toLowerCase();
    }

    /**
     * A method for extracting a name of survey from an expression entered by the user
     *
     * @param expression the expression entered by the user
     * @return name of the survey or null if the command consists of a single word
     */
    public static String getName(String expression) {
        if (!expressionCanBeValid(expression)) return null;
        String[] words = expression.trim().split("\\s+");
        return words.length == 1 ? null : words[1];
    }
}
