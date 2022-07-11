package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class of the Survey entity. Contains getters and setters for all fields of the class,
 * some additional functions, override standard Object methods: toString, hashCode, equals.
 */
public class Survey {
    /** The name of the survey */
    private String name;

    /** The topic of the survey or the question that the interviewees are asked to answer */
    private String topic;

    /** The number of response options in the survey */
    private int size;

    /** Suggested survey responses */
    private ArrayList<String> responses;

    /** Creating a new object with fully initialized fields */
    public Survey(String name, String topic, int size, ArrayList<String> responses) {
        this.name = name;
        this.topic = topic;
        this.size = size;
        this.responses = responses;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getTopic() {
        return topic;
    }

    public Survey(ArrayList<String> responses) {
        this.responses = responses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setResponses(ArrayList<String> responses) {
        this.responses = responses;
    }

    /**
     * The method is used to find a Survey by name in a collection of Surveys
     *
     * @param list the List<Survey> collection in which the search is performed
     * @param name the name of the Survey we are looking for
     * @return the first occurrence of a Survey with this name, or null if the Survey is not found
     */
    public static Survey findSurveyInListByName(List<Survey> list, String name) {
        return list.stream().filter((survey -> survey.getName().equals(name)))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        String survey = "Survey \"" + name + "\": \n Topic: " + topic + "\n Responses:";
        for (String response: responses)
            survey += "\n - " + response;
        return survey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return size == survey.size && Objects.equals(name, survey.name)
                && Objects.equals(topic, survey.topic)
                && Objects.equals(responses, survey.responses);
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + name.hashCode();
        result = 31*result + topic.hashCode();
        result = 31*result + size;
        for (String response: responses)
            result = 31*result + response.hashCode();
        return result;
    }
}
