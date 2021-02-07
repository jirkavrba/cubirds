package dev.vrba.cubirds.engine;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Data
public class Player {
    private final UUID id;

    private final String username;

    private final Map<Bird, Integer> hand;

    private final List<Flock> flocks;

    public Player(@NotNull String username) {
        this(UUID.randomUUID(), username);
    }

    public Player(@NotNull UUID id, @NotNull String username) {
        this.id = id;
        this.username = username;
        this.hand = new HashMap<>();
        this.flocks = new ArrayList<>();
    }
}
