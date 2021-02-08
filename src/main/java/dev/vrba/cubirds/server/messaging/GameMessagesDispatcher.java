package dev.vrba.cubirds.server.messaging;

import dev.vrba.cubirds.server.GameManager;
import org.jetbrains.annotations.NotNull;
import org.webbitserver.WebSocketConnection;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class GameMessagesDispatcher {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final GameManager gameManager;

    private final Set<WebSocketConnection> connections = new HashSet<>();

    public GameMessagesDispatcher(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void open(@NotNull WebSocketConnection connection) {
        this.connections.add(connection);
    }

    public void close(@NotNull WebSocketConnection connection) throws Exception {
        connections.remove(connection);
    }

    public void dispatch(@NotNull GameMessage message) {
        switch (message.getType()) {
            default:
                logger.info("Received game message with unknown type [" + message.getType() + "]");
                break;
        }
    }
}
