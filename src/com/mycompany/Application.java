package com.mycompany;

import com.mycompany.model.Survey;
import com.mycompany.service.Writer;

import static com.mycompany.service.MenuValidation.*;
import static com.mycompany.model.Survey.findSurveyInListByName;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * An application for creating surveys. Support commands:
 * - creating a new survey
 * - view a survey with the name
 * - view all created surveys
 * - deleting a survey by name
 * - exit the application
 * If there is a "savefile" CLI argument as the first argument, the created surveys are saved to a file.
 * The application provides validation of commands entered by the user.
 *
 * The application supports entering commands until the exit command is entered.
 * Basic commands can be entered in any register.
 */
public class Application {
    public static void main(String[] args) {
        boolean saveFile = false;
        if (args.length != 0 && "savefile".equalsIgnoreCase(args[0]))
            saveFile = true;

        //variable for saving created surveys
        LinkedList<Survey> surveys = new LinkedList<Survey>();

        //the command entered by the user
        String choice = "";
        do {
            System.out.println(MENU);
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            if (!expressionCanBeValid(choice)) {
                System.out.println("You have entered an incorrect command. Try again.");
                continue;
            }
            //action of command
            String action = getAction(choice);
            //name of survey
            String name = getName(choice);

            switch (action) {
                case "create": {
                    Survey foundSurvey = findSurveyInListByName(surveys, name);
                    if (foundSurvey != null) {
                        System.out.println("A survey with this name already exists. Try entering a different name.");
                        continue;
                    }
                    System.out.println("Enter the topic of the survey with the name \"" + name + "\":");
                    //entered topic of the survey
                    String topic = scanner.nextLine();

                    //entered number of response options
                    int responsesNumber = 0;
                    boolean isPositive = true;
                    boolean isInteger = true;
                    do {
                        System.out.println("Enter the number of survey response options:");
                        try {
                            responsesNumber = Integer.parseInt(scanner.nextLine());
                            isInteger = true;
                            isPositive = responsesNumber <= 0 ? false : true;
                            if (!isPositive) {
                                System.out.println("You entered a negative number or zero. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            isInteger = false;
                            System.out.println("You didn't enter an integer. " +
                                    "Try to create the survey again by entering the correct values.");
                        }
                    } while (!isInteger || !isPositive);

                    //entered responses of the survey
                    ArrayList<String> responses = new ArrayList<String>();
                    System.out.println("Enter the survey response options:");
                    for (int i = 0; i < responsesNumber; i++) {
                        String response = scanner.nextLine();
                        responses.add(response);
                    }
                    Survey survey = new Survey(name, topic, responsesNumber, responses);
                    surveys.add(survey);
                    System.out.println("The survey was successfully added");
                    break;
                }
                case "list": {
                    if (surveys.size() == 0)
                        System.out.println("You don't have any surveys created");
                    else for (Survey survey : surveys)
                        System.out.println(survey.getName() + " (" + survey.getTopic() + ")");
                    break;
                }
                case "view": {
                    Survey survey = findSurveyInListByName(surveys, name);
                    if (survey == null) System.out.println("A survey with this name was not found");
                    else System.out.println(survey);
                    break;
                }
                case "delete": {
                    Survey survey = findSurveyInListByName(surveys, name);
                    if (survey == null) System.out.println("A survey with this name was not found");
                    else {
                        surveys.remove(survey);
                        System.out.println("The survey was successfully deleted");
                    }
                    break;
                }
                case "exit": {
                    if (saveFile && !surveys.isEmpty()) {
                        boolean error = false;
                        do {
                            System.out.println("Enter the file path, name and extension if necessary: ");
                            String path = scanner.nextLine();
                            if (Writer.writeFile(path, surveys)) {
                                error = false;
                                System.out.println("The file was saved successfully.");
                            } else {
                                error = true;
                                System.out.println("There were problems with writing to the file. Try again.");
                            }
                        } while (error);
                    }
                    System.exit(0);
                    break;
                }
            }
        } while (!"exit".equalsIgnoreCase(choice.trim()));
    }
}
