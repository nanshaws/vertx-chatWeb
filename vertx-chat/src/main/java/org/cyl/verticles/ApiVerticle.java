package org.cyl.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.cyl.handles.LoginHandle;

public class ApiVerticle extends AbstractVerticle {
    @Override
    public void start() {
        // 创建一个 HTTP 服务器
        HttpServer server = vertx.createHttpServer();
        // 创建一个 Router
        Router router = Router.router(vertx);
        // 在根路径上处理请求
        router.route("/").handler(routingContext -> {
            routingContext.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        });
        router.post("/login").handler(new LoginHandle().handLogin());
        router.get("/logout").handler(new LoginHandle().handLogout());


        // 将请求路由到相应的处理程序
        server.requestHandler(router).listen(8080);
    }
}
