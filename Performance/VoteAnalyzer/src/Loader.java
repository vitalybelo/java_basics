import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * В этом классе реализована обработка входных данных моделью DOM Document
 * Данные накапливаются в переменную StringBuilder, размер буфера которой отслеживается
 * чтобы не получить - PacketTooBigException (определяется значением max_allowed_packet)
 * в классе DBConnection определена константа задающая длину переменной StringBuilder
 * Когда размер буфера достигает установленного предела, выполняется multi insert в базу.
 * Буфер обнуляется, продолжается обработка входного файла до полного окончания данных
 * В зависимости от установленного размера буфера и входных данных может выполняться
 * один или несколько запросов Multi Insert.
 *
 */
public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";

        long begin = System.currentTimeMillis();
        parseFile(fileName);

        DBConnection.printVoterCounts();

        System.out.println("\nPARSE Lasted: " + (System.currentTimeMillis() - begin) + " ms");
    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();

            DBConnection.countVoter(name, birthDay);
        }
        DBConnection.executeMultiInsert();
    }

}