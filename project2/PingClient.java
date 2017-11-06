// PingClient, the UDP ping client made by Greg and Phil

import java.io.*;
import java.net.*;

public class PingClient {

  public static void main(String[] args) throws Exception {
    // preconditions
    if (args.length < 2) {
      System.err.print("Too few parameters provided. Correct usage is:\n\tjava PingClient <host> <port>\n");
      System.exit(1);
    }

    // definitions
    String host = args[0];
    Integer port = Integer.parseInt(args[1]);
    System.out.println(host);
    System.out.println(port);

    
  }
}
