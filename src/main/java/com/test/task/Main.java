package com.test.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.task.beans.Sport;
import com.test.task.parsers.Parser;
import com.test.task.parsers.williamhill.WilliamhillParser;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String RESULTS_PATH = "target/result.json";

    public static void main(String[] args) {
        Parser parser = new WilliamhillParser();
        Sport sport = parser.parse();

        if (sport == null) {
            System.out.println("WARN! Parsed data is NULL.");
        } else {
            writeToJsonFile(sport);
        }
    }

    private static void writeToJsonFile(Sport sport) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(RESULTS_PATH), sport);
            System.out.println("Result are in the " + RESULTS_PATH);
        } catch (IOException e) {
            System.out.println("Can't save result.json!");
            e.printStackTrace();
        }
    }
}
