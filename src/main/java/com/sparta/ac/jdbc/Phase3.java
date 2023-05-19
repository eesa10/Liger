package com.sparta.ac.jdbc;

import com.sparta.ac.jdbc.logging.config.CustomFormatter;
import com.sparta.ac.jdbc.logging.config.FileHandlerConfig;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Phase3 {
    public static final Logger logger = Logger.getLogger(Phase3.class.getName());

    public static void main(String[] args) {
        StartPhase3();
    }

    public static void StartPhase3 () {
        try {
            employeeLogger();
            //Ask user fileNames to load data from CSV, XML or JSON Files
            Scanner scan = new Scanner(System.in);
            System.out.println("Project Phase 3");
            System.out.println("*** This program will load data in the database from CSV, XML or JSON Files ***");
            System.out.println("Please Choose file to load in the database (csv,json,xml)");
            System.out.println("1) employees01.csv");
            System.out.println("2) employees01.json");
            System.out.println("3) employees01.xml");
            System.out.print("Option: ");
            int option = scan.nextInt();
            if(option>3 || option<1){
                throw new RuntimeException("Invalid Entry");
            }
            System.out.println("File Loading...");
            String deptNo = scan.nextLine();

            ArrayList<String> allEmployeesList = new ArrayList<>();

            if(option==1) {
                allEmployeesList = EmployeeFactory.getAllEmployeesCSVList();
            } else if(option==2) {
                allEmployeesList = EmployeeFactory.getAllEmployeesJSONList();
            } else if(option==3) {
                allEmployeesList = EmployeeFactory.getAllEmployeesXMLList();
            }
            EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.createConnetion());

//            logger    --  logHowManyEmployeeRecordsRetrieved
//            logger.log(Level.INFO, "Employee Records Retrieved: "+employees.length);
            ArrayList<Employee> loe = new ArrayList<>();
            ArrayList<EmployeeBAD> lobr = new ArrayList<>();
            //createEmployeeRecords(loe,lobr, employees);
            createEmployeeRecordsFromList(loe,lobr, allEmployeesList);
//            displayRecords(loe,"Good Records");
//            displayBadRecords(lobr,"Bad Records");
            writeBadRecords(lobr);
            int recordsCreated = employeeDAO.createEmployeeRecords(loe);

            System.out.println("Source data file successfully loaded");
            System.out.println("Good Records inserted in the database: emp table");
            System.out.println("Bad Records file created: badRecords.txt");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public static void displayRecords(ArrayList<Employee> loe,String s) {
        System.out.println("List of Employees: "+s);
        for(Employee e:loe){
            System.out.println(e);
        }
    }

    public static void displayBadRecords(ArrayList<EmployeeBAD> loe,String s) {
        System.out.println("List of Bad Employees: "+s);
        for(EmployeeBAD e:loe){
            System.out.println(e);
        }
    }
    private static void writeBadRecords(ArrayList<EmployeeBAD> loe) {
        try {
            FileWriter file = new FileWriter("src/main/resources/badRecords.txt");

            for (EmployeeBAD e : loe) {
                file.write(e.toString());
            }
        } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }

    public static void employeeLogger(){
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        consoleHandler.setFormatter(new CustomFormatter());
        logger.setUseParentHandlers(false);

        //logger.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.addHandler(FileHandlerConfig.getFileHandler());
    }

    private static void createEmployeeRecordsFromList(List<Employee> employeeList,List<EmployeeBAD> listOfBadRecords, ArrayList<String> allEmployeesList) {
        EmployeeBAD eb;
        for (int i = 0; i < allEmployeesList.size(); i++) {
            String[] emp = allEmployeesList.get(i).split(",");

            int emp_no=0;
            String birth_date="";
            String first_name="";
            String last_name="";
            String gender="";
            String hire_date="";
            try {

            if(emp.length==6) {

                if (employeeValidated(emp[0], emp[1], emp[2], emp[3], emp[4], emp[5])) {

                    emp_no = Integer.parseInt(emp[0]);
                    birth_date  = emp[1];
                    first_name  = emp[2];
                    last_name   = emp[3];
                    gender      = emp[4];
                    hire_date   = emp[5];

                    Employee e = new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date);
                    employeeList.add(e);
                } else {
                    //logger    --  Add_Line_BADRECORD_HeaderRecord_Exist_in_csv
                    //logger.log(Level.WARNING, "Employee Record Failed Validation R: "+emp[0]);
                    eb = new EmployeeBAD(emp[0], emp[1], emp[2], emp[3], emp[4], emp[5]);
                    listOfBadRecords.add(eb);
                }
            } else {
                eb = new EmployeeBAD();
                eb.setEmp_no(emp[0]);
                listOfBadRecords.add(eb);
            }
                } catch (NumberFormatException e) {
                    eb = new EmployeeBAD(emp[0],emp[1],emp[2],emp[3],emp[4],emp[5]);
                    listOfBadRecords.add(eb);
                }
            }
        }

    private static boolean employeeValidated(String emp_no, String birth_date,
    String first_name,String last_name, String gender, String hire_date)
        {
        boolean empValidated = true;
        /*
        emp_no should be only digits up to 8 characters
        dates should be YYYY-MM-DD
        dates should be in the past
        birth_date should be after 1900-01-01
        hire_date should be more recent than birth_date
        names should only contain alpha chars, spaces and hyphens and should begin with a capital letter
       emp_no   birth_date  first_name last_name Gender hire_date
       10083	23/07/1959	Vishv	Zockler	M	31/03/1987

        */
            if(!checkEmpLength(emp_no)) { return false;}
            if(!checkGender(gender)){ return false;}
            if(!(checkDateFormat(birth_date) && checkDateFormat((hire_date)))) { return false;}
            if(!birthdate_HireDate(birth_date,hire_date)) { return false;}
            if(!nameCheck(first_name,last_name)) { return false;}

        return empValidated;
    }
    private static boolean birthdate_HireDate(String birthDate, String hireDate) {
        String[] bd = birthDate.split("-");
        String[] hd = hireDate.split("-");
        int year1=0,month1=0,day1=0;
        int year2=0,month2=0,day2=0;
        for(int i=0;i<bd.length;i++){
            year1=Integer.parseInt(bd[0]);
            year2=Integer.parseInt(hd[0]);
            month1=Integer.parseInt(bd[1]);
            month2=Integer.parseInt(hd[1]);
            day1=Integer.parseInt(bd[2]);
            day2=Integer.parseInt(hd[2]);
            if(bd.length>=3){  day1=Integer.parseInt(bd[2]); }else {return false;}
            if(year1>year2) {return false; }
            if(year1==year2 && month1>month2) { return false; }
            if(year1==year2 && month1==month2 && day1>=day2) { return false; }
        }
        return true;
    }
    private static boolean checkEmpLength(String emp_no) {
        boolean empCheck = true;
        if (emp_no.length()>8) return false;
        return empCheck;
    }
    private static boolean checkGender(String s) {
        boolean gCheck = true;
        if (s.length()>1) return false;
        if (!(s.equals("M") || s.equals("F"))) return false;
        return gCheck;
    }
    private static boolean birth_dateCheck() {
        boolean dateCheck = true;
        //birth_date<="1900-01-01";
        return dateCheck;
    }
    private static boolean nameCheck(String firstName, String lastName) {
    boolean nameValidated=true;
    boolean isFNUpperCase = Character.isUpperCase(firstName.charAt(0));
    boolean isLNUpperCase = Character.isUpperCase(lastName.charAt(0));
    if(!(isFNUpperCase && isLNUpperCase)){
        return false;
    }
    return nameValidated;
    }

    private static boolean checkDateFormat(String d1) {
        boolean correctDateFormat = true;
        String[] arr = d1.split("-");
        int year=0,month=0,day=0;
        for(int i=0;i<arr.length;i++){
            year=Integer.parseInt(arr[0]);
            month=Integer.parseInt(arr[1]);
            if(arr.length>=3){  day=Integer.parseInt(arr[2]); }else {return false;}
            if(year<1900) return false;
            if(month>12) return false;
            if(day>31) return false;
        }
        return correctDateFormat;
    }
}
