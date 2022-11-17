import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class LoaderForcedDB {

    private static final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();

        String fileName = "res/data-0.2M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandlerDB handler = new XMLHandlerDB();
        parser.parse(new File(fileName), handler);

        DBConnection.printVoterCounts();

        System.out.println("\ncalculation lasted: " + (System.currentTimeMillis() - begin) + " ms");

    }

}