import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLTicTacToe implements FileOfGameTicTacToe {
    public void write(String outPath, PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) throws XMLStreamException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);
        writer.writeStartDocument("utf-8", "1.0");

        writer.writeStartElement("GamePlay");

        writer.writeStartElement("Player");
        writer.writeAttribute("id", playerX.getID());
        writer.writeAttribute("name", playerX.getName());
        writer.writeAttribute("symbol", String.valueOf(playerX.getSymbol()));
        writer.writeEndElement();

        writer.writeStartElement("Player");
        writer.writeAttribute("id", player0.getID());
        writer.writeAttribute("name", player0.getName());
        writer.writeAttribute("symbol", String.valueOf(player0.getSymbol()));
        writer.writeEndElement();

        writer.writeStartElement("Game");
        int j = 0;
        for (int i = 1; i <= playerX.getSteps().size() + player0.getSteps().size(); i++) {
            writer.writeStartElement("Step");
            writer.writeAttribute("num", String.valueOf(i));
            if (i % 2 != 0) {
                writer.writeAttribute("playerId", playerX.getID());
                writer.writeCharacters(String.valueOf(playerX.getSteps().get(j)));
            } else {
                writer.writeAttribute("playerId", player0.getID());
                writer.writeCharacters(String.valueOf(player0.getSteps().get(j)));
                j++;
            }
            writer.writeEndElement();
        }
        writer.writeEndElement();

        writer.writeStartElement("GameResult");
        if (winner.equals("Draw!")) {
            writer.writeCharacters(winner);
        } else {
            writer.writeStartElement("Player");
            if (winner.equals(playerX.getName())) {
                writer.writeAttribute("id", playerX.getID());
                writer.writeAttribute("name", playerX.getName());
                writer.writeAttribute("symbol", String.valueOf(playerX.getSymbol()));
            } else {
                writer.writeAttribute("id", player0.getID());
                writer.writeAttribute("name", player0.getName());
                writer.writeAttribute("symbol", String.valueOf(player0.getSymbol()));
            }
            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();

        String xml = out.toString(StandardCharsets.UTF_8);
        File file = new File(outPath + ".xml");
        try {
            String prettyPrintXML = formatXML(xml);
            Files.writeString(Paths.get(String.valueOf(file)), prettyPrintXML, StandardCharsets.UTF_8);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatXML(String xml) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));
        return output.toString();
    }

    public void read(String outPath) throws IOException, XMLStreamException {
        char[] fieldGame = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char step = ' ';


        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(outPath + ".xml"));
        int eventType;

        while (reader.hasNext()) {
            eventType = reader.next();
            if (eventType == XMLEvent.START_ELEMENT) {
                if (reader.getName().getLocalPart().equals("Step")) {
                    String playerId = reader.getAttributeValue(null, "playerId");
                    if (playerId.equals("1")) step = 'X';
                    if (playerId.equals("2")) step = '0';
                    eventType = reader.next();
                }
                if (eventType == XMLEvent.CHARACTERS) {
                    switch (AdapterGameField.adapter(reader.getText())) {
                        case "1" -> fieldGame[0] = step;
                        case "2" -> fieldGame[1] = step;
                        case "3" -> fieldGame[2] = step;
                        case "4" -> fieldGame[3] = step;
                        case "5" -> fieldGame[4] = step;
                        case "6" -> fieldGame[5] = step;
                        case "7" -> fieldGame[6] = step;
                        case "8" -> fieldGame[7] = step;
                        case "9" -> fieldGame[8] = step;
                    }
                    for (int i = 0; i < 9; i += 3) {
                        System.out.println("|" + fieldGame[i] + "|" + fieldGame[i + 1] + "|" + fieldGame[i + 2] + "|");
                    }
                    System.out.println();
                }
            }
            if (eventType == XMLEvent.START_ELEMENT) {
                if (reader.getName().getLocalPart().equals("GameResult")) {
                    eventType = reader.next();
                    if (eventType == XMLEvent.CHARACTERS) {
                        if (reader.getText().equals("Draw!")) {
                            System.out.println(reader.getText());
                        }
                        eventType = reader.next();
                        if (eventType == XMLEvent.START_ELEMENT) {
                            if (reader.getName().getLocalPart().equals("Player")) {
                                String id = reader.getAttributeValue(null, "id");
                                String name = reader.getAttributeValue(null, "name");
                                String symbol = reader.getAttributeValue(null, "symbol");
                                System.out.println("Player " + id + " -> " + name + " is winner as '" + symbol + "'!");
                            }
                        }
                    }
                }
            }
        }
    }
}
