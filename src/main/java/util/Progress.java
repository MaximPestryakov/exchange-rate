package util;

public class Progress {

  private static final char[] LOADING_CHARS = new char[]{'|', '/', '-', '\\'};
  private Thread thread;

  public Progress() {
    thread = new Thread(() -> {
      try {
        for (int i = 0; !Thread.currentThread().isInterrupted(); ++i) {
          System.out.print("\rLoading " + LOADING_CHARS[i]);
          if (i == LOADING_CHARS.length - 1) {
            i = -1;
          }
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
  }

  public void start() {
    thread.start();
  }

  public void stop() {
    System.out.print("\r         \r");
    System.out.flush();
    thread.interrupt();
  }

}
