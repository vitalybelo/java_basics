import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * В классе реализован пошаговый парсинг через SAX
 * Накопление данных выполняется в HashMap, за счет чего самая высокая производительность
 * Недостатки: ограничение оперативной памяти не позволяют обрабатывать большие файлы
 *
 */
public class LoaderForced {

    public static void main(String[] args) throws Exception {

        String fileName = "res/data-18M.xml";

        long begin = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        handler.printRepeatVoters();
        System.out.println("\ncalculation lasted: " + (System.currentTimeMillis() - begin) + " ms");

    }

}