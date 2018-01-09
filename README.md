[![Build Status](https://travis-ci.org/jooby-project/websocket-starter.svg?branch=master)](https://travis-ci.org/jooby-project/websocket-starter)
# websocket starter

WebSocket starter project.

## quick preview

This project contains a simple application that:

* Accept a websocket connection at `/ws`
* Send back any message sent by a client
* Broadcast message to all connected clients at `/ws`

[App.java](https://github.com/jooby-project/websocket-starter/blob/master/src/main/java/starter/websocket/App.java):

```java
public class App extends Jooby {

  {
    // https for wss
    securePort(8043);
    use(new Jackson());

    assets("/", "index.html");

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
```

## run

    mvn jooby:run

- WebSocket: [http://localhost:8080](http://localhost:8080)
- WebSocket over SSL: [https://localhost:8043/secure](https://localhost:8043/secure)

## help

* Read the [websocket documentation](http://jooby.org/doc/#web-sockets)
* Join the [channel](https://gitter.im/jooby-project/jooby)
* Join the [group](https://groups.google.com/forum/#!forum/jooby-project)
