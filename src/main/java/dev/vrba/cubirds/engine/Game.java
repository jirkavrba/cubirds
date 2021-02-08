package dev.vrba.cubirds.engine;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Data
public class Game {

    private final UUID id;

    private boolean started = false;

    private GameState state;

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

        this.state = this.createInitialState();

        return this;
    }

    private @NotNull GameState createInitialState() {
        CardsDecks decks = CardsDecks.createDefault();

        Player firstPlayer = players.values().iterator().next();

        List<Bird>[] columns = drawColumns(decks);

        return new GameState(
                null,
                firstPlayer,
                columns,
                decks
        );
    }

    private @NotNull List<Bird>[] drawColumns(@NotNull CardsDecks decks) {
       @SuppressWarnings("unchecked")
       final List<Bird>[] columns = (List<Bird>[]) new List[4];

       for (List<Bird> column : columns) {
           while (column.size() < 3) {
               // There needs to be 3 unique bird types in each column
               Bird next = decks.drawCard();

               if (column.contains(next)) {
                   decks.discardCard(next);
                   continue;
               }

               column.add(next);
           }
       }

       return columns;
    }
}
