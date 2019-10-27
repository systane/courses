package com.designpatterns.strategy;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public class StrategyProblemLambda {

    /*
     * We need to offer different types of discounts to a purchase, based on whether it's a Christimas, Easter ow
     * new Year.
     */

    //Strategy interface
    private interface Discounter {
        BigDecimal applyDiscount (BigDecimal amount);
    }

    //ConcreteStrategy Class A
    private static class EasterDiscount implements Discounter{
        @Override
        public BigDecimal applyDiscount(BigDecimal amount) {
            return amount.multiply(BigDecimal.valueOf(0.5));
        }
    }

    //ConcreteStrategy Class B
    private static class ChristimasDiscount implements Discounter{
        @Override
        public BigDecimal applyDiscount(BigDecimal amount) {
            return amount.multiply(BigDecimal.valueOf(0.9));
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
        Discounter easterDiscount = new EasterDiscount();
        Purchase purchase = new Purchase(easterDiscount, BigDecimal.valueOf(100));

        System.out.println("The amount of discount is: " + purchase.calculateDiscount());

        //Main problem of this implementation is the verbosity. You need to create multiple concrete classes just to implement
        //one single method with a simple rule. The solution for this is use lambda expressions with a functional interface,
        //and for our luck, we already have this interface when we use the strategy pattern.
    }
}
