package dev.vrba.cubirds.server.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.webbitserver.WebSocketConnection;

@Data
public class GameMessage {
    private final String type;
    private final JsonNode payload;
    private final WebSocketConnection connection;
}
