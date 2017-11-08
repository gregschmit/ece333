# Project 2

## Meta

Gregory N. Schmit

Philip Horwitz

ECE 333 Computer Networks I

## Description

We decided to do the UDP Pinger. It was fairly simple. We designed the Client to use the next port in line as it's communication port (so if the server is on 5000, then the client is on 5001). The output for the client is based on the same format that the FreeBSD Unix `ping` utility uses.

## Usage

```
$ javac *.java
$ java PingServer <port>
$ java PingClient <host> <port>
```

For testing on a local machine, use port=5000 and host=localhost.
