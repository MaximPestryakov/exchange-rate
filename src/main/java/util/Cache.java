package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cache {

  private static final String PATH = ".cache";

  public Cache() {
    try {
      Files.createDirectory(Paths.get(PATH));
    } catch (IOException e) {
      //Dir already exists, no problem
    }
  }

  public void write(String name, String content) {
    Path path = Paths.get(PATH + "/" + name);
    try {
      Files.write(path, content.getBytes());
    } catch (IOException e) {
      System.err.println("[Warning] An error occurred while saving cache");
    }
  }

  public String read(String name) {
    try {
      Path path = Paths.get(PATH + "/" + name);
      byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    } catch (IOException e) {
      //No cache, no problem :)
    }
    return null;
  }
}
