import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class AddToMySQL {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "nokia3510";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;

    private Date date;
    private String name;
    private String text;
    private String namePCAndIP;
    private String status;

    AddToMySQL(Message message) {
        this.date = message.getDate();
        this.name = message.getName();
        this.text = message.getText();
        this.namePCAndIP = message.getNamePCAndIP();
        this.status = message.getStatus();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

            System.out.println(simpleDateFormat.format(date));

            // executing SELECT query
            String query = "INSERT INTO myshema.message (date, name, text, namePCAndIP, status) \n" +
                    " VALUES (\'" + simpleDateFormat.format(date) + "\', \'" + name +
                    "\', \'" + text + "\', \'" + namePCAndIP + "\', \'" + status + "\');";
            statement.executeUpdate(query);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }
}
