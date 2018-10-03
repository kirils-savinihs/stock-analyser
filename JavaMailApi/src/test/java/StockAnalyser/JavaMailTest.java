package StockAnalyser;

import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import javax.mail.MessagingException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JavaMailTest {

    private static JavaMail mail;
    private static Connection conn;
    private static List<String> rec;

    private static Logger logger = Logger.getLogger(JavaMailTest.class);


    @SuppressWarnings("unchecked")
    @Test
    public void test00Exceptions() throws Exception{
        mail = new JavaMail();
        rec = Mockito.mock(ArrayList.class);
        mail.recipients=rec;
        Mockito.when(rec.get(Mockito.anyInt())).thenThrow(MessagingException.class);
        rec.add("test018@gmail.com");
        mail.SendMessage(rec, rec, rec, rec, rec, rec, rec, rec, rec, rec);
        Mockito.when(rec.add(Mockito.anyString())).thenThrow(SQLException.class);
        mail.FormSQL();

    }

    @Test
    public void test01FormSQL() {
        try {
            mail = new JavaMail();
            List<String> result=mail.FormSQL();
            conn = mail.conn;
            assertNotEquals("StockAnalyser.JavaMail connection is not initialized", null, conn);
            logger.debug("Connection successfully established!");
            assertEquals("Incorect data about email.", "ingus.p98@gmail.com", result.get(0));
            assertEquals("Incorect data about email.", "testjava018@gmail.com", result.get(1));
            logger.info("OK");
        } catch (Exception e) {
            String message = handleError(e);
            fail(message);
        }
    }

    @Test
    public void test02SendMessageTRUE() {
        try {
            List<String> rec = new ArrayList<String>();
            rec.add("test018@gmail.com");
            mail = new JavaMail();
            boolean result = mail.SendMessage(rec, rec, rec, rec, rec, rec, rec, rec, rec, rec);
            assertEquals("Incorect data about email.", true, result);
            logger.info("OK");

        } catch (Exception e) {
            String message = handleError(e);
            fail(message);
        }
    }

    @Test
    public void test03SendMessageFalse(){
        try {
            List<String> rec = new ArrayList<String>();
            rec.add("IncorectValue");
            mail = new JavaMail();
            boolean result = mail.SendMessage(rec, rec, rec, rec, rec, rec, rec, rec, rec, rec);
            assertEquals("Incorect data about email.", false, result);
            logger.info("OK");

        } catch (Exception e) {
        String message = handleError(e);
        fail(message);
    }
    }

    @Test
    public void test04SendMessageEmpty(){
        try {
            List<String> rec = new ArrayList<String>();
            mail = new JavaMail();
            boolean result = mail.SendMessage(rec, rec, rec, rec, rec, rec, rec, rec, rec, rec);
            assertEquals("Incorect data about email, accepted empty recipient list.", false, result);
            logger.info("OK");

        } catch (Exception e) {
            String message = handleError(e);
            fail(message);
        }
    }

    public static synchronized String handleError(Throwable e) {
        String message = "\n" + e.getClass().getName();
        String msg = e.getMessage();
        if (msg != null)
            message += ", message: " + e.getMessage();
        Throwable cause = e.getCause();
        while (cause != null) {
            message = message + "\ncause: " + cause.getClass().getName();
            msg = cause.getMessage();
            if (msg != null)
                message += ", message: " + msg;
            cause = cause.getCause();
        }
        message = message + "\nStack trace:\n" + Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n");
        logger.error(message);
        return message;
    }

}
