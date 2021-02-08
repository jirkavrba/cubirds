package dev.vrba.cubirds.engine;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@EqualsAndHashCode
public class CardsDecks {

    private final List<Bird> drawingDeck;

    private final List<Bird> discardingDeck;

    public CardsDecks(@NotNull List<Bird> items) {
        this.drawingDeck = items;
        this.discardingDeck = new ArrayList<>();
    }

    public @NotNull CardsDecks shuffle() {
        Collections.shuffle(this.drawingDeck);
        return this;
    }

    public int drawingDeckCards() {
        return this.drawingDeck.size();
    }

    public int discardingDeckCards() {
        return this.discardingDeck.size();
    }

    public @NotNull Bird drawCard() {
        if (this.drawingDeck.isEmpty()) {
            System.out.println("Shuffling discarding deck into drawing deck");

            this.drawingDeck.addAll(this.discardingDeck);
            this.discardingDeck.clear();

            Collections.shuffle(this.drawingDeck);
        }

        return this.drawingDeck.remove(0);
    }

    public void discardCard(@NotNull Bird card) {
        this.discardingDeck.add(card);
    }

    public static @NotNull CardsDecks createDefault() {
        List<Bird> deck = new ArrayList<>();

        for (Bird bird : Bird.values()) {
            deck.addAll(Collections.nCopies(bird.count, bird));
        }

        return new CardsDecks(deck).shuffle();
    }
}
