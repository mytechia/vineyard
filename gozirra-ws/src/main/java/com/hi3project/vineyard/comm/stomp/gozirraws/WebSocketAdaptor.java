package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ClientHandshake;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luis Llamas on 11/11/16.
 */
public class WebSocketAdaptor extends WebSocketServer{
    //Hacia fuera
 
    //Hacia dentro

    private HashMap<InetSocketAddress,WsAdaptorConnection> connections;

    private int insideport;
    private InetAddress insideinetaddress;
    private ByteBuffer b;

    public WebSocketAdaptor(int port, int internalport, InetAddress internalAddress) throws IOException {
        super( new InetSocketAddress( port ) );

        this.insideport = internalport;
        this.insideinetaddress = internalAddress;
        this.start();
        System.out.println("Creating adaptor");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        //Añadir el nuevo cliente a la lista
        System.out.println("On Open");
        try {
            Socket clientSocket = new Socket(insideinetaddress,insideport);
            connections.put(webSocket.getRemoteSocketAddress(),new WsAdaptorConnection(webSocket,clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        //eliminar al cliente de la lista
        connections.remove(webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        //Redirigir el mensaje recibido al servidor interno para que haga sus cosas
        try {
            connections.get(webSocket.getRemoteSocketAddress()).sendMessageToStompServer(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

}
