package com.sparta.ac.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Phase1 {
    public static void main(String[] args) {
        StartPhase1();
    }

    public static void StartPhase1() {
        ArrayList<Employee> employees = new ArrayList<>();

        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.createConnetion());

        //Show user text fileName to store employees records
        Scanner scan = new Scanner(System.in);
        System.out.println("Project Phase 1");
        System.out.println("*** This program will read data from DB (Employees Table) and create TEXT File ***");
        System.out.println("File Writing...");
        System.out.println(("Please see employees table records in file: employees.txt"));

        employees = employeeDAO.getListofEmployees();
        writeEmployeeRecords(employees);
    }
    private static void writeEmployeeRecords(ArrayList<Employee> loe) {
        try {
            FileWriter file = new FileWriter("src/main/resources/employees.txt");

            for (Employee e : loe) {
                file.write(e.toString()+"\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
