import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.*;

/**
 * В этом классе используется пошаговая обработка xml файла через SAX
 * Данные накапливаются в HashMap в памяти (вместо StringBuilder) в XMLHandlerInFile
 * Потом, обработанные данные сбрасываются на локальный диск в файл .csv
 * Загрузка данных из файла выполняется sql запросом LOAD DATA LOCAL INFILE
 * После того как закончился парсинг и запись данных на диск - скорость
 * зависит только от настроек сервера (в основном выделяемой памяти)
 * Длительность обработки xml файла 1572М до полной готовности загрузки данных
 * в базу составляет примерно 25-30 секунд. Скорость работы mysql у каждого своя
 *
 */
public class LoaderInFile {

    public static void main(String[] args) throws Exception {

        String xmlFile = "res/data-18M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandlerInFile handler = new XMLHandlerInFile();

        long step1 = System.currentTimeMillis();
        parser.parse(new File(xmlFile), handler);
        System.out.println("PARSED IN MEM: " + (System.currentTimeMillis() - step1) + " ms");

        long step2 = System.currentTimeMillis();
        handler.storageVoters();
        System.out.println("STORED IN FILE: " + (System.currentTimeMillis() - step2) + " ms");

        long step3 = System.currentTimeMillis();
        generateConnection(handler.getDatafile());
        System.out.println("\nLOADED TABLE INFILE: " + (System.currentTimeMillis() - step3) + " ms");
        System.out.println("COMPLETE: " + (System.currentTimeMillis() - step1) + " ms");

    }

    public static void generateConnection (String datafile) {

        // Для установки привилегий серверу используйте команду
        // server command mysql> SET GLOBAL local_infile = true;

        String dbName = "learn";
        String dbUser = "root";
        String dbPass = "Vitalex88";
        String localhost = "jdbc:mysql://localhost:3306/";

        try {
            // подключение к базе
            Connection connection = DriverManager.getConnection(
                    localhost + dbName + "?user=" + dbUser + "&password=" + dbPass + "&allowLoadLocalInfile=true");
            // удаление старой таблицы
            Statement sql = connection.createStatement();
            sql.execute("DROP TABLE IF EXISTS voter_count");
            // создание новой таблицы
            sql.execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "counter INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");
            // загрузка данных из файла
            sql.execute("LOAD DATA " +
                    "LOCAL INFILE '" + datafile +"' INTO TABLE voter_count " +
                    "FIELDS TERMINATED BY ',' " +
                    "ENCLOSED BY '\"' " +
                    "LINES TERMINATED BY '\\r\\n' " +
                    "(name, birthDate, counter)");

            // отображаем проголосовавших больше одного раза
            ResultSet rs = sql.executeQuery("SELECT name, birthDate, counter FROM voter_count WHERE counter > 1");

            while (rs.next()) {
                System.out.println(":: > " + rs.getString("name") + " (" +
                        rs.getString("birthDate") + ") - " + rs.getInt("counter"));
            }
            rs.close();
            sql.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
