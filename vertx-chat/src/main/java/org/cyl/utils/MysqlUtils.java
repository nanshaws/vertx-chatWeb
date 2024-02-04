package org.cyl.utils;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class MysqlUtils{
  static   Vertx vertx = Vertx.vertx();
  static   MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setHost("localhost")
            .setPort(3306)
            .setDatabase("database")
            .setUser("root")
            .setPassword("password");
  static   PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(5);

    static MySQLPool client = MySQLPool.pool(vertx,connectOptions,poolOptions);
    public static MySQLPool getClient(){
        return client;
    }

    public static void Close(){
        client.close();
    }

    public static Future<Void> executeQuery(String query) {
        Promise<Void> promise = Promise.promise();
        MySQLPool client = getClient();
        client.query(query).execute(ar -> {
            if (ar.succeeded()) {
                RowSet<Row> result = ar.result();
                System.out.println("GOT "+result.size()+" rows");
                promise.complete();
            } else {
                promise.fail(ar.cause());
                System.out.println("Failure: "+ ar.cause().getMessage());
            }
        });
        return promise.future();
    }

}



