//package com.sparta.em.phase3;
package com.sparta.ac.jdbc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readEmployeesJSON {
    public static void main(String[] args){
        String[] employeesList = getEmployees(3);
        for(String s:employeesList) {
            System.out.println();
        }
    }
    public static String[] getEmployees(int numEmployees) {

        List<String> result = new ArrayList<>();

        try (JsonReader reader = new JsonReader(new FileReader("src/main/resources/employees02.json"))) {
            Gson gson = new Gson();
            reader.beginArray();

            while (reader.hasNext() && result.size() < numEmployees) {
                String employeeLine = gson.fromJson(reader, String.class);
                result.add(employeeLine);
            }

            reader.endArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toArray(new String[0]);
    }

}
