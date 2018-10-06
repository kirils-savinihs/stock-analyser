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
        db = new DatabaseManager(password);

        db.resetDatabase();
        db.add("INTC");
        assertTrue(db.getAll().size() == 1);


    }

    @Test
    void resetDatabase() {
        db = new DatabaseManager(password);
        db.resetDatabase();
        assertTrue(db.getAll().size() == 0);
        db.add("INTC");
        assertTrue(db.getAll().size() == 1);
        db.resetDatabase();
        assertTrue(db.getAll().size() == 0);



    }

    @Test
    void closeConnection() {
        db = new DatabaseManager("00000000");

        assertTrue(db.add("INTC") == 0);

        db.closeConnection();

        assertTrue(db.add("INTC") == -1);


    }

    @Test
    void getAll() {
        db = new DatabaseManager("00000000");
        db.add("INTC", "INTC");
        assertTrue(db.getAll().size() == 2);

    }
}