package postmanager;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle{

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        /*Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/vertx").handler()*/
        vertx
                .createHttpServer()
                .requestHandler(r -> r.response().end("Ok"))
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                    if (result.succeeded()){
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
