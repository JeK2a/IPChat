import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class Settings {
    static int PORT;
    static String SERVER_PC;
    static int SIZE_HISTORY;
    static int SIZE_MAX_CLIENTS;

    Settings() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
//            File file = new File("E:\\Dropbox\\IPChatv3\\configChat.xml");
            File file = new File("configChat.xml");
            doc = builder.parse(file);
//          URL url = new URL("http://jc3.ucoz.com/_ld/0/1_configChat.xml");
//          doc = builder.parse(url.openStream());
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            System.err.println(ex);
        }

        assert doc != null;
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element childElement = (Element) child;
                Text textNode = (Text) childElement.getFirstChild();
                String text = textNode.getData().trim();

                switch (childElement.getTagName()) {
                    case "port":
                        PORT = Integer.parseInt(text);
                        break;
                    case "server_pc":
                        SERVER_PC = text;
                        break;
                    case "size_history":
                        SIZE_HISTORY = Integer.parseInt(text);
                        break;
                    case "size_max_clients":
                        SIZE_MAX_CLIENTS = Integer.parseInt(text);
                        break;
                }
            }
        }
    }
}
