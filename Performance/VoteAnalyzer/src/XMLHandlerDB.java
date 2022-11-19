import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.SQLException;

public class XMLHandlerDB extends DefaultHandler {

    private VoterDB voter = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equals("voter") && voter == null) {

            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            voter = new VoterDB(name, birthDay);

        } else if (qName.equals("visit") && voter != null) {
            try {
                DBConnection.countVoter(voter.getName(), voter.getBirthDay());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }


}
