package dev.vrba.cubirds.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameState {
    private Player winner;
    private Player currentPlayer;
    private List<Bird> drawingDeck;
    private List<Bird> discardDeck;
}
