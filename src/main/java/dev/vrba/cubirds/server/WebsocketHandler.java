package dev.vrba.cubirds.server;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

public class WebsocketHandler extends BaseWebSocketHandler {

    private final GameMessagesDispatcher dispatcher = new GameMessagesDispatcher();

    @Override
    public void onOpen(WebSocketConnection connection) throws Exception {
        this.dispatcher.open(connection);
    }

    @Override
    public void onClose(WebSocketConnection connection) throws Exception {
        this.dispatcher.close(connection);
    }

    @Override
    public void onMessage(WebSocketConnection connection, String message) throws Throwable {
        System.out.println("Received WS message: " + message);
    }
}
