import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestAddToMySQL {
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "nokia3510";

    static void test() {
        AddToMySQL.addMessageToMySQL(new Message(new Date(), "JeK2aTest", "testText", "testPC", "test"));
        System.out.println("TestAddToMySQL 1 Все ок!");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); // opening database connection to MySQL server

            if (connection == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }

            Statement statement = connection.createStatement(); // getting Statement object to execute query

            // executing SELECT query
            String query = "INSERT INTO myshema.message (date, name, text, namePCAndIP, status) \n" +
                    " VALUES (\'" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    "\', \'" + "JeK2aTest" + "\', \'" + "test" + "\', \'" + "test" + "\', \'" + "test" + "\');";
            statement.executeUpdate(query);
            System.out.println("TestAddToMySQL 2 Все ок!");
        } catch(SQLException | ClassNotFoundException ex){
            System.err.println(ex);
        }
    }
}