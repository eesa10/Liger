package com.sparta.ac.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Phase2 {
    public static void main(String[] args) {
        StartPhase2();
    }
        public static void StartPhase2 () {
            ArrayList<EmployeeDept> employeesDept = new ArrayList<>();

            EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.createConnetion());

            //Ask user fileName to store xml and json of emp_dept_join
            Scanner scan = new Scanner(System.in);
            System.out.println("Project Phase 2");
            System.out.println("*** This program will read data from DB and create XML & JSON Files ***");
            System.out.println("Please enter Deptno: (d009) ");
            String deptNo = scan.nextLine();
            System.out.println("Please enter From Date: (1990-01-22)");
            String fromDate = scan.nextLine();
            System.out.println("Please enter To Date: (1996-11-09)");
            String toDate = scan.nextLine();
            System.out.println("Please enter filename: (ed)");
            String fileName = scan.nextLine();

            employeesDept = employeeDAO.getListofEmployeesDept(deptNo, fromDate, toDate);

            ConnectionManager.closeConnection();

            jacksonJSONXML(fileName, employeesDept);
            System.out.println("Two files generated with records based on entered criteria");
            System.out.printf("Please see files %s.xml & %s.json ", fileName, fileName);
            //convert to FactoryMethod
        }




    static void jacksonJSONXML(String fileName, ArrayList<EmployeeDept> employeesDept) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonData = mapper.writeValueAsString(employeesDept);
                writeFile("src/main/resources/"+fileName+".json", jsonData);

                XmlMapper xmlMapper = new XmlMapper();
                String xmlData = xmlMapper.writeValueAsString(employeesDept);

                writeFile("src/main/resources/"+fileName+".xml", xmlData);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static void writeFile(String fileName, String dataToWrite) throws IOException {
            FileWriter file = new FileWriter(fileName);
            file.write(dataToWrite);
        }

    }





