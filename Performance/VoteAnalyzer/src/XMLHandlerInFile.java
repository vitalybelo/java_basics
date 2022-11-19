import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandlerInFile extends DefaultHandler {

    private String voterName = "";
    private String voterBirthDate = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equals("voter") && voterName.isEmpty())
        {
            voterName = attributes.getValue("name");
            voterBirthDate = attributes.getValue("birthDay");
        }
        else if (qName.equals("visit") && !voterName.isEmpty())
        {
            LoaderInFile.countVoters(voterName, voterBirthDate);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voterName = "";
        }
    }

}
