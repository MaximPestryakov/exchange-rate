public class Progress {

  private Thread thread;
  private static final char[] LOADING_CHARS = new char[]{'|', '/', '-', '\\'};

  Progress() {
    thread = new Thread(() -> {
      for (int i = 0; !Thread.currentThread().isInterrupted(); ++i) {
        try {
          System.out.print("\rLoading" + LOADING_CHARS[i]);
          if (i == LOADING_CHARS.length - 1) {
            i = -1;
          }
          Thread.sleep(100);
        } catch (InterruptedException e) {
          System.out.println();
          Thread.currentThread().interrupt();
        }
      }
    });
  }

  void start() {
    thread.start();
  }

  void stop() {
    thread.interrupt();
  }

}
