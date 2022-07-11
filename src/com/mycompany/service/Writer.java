package com.mycompany.service;

import com.mycompany.model.Survey;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class with a static method to write a collection of created surveys to a file
 */
public class Writer {

    /**
     * Static method to write a LinkedList<Survey> collection of created surveys
     * to a file with a specified file path
     *
     * @param path the file path, including the full path to the file,
     *             the file name and extension if necessary (it is enough to specify only the name)
     * @param surveys LinkedList<Survey> collection to be saved
     * @return true in case of successful saving, false if there are problems with writing to the file
     */
    public static boolean writeFile(String path, LinkedList<Survey> surveys) {
        try(FileWriter writer = new FileWriter(path, true))
        {
            for (Survey survey: surveys)
                writer.write(survey.toString() + "\n\n");
        }
        catch(IOException ex) {
            return false;
        }
        return true;
    }
}
