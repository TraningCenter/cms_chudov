import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import postmanager.Server;

import java.net.ServerSocket;

@RunWith(VertxUnitRunner.class)
public class ServerTest {

    private Vertx vertx;
    private int port;

    @Before
    public void setUp(TestContext context) throws Exception{
        vertx = Vertx.vertx();

        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();

        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(Server.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) throws Exception{
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testStart(TestContext context) throws Exception{
        Async async = context.async();

        vertx
                .createHttpClient()
                .getNow(port, "localhost", "/", response -> response.handler(body -> {
                    context.assertTrue(body.toString().contains("Ok"));
                    async.complete();
                }));
    }
}
