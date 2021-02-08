package dev.vrba.cubirds;

import dev.vrba.cubirds.server.WebsocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.util.concurrent.ExecutionException;

public class Cubirds {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebServer server = WebServers.createWebServer(8080)
                .add(new StaticFileHandler("/web"))
                .add("/websocket", new WebsocketHandler())
                .start()
                .get();

        System.out.println("Cubirds server running on " + server.getUri());
    }
}
