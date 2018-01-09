package starter.websocket;

import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * WebSocket starter project.
 */
public class App extends Jooby {

  {
    // https for wss
    securePort(8043);

    use(new Jackson());

    assets("/index.js", "index.js");
    assets("/", "index.html");
    assets("/secure", "index.html");

    /** Start a websocket at /ws and send back JSON: */
    ws("/ws", ws -> {
      /** Send back message: */
      ws.onMessage(msg -> ws.send(new Message(msg.value())));

      /** Broadcast to all clients: */
      ws.broadcast(new Message("New client connected to: " + ws.pattern()));
    }).produces("json");
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
