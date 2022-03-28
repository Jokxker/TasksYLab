import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface FileOfGameTicTacToe {
    void write(String outputPath, PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) throws XMLStreamException, IOException;
    void read(String outPath) throws IOException, XMLStreamException;
}
