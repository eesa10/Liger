package com.sparta.ac.jdbc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFactory {
    public static String[] getAllEmployees() throws IOException {
        String employeeLine;
        List<String> result = new ArrayList<>();
        BufferedReader f = new BufferedReader(new FileReader("src/main/resources/employees01.csv"));

        while ((employeeLine = f.readLine()) != null)
            result.add(employeeLine);
        //return result.subList(0,numEmployees).toArray(new String[0]);
        return result.toArray(new String[0]);
    }

    public static ArrayList<String> getAllEmployeesCSVList() throws IOException {
        String employeeLine;
        ArrayList<String> result = new ArrayList<>();
        BufferedReader f = new BufferedReader(new FileReader("src/main/resources/employees01.csv"));

        while ((employeeLine = f.readLine()) != null)
            result.add(employeeLine);
        //return result.subList(0,numEmployees).toArray(new String[0]);
        return result;
    }

    public static ArrayList<String> getAllEmployeesJSONList() throws IOException {
        String employeeLine;
        ArrayList<String> result = new ArrayList<>();

        try (JsonReader reader = new JsonReader(new FileReader("src/main/resources/employees02.json"))) {
            Gson gson = new Gson();
            reader.beginArray();

            while (reader.hasNext()) {
                employeeLine = gson.fromJson(reader, String.class);
                result.add(employeeLine);
            }

            reader.endArray();
        }
        return result;
    }

    public static ArrayList<String> getAllEmployeesXMLList() throws IOException, ParserConfigurationException, SAXException {
        String employeeLine = "";
        ArrayList<String> result = new ArrayList<>();

        // Read XML file
        File xmlFile = new File("src/main/resources/employees03.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Get employee elements
        NodeList nodeList = doc.getElementsByTagName("employee");

        // Loop through employees and add them to the result list
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element employeeElement = (Element) nodeList.item(i);
            //employeeLine = employeeElement.toString();
            //
            employeeLine = employeeElement.getElementsByTagName("emp_no").item(0).getTextContent() + "," + employeeElement.getElementsByTagName("birth_date").item(0).getTextContent() + "," + employeeElement.getElementsByTagName("first_name").item(0).getTextContent() + "," + employeeElement.getElementsByTagName("last_name").item(0).getTextContent() + "," + employeeElement.getElementsByTagName("gender").item(0).getTextContent() + "," + employeeElement.getElementsByTagName("hire_date").item(0).getTextContent();
            //
            result.add(employeeLine);
        }

        // Return the result as an array
        return result;
    }
}