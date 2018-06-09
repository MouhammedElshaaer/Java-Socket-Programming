import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataSource {
    byte[] getSerializedData() throws IOException,ClassNotFoundException;
}
