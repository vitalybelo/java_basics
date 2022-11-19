import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoaderInFile {

    private static final String datafile = "data/data.csv";
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();

        String xmlFile = "res/data-18M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandlerInFile handler = new XMLHandlerInFile();
        parser.parse(new File(xmlFile), handler);

        storageVoters();
        generateConnection();

        System.out.println("\nParse Lasted: " + (System.currentTimeMillis() - begin) + " ms");

    }

    public static void countVoters (String name, String birthDay)
    {
        birthDay = birthDay.replace('.', '-');
        builder.append("\"").append(name).append("\",\"").append(birthDay).append("\"\r\n");
    }

    public static void storageVoters () {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(datafile);
            writer.write(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }

    public static void generateConnection () {

        String dbName = "learn";
        String dbUser = "root";
        String dbPass = "Vitalex88";
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                            "?user=" + dbUser + "&password=" + dbPass + "&allowLoadLocalInfile=true");
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "counter INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");
            connection.createStatement().execute("LOAD DATA " +
                    "LOCAL INFILE '" + datafile +"' INTO TABLE learn.voter_count " +
                    "FIELDS TERMINATED BY ',' " +
                    "ENCLOSED BY '\"' " +
                    "LINES TERMINATED BY '\\r\\n' " +
                    "(name, birthDate, counter)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
