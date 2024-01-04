package bitcamp.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedDataInputStream extends DataInputStream {

  int size;
  int cursor;
  private byte[] buffer = new byte[8192];

  public BufferedDataInputStream(String name) throws FileNotFoundException {
    super(name);
  }

  @Override
  public int read() throws IOException {
    if (size == 0) {
      size = super.read(buffer);
      if (size < 0) {
        return -1;
      }
      cursor = 0;
    }
    return buffer[cursor++]; // 주의!
  }
}
