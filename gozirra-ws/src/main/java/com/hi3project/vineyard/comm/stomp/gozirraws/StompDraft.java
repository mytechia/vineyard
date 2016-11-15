package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;

/**
 * Created by luis on 14/11/16.
 */
public class StompDraft extends Draft_17 {



    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        //return super.postProcessHandshakeResponseAsServer(request, response);
        response.put("Sec-WebSocket-Protocol","v11.stomp");
        return super.postProcessHandshakeResponseAsServer(request, response);
    }
}
