// PingClient, the UDP ping client made by Greg and Phil

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PingClient {

  public static void main(String[] args) throws Exception {
    // preconditions
    if (args.length < 2) {
      System.err.print("Too few parameters provided. Correct usage is:\n\tjava PingClient <host> <port>\n");
      System.exit(1);
    }

    // definitions
    String host = args[0];
    int port = Integer.parseInt(args[1]);
    int localport = port+1;
    String buffer;
    int n = 10;
    int i;
    boolean miss;
    String response_text;

    // open socket
    DatagramSocket socket = new DatagramSocket(localport);
    socket.setSoTimeout(800);

    // do this n times

    for (i=0; i<n; i++) {
      miss = false;
      // build packet
      LocalTime sent = LocalTime.now();
      buffer = "PING " + Integer.toString(i) + sent.toString() + "\r\n";
      // send ping
      DatagramPacket request = new DatagramPacket(buffer.getBytes("US-ASCII"), buffer.getBytes("US-ASCII").length, InetAddress.getByName(host), port);
      socket.send(request);
      // listen for response
      DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
      try {
        socket.receive(response);
      } catch (SocketTimeoutException e) {
        // miss!
        miss = true;
      }
      LocalTime received = LocalTime.now();
      long diff = sent.until(received, ChronoUnit.MILLIS);
      if (miss) {
        response_text = "Request timeout for udp_seq " + Integer.toString(i) + "\n";
      } else {
        response_text = Integer.toString(response.getLength())
          + " bytes from " + response.getAddress().getHostAddress().toString()
          + ": udp_seq=" + Integer.toString(i) + " time=" + diff + " ms\n";
      }
      System.out.print(response_text);
    }
    // close socket
    socket.close();
  }
}
