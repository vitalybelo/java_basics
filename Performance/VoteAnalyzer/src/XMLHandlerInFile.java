import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.PrintWriter;
import java.util.HashMap;

public class XMLHandlerInFile extends DefaultHandler {

    private VoterDB voter;
    private static final String datafile = "data/data.csv";
    private static HashMap<VoterDB, Integer> voterCounts = null;

    public XMLHandlerInFile() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equals("voter") && voter == null)
        {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            birthDay = birthDay.replace('.', '-');
            voter = new VoterDB(name, birthDay);
        }
        else if (qName.equals("visit") && voter != null)
        {
            int count = voterCounts.getOrDefault(voter, 0) + 1;
            voterCounts.put(voter, count);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void storageVoters () {
        String line;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(datafile);
            for (VoterDB v : voterCounts.keySet()) {
                int doneVote = voterCounts.get(v);
                line = "\"" + v.getName() + "\",\"" + v.getBirthDay() + "\"," + doneVote +"\r\n";
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }

    String getDatafile () {
        return datafile;
    }

}
