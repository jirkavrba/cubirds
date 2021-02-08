package dev.vrba.cubirds.server;

import dev.vrba.cubirds.engine.Game;
import dev.vrba.cubirds.server.messages.GameMessage;
import org.jetbrains.annotations.NotNull;
import org.webbitserver.WebSocketConnection;

import java.util.HashSet;
import java.util.Set;

public class GameMessagesDispatcher {

    // TODO: replace with database or something that's not directly in-memory
    private final Set<Game> games = new HashSet<>();

    private final Set<WebSocketConnection> connections = new HashSet<>();

    public void open(@NotNull WebSocketConnection connection) {
        this.connections.add(connection);
    }

    public void close(@NotNull WebSocketConnection connection) throws Exception {
        connections.remove(connection);
    }

    public void dispatch(@NotNull GameMessage message) {
    }
}
