package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Cache {

  private static final ResourceBundle res = ResourceBundle.getBundle("Strings");
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
      System.err.println(res.getString("saving_cache_error"));
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
