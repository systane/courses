package com.designpatterns.strategy;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public class StrategySolutionLambda {

    /*
     * We need to offer different types of discounts to a purchase, based on whether it's a Christimas, Easter ow
     * new Year.
     */

    //Strategy interface
    private interface Discounter {
        BigDecimal applyDiscount(BigDecimal amount);

        //SOLUTION: These static methods substitute all concrete classes
        static Discounter easterDiscount(){
            return amount -> amount.multiply(BigDecimal.valueOf(0.5));
        }

        static Discounter christimasDiscount(){
            return amount -> amount.multiply(BigDecimal.valueOf(0.9));
        }
    }

    //Context Class
    @AllArgsConstructor
    private static class Purchase {
        private Discounter discounter;
        private BigDecimal amount;

        private BigDecimal calculateDiscount(){
            return discounter.applyDiscount(amount);
        }
    }

    public static void main(String[] args) {
        //client choose which strategy implementation use
        Purchase purchase = new Purchase(Discounter.easterDiscount(), BigDecimal.valueOf(100));

        System.out.println("The amount of discount is: " + purchase.calculateDiscount());
    }
}
