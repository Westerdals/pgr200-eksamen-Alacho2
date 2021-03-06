package no.kristiania.pgr200.server;

import no.kristiania.pgr200.server.requesthandlers.HttpServerRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HttpServerListener {
    private boolean running = true;
    private List<HttpServerRequestHandler> requestHandlers;
    private HttpServerParserRequest requestParser;
    private HttpServerWriter responseWriter;
    private int actualPort;

    public HttpServerListener(List<HttpServerRequestHandler> requestHandlers, HttpServerParserRequest requestParser, HttpServerWriter responseWriter){
        this.requestHandlers = requestHandlers;
        this.requestParser  = requestParser;
        this.responseWriter = responseWriter;
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        this.actualPort = serverSocket.getLocalPort();
        new Thread(() ->  serverThread(serverSocket)).start();
    }

    public void serverThread(ServerSocket serverSocket) {

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                Thread t = new HttpServerConnectionHandler(clientSocket, requestHandlers, requestParser, responseWriter);
                t.start();
            } catch (IOException e) {
                System.out.println("ZOMG SERVER WENT SPLODE");
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        System.out.println("Server shutting down.");
        running = false;
    }

    public int getPort(){
        return actualPort;
    }
}
