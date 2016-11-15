package com.hi3project.vineyard.comm.stomp.gozirraws;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 15/11/16.
 */
public class WebsocketStompServer {

    public WebsocketStompServer(int wsport,int  gozirraport) {
        Server s;
        Draft d = new StompDraft();
        List<Draft> dl = new ArrayList<Draft>();
        Draft d17 = new Draft_17();
        dl.add(d);
        dl.add(d17);
        Stomp stomp = null;
        try {
            s = new Server(gozirraport);

            stomp = new IntraVMClient(s);
            s.listen(gozirraport);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WebSocketAdaptor adaptor = new WebSocketAdaptor(wsport, gozirraport, InetAddress.getLocalHost(), dl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
