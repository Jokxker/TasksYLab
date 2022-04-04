import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJdbcConnection {
    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:mem:test";
        String user = "sa";
        String passwd = "sa";
        return DriverManager.getConnection(url, user, passwd);
    }

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        try(Connection connection = getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }
}
