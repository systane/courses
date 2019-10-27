package com.designpatterns.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StrategySolution {

    //Strategy Interface
    private interface Commission {//SOLUTION: Create an interface with a commom method is the key of the strategy pattern
        double calculateCommission(double value);
    }

    //This enum assigns the Strategy interface, so then each enum value must implements its own logic for the commission
    //Each enum plays the role of a ConcreteStrategy class
    private enum Occupation implements Commission{
        ATTENDANT {
            @Override
            public double calculateCommission(double value) {
                return value * 0.1;
            }
        },
        SELLER{
            @Override
            public double calculateCommission(double value) {
                return value * 0.15 + 5;
            }
        },
        MANAGER {
            @Override
            public double calculateCommission(double value) {
                return value * 0.20 + 10;
            }
        },
        DIRECTOR {
            @Override //NEW OCCUPATION ADDED WITH A NEW LOGIC TO CALCULATE THE COMMISSION
            public double calculateCommission(double value) {
                return value * 0.25 + 20;
            }
        }
    }

    @Data
    private static class Employee{
        private String name;
        private double salary;
        private Occupation occupation;
    }

    //Context Class - This class calls the Strategy interface method an return its value
    @AllArgsConstructor
    private static class Sell{
        private final Employee employee;
        private final double value;

        private double calculateCommission() {
            Occupation occupation = employee.getOccupation();

            return occupation.calculateCommission(value);//You can change this value at runtime, just by changing the employee occupation
        }
    }

    public static void main(String[] args) {
        Employee attendant = new Employee();
        attendant.setName("Joãozinho");
        attendant.setSalary(1200);
        attendant.setOccupation(Occupation.ATTENDANT);

        Sell newSell = new Sell(attendant, 200);
        System.out.println("value commission: " + newSell.calculateCommission());
    }
}
