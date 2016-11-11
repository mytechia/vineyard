package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.WebSocket;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by luis on 11/11/16.
 */
public class WsAdaptorConnection extends Thread {
    private WebSocket webSocket;
    private Socket socket;
    private boolean die = false;
    private byte[] bytes;
    private int index = 0;
    String message = "";
    public WsAdaptorConnection(WebSocket ws, Socket s){
        webSocket = ws;
        socket = s;
    }

    @Override
    public void run() {
        while (!die){

            try {
                if(socket.getInputStream().available()>0){
                    byte  b= (byte) socket.getInputStream().read();
                    message = message + (char) b;
                    if (b==0x0){
                        sendMessageToWsClient(message);
                        message = "";


                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }

    public void sendMessageToStompServer(String s) throws IOException {
        socket.getOutputStream().write(s.getBytes());
    }

    private void sendMessageToWsClient(String s){
        webSocket.send(s);


    }
}
