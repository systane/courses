package br.com.jpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

public class n1Problem {

    @Entity
    @Table(name = "purchaseOrder")
    @Getter
    private static class Order implements Serializable {

        private Long orderNumber;

        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        private Set<OrderItem> items = new HashSet<OrderItem>();
    }

    @Entity
    @Table(name = "item")
    @Getter
    private static class OrderItem implements Serializable {
        //attributes
    }


    private void teste() {
        List<Order> orders = loadOrders();

        for(Order order : orders){
            System.out.println("Order: " + order.getOrderNumber());
            System.out.println("Number of Items: " + order.getItems().size());
        }
    }


    private List<Order> loadOrders() {
        return new ArrayList<Order>();
    }


}
