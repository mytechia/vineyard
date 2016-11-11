package com.hi3project.vineyard.comm.stomp.gozirraws;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by luis on 11/11/16.
 */
public class TestMainClass {
    public static void main(String[] args){
        Server s ;
        try {
            WebSocketAdaptor adaptor = new WebSocketAdaptor(61613,61213,InetAddress.getLocalHost());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        Stomp stomp = null;
        try {
            s = new Server(61213);
            stomp = new IntraVMClient(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TEST");

        while (true){

            stomp.send("/commands",""+i);
            i=i++;
        }
    }
}
