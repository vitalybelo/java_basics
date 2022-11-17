import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandlerDB extends DefaultHandler {

    private VoterDB voter;
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static HashMap<VoterDB, Integer> voterCounts = null;

    public XMLHandlerDB() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        if (qName.equals("voter") && voter == null) {

            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            voter = new VoterDB(name, birthDay);

        } else if (qName.equals("visit") && voter != null) {
            try {
                DBConnection.countVoter(voter.getName(), voter.getBirthDay());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("voter")) voter = null;
    }

    public void printRepeatVoters () {

        for (VoterDB voter : voterCounts.keySet()) {
            int doneVote = voterCounts.get(voter);
            if (doneVote > 1) {
                System.out.println("> " + voter.getName() + " :: voted " + doneVote + " times");
            }
        }

    }

}
