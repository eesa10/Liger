package com.sparta.ac.jdbc;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class EmployeeDAO {
    private final Connection connection;
    private Statement statement;
    private PreparedStatement ps;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> getListofEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        int emp_no;
        String birth_date;
        String first_name;
        String last_name;
        String gender;
        String hire_date;

        try {
            ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_ALL);
            while (resultSet.next()) {
                emp_no = resultSet.getInt(1);
                birth_date = resultSet.getString(2);
                first_name = resultSet.getString(3);
                last_name = resultSet.getString(4);
                gender = resultSet.getString(5);
                hire_date = resultSet.getString(6);

                Employee emp = new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date);
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public ArrayList<EmployeeDept> getListofEmployeesDept(String deptNo, String fromDate, String toDate) {
        ArrayList<EmployeeDept> employeesDept = new ArrayList<>();
        int emp_no;
        String dept_no;
        String dept_name;
        String from_date;
        String to_date;

        try {
            //ResultSet resultSet = statement.executeQuery(SQLQueries.DEPT_EMP_JOIN);
            ps = connection.prepareStatement(SQLQueries.DEPT_EMP_JOIN);
            ps.setString(1, deptNo);
            ps.setString(2, fromDate);
            ps.setString(3, toDate);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                emp_no = resultSet.getInt(1);
                dept_no = resultSet.getString(2);
                dept_name = resultSet.getString(3);
                from_date = resultSet.getString(4);
                to_date = resultSet.getString(5);

                EmployeeDept empDept = new EmployeeDept(emp_no, dept_no, dept_name, from_date, to_date);
                employeesDept.add(empDept);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeesDept;

    }

    public int createEmployeeRecords(ArrayList<Employee> emp) {

        int emp_no;
        String birth_date;
        String first_name;
        String last_name;
        String gender;
        String hire_date;
        int recordsCreated = 0;

        for (Employee e: emp) {
            try {
                emp_no=e.getEmp_no();
                birth_date=e.getBirth_date();
                first_name=e.getFirst_name();
                last_name=e.getLast_name();
                gender=e.getGender();
                hire_date=e.getHire_date();
                ps = connection.prepareStatement(SQLQueries.CREATE);
                ps.setInt(1, emp_no);
                ps.setString(2, birth_date);
                ps.setString(3, first_name);
                ps.setString(4, last_name);
                ps.setString(5, gender);
                ps.setString(6, hire_date);

                recordsCreated = ps.executeUpdate();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
            return recordsCreated;

    }
}

