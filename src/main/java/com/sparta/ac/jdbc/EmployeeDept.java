package com.sparta.ac.jdbc;

public class EmployeeDept {
    int emp_no;
    String dept_no;
    String dept_name;
    String from_date;
    String to_date;

    public EmployeeDept() {
    }

    public EmployeeDept(int emp_no) {
        this.emp_no = emp_no;
    }

    public EmployeeDept(int emp_no, String dept_no, String dept_name, String from_date, String to_date) {
        this.emp_no = emp_no;
        this.dept_no = dept_no;
        this.dept_name = dept_name;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "EmployeeDept{" +
                "emp_no=" + emp_no +
                ", dept_no=" + dept_no +
                ", dept_name='" + dept_name + '\'' +
                ", from_date='" + from_date + '\'' +
                ", to_date='" + to_date + '\'' +
                '}';
    }
}
