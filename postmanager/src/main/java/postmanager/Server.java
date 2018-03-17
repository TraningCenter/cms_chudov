package postmanager;


import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Server extends AbstractVerticle{

    private SockJSHandler handler = null;
    private AtomicInteger online = new AtomicInteger(0);

    @Override
    public void start() throws Exception {
        if (!deploy()){

        }
    }

    private boolean deploy(){
        int hostPost;
    }

    private int getFreePort(){
        int hostPort = 8080;

        if ()
    }
}
