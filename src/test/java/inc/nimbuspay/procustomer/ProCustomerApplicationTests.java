
package inc.nimbuspay.procustomer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test") 
class ProCustomerApplicationTests {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Test
    void contextLoads() {
        String str = "dummy";
        assertEquals("dummy", str);
    }

    @Test
    void testDatabaseConnection() throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            assertNotNull(connection);
        }
    }
}
