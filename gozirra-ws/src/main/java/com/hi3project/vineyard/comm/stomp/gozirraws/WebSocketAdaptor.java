package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created by Luis Llamas on 11/11/16.
 */
public class WebSocketAdaptor extends WebSocketServer{
    //Hacia fuera

    //Hacia dentro

    private HashMap<InetSocketAddress,WsAdaptorConnection> connections = new HashMap<>();

    private int insideport;
    private InetAddress insideinetaddress;
    private ByteBuffer b;





    public WebSocketAdaptor(int port, int internalport, InetAddress internalAddress, List<Draft> drafts) throws IOException {
        //super( new InetSocketAddress( port ) );
        super( new InetSocketAddress(port),drafts);


        InetSocketAddress address = new InetSocketAddress(port);


        this.insideport = internalport;
        this.insideinetaddress = internalAddress;
        this.start();
        this.getDraft();
        System.out.println("Creating adaptor");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        //Añadir el nuevo cliente a la lista



        try {
            Socket clientSocket = new Socket(insideinetaddress,insideport);
            connections.put(webSocket.getRemoteSocketAddress(),new WsAdaptorConnection(webSocket,clientSocket));
            connections.get(webSocket.getRemoteSocketAddress()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        //eliminar al cliente de la lista
        System.out.println("On Close: "+s);
        connections.remove(webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        //Redirigir el mensaje recibido al servidor interno para que haga sus cosas
        System.out.println(s);

        try {
            connections.get(webSocket.getRemoteSocketAddress()).sendMessageToStompServer(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.out.println("On Error: "+e.toString());
    }

}
