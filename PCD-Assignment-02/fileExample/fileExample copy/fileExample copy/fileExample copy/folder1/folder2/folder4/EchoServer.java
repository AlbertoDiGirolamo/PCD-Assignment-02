/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unibo.ds.lab.sockets.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            listen(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Goodbye!");
    }

    public static void listen(int port) throws IOException {
        var server = new ServerSocket();

        // reserve the port
        server.bind(new InetSocketAddress(port));
        System.out.printf("Bound to port %d\n", port);

        // TODO start waiting for the standard input to be closed, then terminate the server
        ShutdownWaiter waiter = new ShutdownWaiter();
        waiter.start();

        while (!server.isClosed()) {
            // accept incoming connections
            Socket client = server.accept(); //It's blocking. Its will unlock when some request of connections arrives.
            System.out.printf("Accepted connection from: %s, on local port %d\n", client.getRemoteSocketAddress(), port);
            // serve them
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serve(client);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        }
    }

    private static final int BUFFER_SIZE = 1024;

    private static void serve(Socket client) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        while (true){
            int readBytes = client.getInputStream().read(buffer);

            if(readBytes < 0){
                client.shutdownOutput(); //We don't have something to send back to the client so we close the output
                System.out.printf("End of interaction with %s\n", client.getRemoteSocketAddress());
                break;
            }else{
                System.out.printf("Echoed %d bytes from %s\n", readBytes, client.getRemoteSocketAddress());
                client.getOutputStream().write(buffer, 0, readBytes);
                client.getOutputStream().flush();
            }
        }
    }
}