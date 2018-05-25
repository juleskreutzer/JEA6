package websocket;

import domain.Account;
import domain.Kwet;
import service.AccountService;
import service.KwetService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@ServerEndpoint(
        value = "/socket"
)
public class Websocket {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    private Logger logger = Logger.getLogger(getClass().getName());

    @OnMessage
    public void onMessage(final Session client, final String message) {
        if (message != null) {
            sendAll(message);
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        logger.warning("Opening connection");
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        logger.warning("Closing connection");
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    private void sendAll(Object answer) {
        logger.warning("Sending message to all peers");
        peers.stream().forEach((peer) -> {
            sendMessage(peer, answer);
        });
    }

    private void sendMessage(Session peer, Object send) {
        if (peer.isOpen()) {
            try {
                peer.getBasicRemote().sendObject(send);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
