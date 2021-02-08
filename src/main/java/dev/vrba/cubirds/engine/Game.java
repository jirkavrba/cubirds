package dev.vrba.cubirds.engine;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Game {

    private final UUID id;

    private boolean started = false;

    private final Map<UUID, Player> players = new HashMap<>();

    public Game() {
        this.id = UUID.randomUUID();
    }

    public @NotNull Game addPlayer(@NotNull Player player) {
        if (this.started) {
            throw new IllegalStateException("Game already started");
        }

        if (this.players.size() >= 5) {
            throw new IllegalStateException("There is a limit of 5 players per game");
        }

        this.players.put(player.getId(), player);

        return this;
    }

    public @NotNull Game removePlayer(@NotNull UUID id) {
        if (this.started) {
            throw new IllegalStateException("Game already started");
        }

        if (!this.players.containsKey(id)) {
            throw new IllegalArgumentException("Player with id of " + id + " not found");
        }

        this.players.remove(id);

        return this;
    }

    public @NotNull Game start() {
        if (this.started) {
            throw new IllegalStateException("Game already started");
        }

        if (this.players.size() < 2) {
            throw new IllegalStateException("There needs to be at least 2 players in the game");
        }

        return this;
    }
}
