package com.sparta.ac.jdbc;

public interface SQLQueries {
    String SELECT_ALL = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date from employees.employees";
    String CREATE = "INSERT INTO `employees`.`emp` (`emp_no`, `birth_date`, `first_name`, `last_name`, `gender`, `hire_date`) VALUES (?,?,?,?,?,?)";
    //String DEPT_EMP_JOIN = "select e.emp_no,d.dept_no,d.dept_name,e.from_date,e.to_date from dept_emp e,departments d where d.dept_no=e.dept_no";
    String DEPT_EMP_JOIN = "select e.emp_no,d.dept_no,d.dept_name,e.from_date,e.to_date from dept_emp e,departments d where d.dept_no=e.dept_no and d.dept_no=? and from_date>=? and to_date<=?";
}
