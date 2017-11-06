// PingClient, the UDP ping client made by Greg and Phil

import java.io.*;
import java.net.*;

public class PingClient {

  public static void main(String[] args) throws Exception {
    // preconditions
    if (args.length < 3) {
      System.err.print("Too few parameters provided. Correct usage is:\n\tjava PingClient <host> <port>")
    }

    // definitions
    String host = args[1];
    Integer port = 25;
    String user = "gschmi4";
    String email_from = "gschmi4@uic.edu";
    String email_to = "phorwi2@uic.edu";
    String email_subject = "ece333 test email";
    String email_body = "This is my awesome email body.";


  }
}
