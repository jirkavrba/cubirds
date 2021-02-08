package dev.vrba.cubirds.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.vrba.cubirds.server.messaging.GameMessage;
import dev.vrba.cubirds.server.messaging.GameMessagesDispatcher;
import org.jetbrains.annotations.NotNull;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.util.logging.Logger;

public class WebsocketHandler extends BaseWebSocketHandler {

    private final GameMessagesDispatcher dispatcher;

    public WebsocketHandler(@NotNull GameMessagesDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void onOpen(WebSocketConnection connection) throws Exception {
        this.dispatcher.open(connection);
    }

    @Override
    public void onClose(WebSocketConnection connection) throws Exception {
        this.dispatcher.close(connection);
    }

    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        System.out.println("Received WS message: " + message);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.readValue(message, ObjectNode.class);

            if (!node.has("type") || node.get("type").getNodeType() != JsonNodeType.STRING) {
                throw new IllegalArgumentException("Message must contain its type.");
            }

            if (!node.has("payload")) {
                throw new IllegalArgumentException("Message must contain a payload.");
            }

            GameMessage gameMessage = new GameMessage(
                    node.get("type").asText(),
                    node.get("payload"),
                    connection
            );

            this.dispatcher.dispatch(gameMessage);
        }
        catch (Throwable exception) {
            Logger logger = Logger.getLogger(this.getClass().getName());

            logger.warning("Received an invalid WS message: " + message + " that resulted into exception:");
            logger.warning(exception.getMessage());
        }
    }
}
