package br.com.jpa;


import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class n1Problem {

    @Entity
    @Table(name = "purchaseOrder")
    @Getter
    private static class Order implements Serializable {

        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        private Set<OrderItem> items = new HashSet<OrderItem>();
    }

    @Entity
    @Table(name = "item")
    @Getter
    private static class OrderItem implements Serializable {
        //attributes
    }


}
