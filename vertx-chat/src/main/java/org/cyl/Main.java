package org.cyl;

import io.vertx.core.Vertx;
import org.cyl.verticles.ApiVerticle;

public class Main{

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ApiVerticle());
    }

}

