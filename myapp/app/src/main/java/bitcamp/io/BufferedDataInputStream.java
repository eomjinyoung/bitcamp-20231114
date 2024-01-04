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
    if (cursor == size) {
      cursor = 0;
      size = super.read(buffer);
      if (size == -1) {
        return -1;
      }
    }
    return buffer[cursor++] & 0xff; // 주의!
  }

  @Override
  public int read(byte[] arr) throws IOException {
    return read(arr, 0, arr.length);
  }

  @Override
  public int read(byte[] arr, int off, int len) throws IOException {
    for (int i = off, count = 0; count < len; i++, count++) {
      int b = read();
      if (b == -1) {
        return count > 0 ? count : -1;
      }
      arr[i] = (byte) b;
    }
    return len;
  }
}
