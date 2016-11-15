package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by luis on 11/11/16.
 */
public class TestMainClass {
    public static void main(String[] args){
        /*Server s ;
        Draft d = new StompDraft();
        List<Draft> dl = new ArrayList<Draft>();
        Draft d17 = new Draft_17();
        dl.add(d);
        dl.add(d17);
        Stomp stomp = null;
        try {
            s = new Server(61213);
            stomp = new IntraVMClient(s);
            s.listen(61213);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WebSocketAdaptor adaptor = new WebSocketAdaptor(61623,61213,InetAddress.getLocalHost(),dl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        WebsocketStompServer server = new WebsocketStompServer(61623,61213);
       Long i = (long) 0;

        System.out.println("TEST");

        while (true){
            /*i=i+1;
            if (i.longValue() == (long)999999999){
                stomp.send("commands","ping");
                System.out.println("ping");
                i=(long) 0;
            }*/


        }
    }
}
