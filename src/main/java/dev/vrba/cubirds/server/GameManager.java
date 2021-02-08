package dev.vrba.cubirds.server;

import dev.vrba.cubirds.engine.Game;
import dev.vrba.cubirds.server.messaging.GameMessagesDispatcher;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class GameManager {
    // TODO: replace with database or something that's not directly in-memory
    private final Set<Game> games = new HashSet<>();

    @Getter
    private final GameMessagesDispatcher dispatcher = new GameMessagesDispatcher(this);

    public @NotNull Game createGame() {
        return new Game();
    }
}
