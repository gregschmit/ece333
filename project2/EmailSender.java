// EmailSender, the mail agent made by Greg and Phil.

import java.io.*;
import java.net.*;

public class EmailSender {

  public static void main(String[] args) throws Exception {
    // definitions
    String host = "mxwest2.uic.edu";
    Integer port = 25;
    String user = "gschmi4";
    String email_from = "gschmi4@uic.edu";
    String email_to = "phorwi2@uic.edu";
    String email_subject = "ece333 test email";
    String email_body = "This is my awesome email body.";

    // Establish a TCP connection with the mail server.
    Socket socket = new Socket(host, port);

    // Create a BufferedReader to read a line at a time.
    InputStream is = socket.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    // Read greeting from the server.
    String response = br.readLine();
    System.out.println(response);
    if (!response.startsWith("220")) {
       throw new Exception("220 reply not received from server.");
    }

    // Get a reference to the socket's output stream.
    OutputStream os = socket.getOutputStream();

    // Send HELO command and get server response.
    String command = "HELO " + user + "\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);
    if (!response.startsWith("250")) {
       throw new Exception("250 reply not received from server.");
    }

    // Send MAIL FROM command.
    command = "MAIL FROM: <" + email_from + ">\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);

    // Send RCPT TO command.
    command = "RCPT TO: <" + email_to + ">\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);

    // Send DATA command.
    command = "DATA\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);

    // Send message data (including subject).
    command = "SUBJECT: " + email_subject + "\r\n\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    command = email_body + "\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));

    // End with line with a single period.
    command = ".\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);

    // Send QUIT command.
    command = "QUIT\r\n";
    System.out.print(command);
    os.write(command.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);
  }
}
