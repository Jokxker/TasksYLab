import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FileOfGameTicTacToe {
    void write(String outputPath, PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) throws XMLStreamException, IOException;
    void read(String outPath) throws IOException, XMLStreamException;
}
