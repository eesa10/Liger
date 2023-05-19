import com.sparta.ac.jdbc.*;
import com.sparta.ac.jdbc.logging.config.CustomFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentMatchers;

public class Phase3Test {

//    @Test
//    public void testStartPhase3() throws Exception {
//        // Mock EmployeeFactory.getAllEmployeesList()
//        List<String> allEmployeesList = Arrays.asList("1,1990-01-01,John,Doe,M,2022-01-01", "2,1990-02-02,Jane,Smith,F,2022-02-02");
//        EmployeeFactory employeeFactory = mock(EmployeeFactory.class);
//        when(employeeFactory.getAllEmployeesList()).thenReturn(new ArrayList<>(allEmployeesList));
//
//        // Mock ConnectionManager.createConnection()
//        ConnectionManager connectionManager = mock(ConnectionManager.class);
//        Connection connection = mock(Connection.class);
//        when(connectionManager.createConnection()).thenReturn(connection);
//
//        // Manual instantiation of EmployeeDAO
//        EmployeeDAO employeeDAO = new EmployeeDAO(connection);
//
//        // Mock System.out and set it as the current output stream
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//
//        // Call the method under test
//        Phase3.StartPhase3();
//
//        // Verify method invocations and output
//        verify(employeeFactory).getAllEmployeesList();
//        verify(connectionManager).createConnection();
//        verify(employeeDAO).createEmployeeRecords(ArgumentMatchers.<ArrayList<Employee>>anyList());
//        assertEquals("List of Employees: Good Records\n" +
//                "Employee [emp_no=1, birth_date=1990-01-01, first_name=John, last_name=Doe, gender=M, hire_date=2022-01-01]\n" +
//                "Employee [emp_no=2, birth_date=1990-02-02, first_name=Jane, last_name=Smith, gender=F, hire_date=2022-02-02]\n" +
//                "List of Bad Employees: Bad Records\n", outputStream.toString());
//    }


//    @Test
//    void testEmployeeLogger() {
//        Logger logger = mock(Logger.class);
//        ConsoleHandler consoleHandler = mock(ConsoleHandler.class);
//        Handler fileHandler = mock(Handler.class);
//
//        // Mock the behavior of ConsoleHandler
//        doReturn(Level.WARNING).when(consoleHandler).getLevel();
//        doNothing().when(consoleHandler).setLevel(Level.WARNING);
//        doNothing().when(consoleHandler).setFormatter(any(CustomFormatter.class));
//
//        // Mock the behavior of Logger
//        doReturn(new Handler[0]).when(logger).getHandlers();
//        doNothing().when(logger).setUseParentHandlers(false);
//        doNothing().when(logger).addHandler(consoleHandler);
//        doNothing().when(logger).addHandler(fileHandler);
//
//        // Mock the behavior of FileHandlerConfig
//        doReturn(fileHandler).when(FileHandlerConfig).getFileHandler();
//
//        // Call the method under test
//        EmployeeLogger.employeeLogger();
//
//        // Verify method invocations and assertions
//        verify(consoleHandler).setLevel(Level.WARNING);
//        verify(consoleHandler).setFormatter(any(CustomFormatter.class));
//        verify(logger).getHandlers();
//        verify(logger).setUseParentHandlers(false);
//        verify(logger).addHandler(consoleHandler);
//        verify(logger).addHandler(fileHandler);
//        verifyStatic(FileHandlerConfig.class);
//        FileHandlerConfig.getFileHandler();
//
//        // Additional assertions if needed
//        // assertEquals(Level.ALL, logger.getLevel());
//    }

    @Test
    @DisplayName("Test that valid records are created from the createEmployeeRecordsFromList method")
    public void testCreateEmployeeRecordsFromList() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<EmployeeBAD> listOfBadRecords = new ArrayList<>();
        ArrayList<String> allEmployeesList = new ArrayList<>();
        allEmployeesList.add("10083,23/07/1959,Vishv,Zockler,M,31/03/1987");

        Phase3.createEmployeeRecordsFromList(employeeList, listOfBadRecords, allEmployeesList);

