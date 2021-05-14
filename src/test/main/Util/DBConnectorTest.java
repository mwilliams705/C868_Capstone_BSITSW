package main.Util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {

    Connection c  = DBConnector.getConnection();


    @BeforeEach
    void setUp() {
        DBConnector.startConnection();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testStartConnection() throws SQLException {
        System.out.println("Test Connection");
        System.out.println(c.getCatalog());
        assertNotNull(c);

    }

    @Test
    void getConnection() {
    }

    @Test
    void canCloseConnection() {
        System.out.println("Test Closing Connection");
        DBConnector.closeConnection();
        assertNull(c);
    }
}