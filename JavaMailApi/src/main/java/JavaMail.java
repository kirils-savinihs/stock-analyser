import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaMail {

    protected List<String> recipients = new ArrayList<String>();
    protected Connection conn;


    public static void main(String[] args) {
        //the main is only used for testing
        //this takes info of database of clients
        List<String> test1 = new ArrayList<String>();
        List<String> test2 = new ArrayList<String>();
        List<String> test3 = new ArrayList<String>();

        String t = "<p>Good:<br>1)Facebook <br>2)Twitter </p><p>Bad:<br>1)Bing<br>2)Vine</p>";

        test1.add("Facebook");
        test1.add("Facebook");
        test2.add("Twitter");
        test2.add("Twitter");
        test3.add("Bing");
        test3.add("Bing");
        new JavaMail().FormForTesting(test1, test2, test3);
        //new JavaMail().FrontEnd(test1, test2, test3);
    }

    public void FrontEnd(List<String> a, List<String> b, List<String> c){

        SendMessage(FormSQL(),a,b,c);
    }

    public void FormForTesting(List<String> a, List<String> b, List<String> c) {
        recipients.add("testjava018@gmail.com");
        //recipients.add("incorectvalue");
        //recipients.add("ingus.p98@gmail.com");
        SendMessage(recipients, a, b, c);
    }

    public List<String> FormSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", "abcd1234");
            conn.setAutoCommit(false);

            //get emails from SQL Database
            PreparedStatement ps = conn.prepareStatement("select eMail from database_activity.Clients");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recipients.add(rs.getString("eMail"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipients;
    }

    public boolean SendMessage(List<String> rec, List<String> info1, List<String> info2, List<String> info3) {
        String from = "testjava018@gmail.com";
        final String username = "testjava018@gmail.com";
        final String password = "JavaTest123";
        String mes1="";
        String mes2="";
        String mes3="";
        String format="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "* {\n" +
                "    box-sizing: border-box;\n" +
                "}"+".column {\n" +
                "    float: left;\n" +
                "    width: 33%;\n" +
                "    padding: 20px;\n" +
                "    font-size: 22px;\n"+
                "    list-style-type: square;\n"+
                "    height: 300px; /* Should be removed. Only for demonstration */\n" +
                "}"+".row:after {\n" +
                "    content: \"\";\n" +
                "    display: table;\n" +
                "    clear: both;\n" +
                "}\n" +
                "p {\n" +
                "    font-size: 15px;\n" +
                "}"+
                "</style>\n" +
                "</head>\n" +
                "<body>";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        for(int i=0; i<info1.size(); i++){
            mes1=mes1+"<li>"+info1.get(i)+"</li>";
            mes2=mes2+"<li>"+info2.get(i)+"</li>";
            mes3=mes3+"<li>"+info3.get(i)+"</li>";
        }

        boolean send=false;
        try {

            for(int i = 0; i < rec.size(); i++){
                send=false;
                String use=rec.get(i);
                String name="";
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));


                message.addRecipient(Message.RecipientType.TO, new InternetAddress(rec.get(i)));
                System.out.println("Recipient formed.");


                // Set Subject: header field
                message.setSubject(dtf.format(now) + " Stock rate changes!");
                int token=0;
                while(name.equals("")&&token<use.length()){
                    if(use.charAt(token)=='@'){
                        name=use.substring(0,token);
                    }
                    token++;
                }

                // Now set the actual message
                //Message is gotten from another class + some regular text.

                message.setContent(format+"<h2>Dear "+name+",</h2><p>We present you with our latest stocks of interest.<br>These stocks have changed"+
                        " and should be in your desire to check out.</p>\n" +
                        "<div class=\"row\">\n" +
                        "  <div class=\"column\" style=\"background-color:#006400;\"><h3>Utilities</h3>" + mes1 +"</div>\n" +
                        "  <div class=\"column\" style=\"background-color:#008000;\"><h3>Financials</h3>"+ mes2 +"</div>\n" +
                        "  <div class=\"column\" style=\"background-color:#7FFF00;\"><h3>Consumers</h3>" + mes3 + " </div>\n" +
                        "</div><p>We hope this information is found useful.</p> <p>See you again tommorow.</p><h2>StockRatio Team.</h2>"+
                        "</body>\n" +
                        "</html>", "text/html; charset=utf-8");

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
                send=true;
            }

        } catch (Exception mex) {
            mex.printStackTrace();

        }
        return send;

    }

}
