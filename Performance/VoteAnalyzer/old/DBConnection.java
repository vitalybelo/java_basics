import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "Vitalex88";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate TINYTEXT NOT NULL, " +
                    "counter INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate(10)))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException {

        birthDay = birthDay.replace('.', '-');

        String sql = "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        if (!rs.next()) {
            DBConnection.getConnection().createStatement()
                .execute("INSERT INTO voter_count(name, birthDate, counter) VALUES('" +
                    name + "', '" + birthDay + "', 1)");
        } else {
            Integer id = rs.getInt("id");
            DBConnection.getConnection().createStatement()
                .execute("UPDATE voter_count SET counter = counter + 1 WHERE id=" + id);
        }
        rs.close();

    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, counter FROM voter_count WHERE counter > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println(">" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("counter"));
        }
    }
}
