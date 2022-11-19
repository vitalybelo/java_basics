import java.sql.*;

public class DBConnection {

    private static final int maxQuerySize = 10_000_000;
    private static Connection connection;
    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "Vitalex88";
    private static boolean insertNext = false;
    private static int insertCount = 0;
    private static final StringBuilder query_builder = new StringBuilder();

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
                    "birthDate DATE NOT NULL, " +
                    "counter INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert () throws SQLException {

        if (query_builder.length() > 0) {
            System.out.print("buffered: " + query_builder.length());

            query_builder.insert(0, "INSERT INTO voter_count(name, birthDate, counter) VALUES");
            query_builder.append(" ON DUPLICATE KEY UPDATE counter = counter + 1");
            Statement sql = DBConnection.getConnection().createStatement();
            sql.executeUpdate(query_builder.toString());
            sql.close();
            System.out.println(" -->> INSERT INTO #" + (++insertCount));

            // обнуляем строку и флаг первой вставки
            insertNext = false;
            query_builder.setLength(0);
        }
    }

    public static void countVoter(String name, String birthDay) throws SQLException {

        birthDay = birthDay.replace('.', '-');

        if (insertNext) {
            query_builder.append(",");
        } else insertNext = true;
        query_builder.append("('").append(name).append("', '").append(birthDay).append("', 1)");

        if (query_builder.length() > maxQuerySize) {
            executeMultiInsert();
        }

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