        int expectedEmployeeListSize = 1;
        int expectedBadRecordsListSize = 0;

        assertEquals(expectedEmployeeListSize, employeeList.size());
        assertEquals(expectedBadRecordsListSize, listOfBadRecords.size());
    }

    @Test
    @DisplayName("Test that invalid records are created from the createEmployeeRecordsFromList method")
    public void testCreateEmployeeRecordsFromList_InvalidRecords() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<EmployeeBAD> listOfBadRecords = new ArrayList<>();
        ArrayList<String> allEmployeesList = new ArrayList<>();
        allEmployeesList.add("10083,23/07/1959,Vishv,Zockler,M");

        Phase3.createEmployeeRecordsFromList(employeeList, listOfBadRecords, allEmployeesList);

        int expectedEmployeeListSize = 0;
        int expectedBadRecordsListSize = 1;

        assertEquals(expectedEmployeeListSize, employeeList.size());
        assertEquals(expectedBadRecordsListSize, listOfBadRecords.size());
    }

//    @Test
//    public void testDisplayRecords() {
//        // Redirect System.out to capture printed output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        PrintStream originalPrintStream = System.out;
//        System.setOut(printStream);
//
//        ArrayList<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1, "1990-01-01", "John", "Doe", "M", "2021-01-01"));
//
//        String expectedOutput = "List of Employees: Good Records\n" +
//                "Employee{emp_no=1, birth_date='1990-01-01', first_name='John', last_name='Doe', gender='M', hire_date='2021-01-01'}\n";
//
//        Phase3.displayRecords(employees, "Good Records");
//
//        // Reset System.out
//        System.out.flush();
//        System.setOut(originalPrintStream);
//
//        assertEquals(expectedOutput, outputStream.toString());
//    }

//    @Test
//    public void testDisplayBadRecords() {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        PrintStream originalPrintStream = System.out;
//        System.setOut(printStream);
//
//        ArrayList<EmployeeBAD> badRecords = new ArrayList<>();
//        badRecords.add(new EmployeeBAD("2", "1992-01-01", "Jane", "Smith", "F", "2022-01-01"));
//
//        String expectedOutput = "List of Bad Employees: Bad Records\n" +
//                "EmployeeBAD{emp_no='2', birth_date='1992-01-01', first_name='Jane', last_name='Smith', gender='F', hire_date='2022-01-01'}\n";
//
//        Phase3.displayBadRecords(badRecords, "Bad Records");
//
//        System.out.flush();
//        System.setOut(originalPrintStream);
//
//        assertEquals(expectedOutput, outputStream.toString());
//    }

    @Test
    @DisplayName("Checking that check Employee length method works correctly")
    void testCheckEmpLength() {
        assertFalse(Phase3.checkEmpLength("123456789"));  // Expected: false
        assertTrue(Phase3.checkEmpLength("12345678"));  // Expected: true
    }

    @Test
    @DisplayName("Check that checkGender method returns expected output")
    void testCheckGender() {
        assertFalse(Phase3.checkGender("MF"));  // Expected: false

        assertTrue(Phase3.checkGender("M"));  // Expected: true

        assertTrue(Phase3.checkGender("F"));  // Expected: true

        assertFalse(Phase3.checkGender("X"));  // Expected: false
    }

    @Test
    @DisplayName("Test that name is in correct format and returns correct output")
    void testNameCheck() {
        assertTrue(Phase3.nameCheck("John", "Doe"));  // Expected: true

        assertFalse(Phase3.nameCheck("jane", "Smith"));  // Expected: false

        assertFalse(Phase3.nameCheck("John", "doe"));  // Expected: false

        assertFalse(Phase3.nameCheck("jane", "smith"));  // Expected: false
    }

    @Test
    @DisplayName("Test that checkDateFormat returns expected output")
    void testCheckDateFormat() {
        assertTrue(Phase3.checkDateFormat("01/01/2022"));  // Expected: true

        assertFalse(Phase3.checkDateFormat("01012022"));  // Expected: false

        assertFalse(Phase3.checkDateFormat("01-01-2022"));  // Expected: false
    }

}