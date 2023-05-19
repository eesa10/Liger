import com.sparta.ac.jdbc.Employee;
import com.sparta.ac.jdbc.EmployeeDAO;
import com.sparta.ac.jdbc.EmployeeDept;
import com.sparta.ac.jdbc.SQLQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class EmployeeDAOTest {
    private Connection connection;
    private Statement statement;
    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setUp() {
        connection = mock(Connection.class);
        statement = mock(Statement.class);
        employeeDAO = new EmployeeDAO(connection);
    }

    @Test
    public void testGetListofEmployees() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("1990-01-01");
        when(resultSet.getString(3)).thenReturn("John");
        when(resultSet.getString(4)).thenReturn("Smith");
        when(resultSet.getString(5)).thenReturn("Male");
        when(resultSet.getString(6)).thenReturn("2022-01-01");

        ArrayList<Employee> employees = employeeDAO.getListofEmployees();

    }

    @Test
    public void testGetListofEmployeesDept() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("D001");
        when(resultSet.getString(3)).thenReturn("Development");
        when(resultSet.getString(4)).thenReturn("2022-01-01");
        when(resultSet.getString(5)).thenReturn("2022-12-31");

        ArrayList<EmployeeDept> employeesDept = employeeDAO.getListofEmployeesDept("D001", "2022-01-01", "2022-12-31");

    }
}
