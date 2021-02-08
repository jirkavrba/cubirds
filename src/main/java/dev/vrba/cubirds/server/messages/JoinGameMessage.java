package dev.vrba.cubirds.server.messages;

import org.jetbrains.annotations.NotNull;

public class JoinGameMessage extends GameMessage {
    @Override
    public @NotNull String getName() {
        return "join";
    }
}
