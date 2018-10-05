package Database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DatabaseManagerTest {
    //Enter your mysql password
    DatabaseManager db;
    String password = "00000000";

    @BeforeEach
    void setUp() {
        db = null;
    }

    @AfterEach
    void tearDown() {
        db.closeConnection();
    }


    @Test
    void connect() {
        try {
            db = new DatabaseManager(password);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }


        try {
            db = new DatabaseManager("WRONG");
        } catch (Exception e) {
            assertTrue("Exception should be thrown", true);
        }

    }


    @Test
    void add() {

    }

    @Test
    void resetDatabase() {
    }

    @Test
    void closeConnection() {
    }

    @Test
    void getAll() {
    }
}