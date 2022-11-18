import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * В этом классе реализована пошаговая обработка через SAX
 * Данные накапливаются в переменную StringBuilder, размер буфера которой отслеживается
 * чтобы не получить - PacketTooBigException (определяется значением max_allowed_packet)
 * в классе DBConnection определена константа задающая длину переменной StringBuilder
 * Когда размер буфера достигает установленного предела, выполняется multi insert в базу.
 * Буфер обнуляется, продолжается обработка входного файла до полного окончания данных
 * В зависимости от установленного размера буфера и входных данных может выполняться
 * один или несколько запросов Multi Insert.
 *
 */
public class LoaderForcedDB {

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();

        String fileName = "res/data-1572M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandlerDB handler = new XMLHandlerDB();
        parser.parse(new File(fileName), handler);

        DBConnection.executeMultiInsert();
        DBConnection.printVoterCounts();

        System.out.println("\nParse Lasted: " + (System.currentTimeMillis() - begin) + " ms");

    }

}