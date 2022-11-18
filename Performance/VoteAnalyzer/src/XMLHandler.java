import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static HashMap<Voter, Integer> voterCounts = null;

    public XMLHandler() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        try {
            if (qName.equals("voter") && voter == null) {

                String name = attributes.getValue("name");
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(name, birthDay);

            } else if (qName.equals("visit") && voter != null) {

                int count = voterCounts.getOrDefault(voter, 0) + 1;
                voterCounts.put(voter, count);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printRepeatVoters () {

        for (Voter voter : voterCounts.keySet()) {
            int doneVote = voterCounts.get(voter);
            if (doneVote > 1) {
                System.out.println("> " + voter.getName() + " :: voted " + doneVote + " times");
            }
        }
    }

}
