package org.cyl.handles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class LoginHandle extends AbstractVerticle {


    public Handler<RoutingContext> handLogin() {

        return new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                HttpServerRequest request = routingContext.request();
                HttpServerResponse response = routingContext.response();
                response.putHeader("Content-Type", "text/plain; charset=utf-8");
                request.setExpectMultipart(true);
                request.bodyHandler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {
                        MultiMap formAttributes = request.formAttributes();
                        String name = formAttributes.get("name");
                        String age = formAttributes.get("age");
                        System.out.println("name=" + name + "&age=" + age);
                        //做数据库操作

                        //redis操作

                    }
                });
                routingContext.response().end("POST请求已处理");
            }
        };
    }

    public Handler<RoutingContext> handLogout() {
        return new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                routingContext.response().end("logout!!!");
            }
        };
    }
}
