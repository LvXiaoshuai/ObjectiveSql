package com.github.braisdom.objsql.example.oracle;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionalExample {

    public static void createNormally() {
        Domains.Order order = new Domains.Order()
                .setNo("202000001")
                .setMemberId(3L)
                .setAmount(3.5f)
                .setQuantity(100.3f)
                .setSalesAt(Timestamp.valueOf("2020-05-01 09:30:00"));

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for(int i = 0; i< 1; i++) {
            executorService.execute(() -> {
                try {
                    Domains.Order.makeOrder(order, null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws SQLException {
        createNormally();
    }
}
