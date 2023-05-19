import com.sparta.ac.jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionManagerTest {
    @Test
    void testCreateConnection() {
        Connection connection = ConnectionManager.createConnection();

        assertNotNull(connection);
    }

    @Test
    void testCloseConnection() {
        Connection connection = ConnectionManager.createConnection();

        assertThrows(SQLException.class, () -> {
            ConnectionManager.closeConnection();
            connection.createStatement();
        });
    }
}
