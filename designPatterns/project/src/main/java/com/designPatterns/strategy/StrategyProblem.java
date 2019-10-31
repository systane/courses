package com.designPatterns.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StrategyProblem {

    private enum Occupation {
        ATTENDANT, SELLER, MANAGER
    }

    @Data
    private static class Employee{
        private String name;
        private double salary;
        private Occupation occupation;
    }

    @AllArgsConstructor
    private static class Sell{
        private final Employee employee;
        private final double value;

        //PROBLEM: This class has different logics to calculate the commission to many employees and all this logic is
        //organized in IF/ELSE. But would happen if we nedd to use the same logic in another class? or if we need to add
        //more occupations? This sequence of IF/ELSE will increase. To avoid this problem, we can use the strategy pattern.
        private double calculateCommission() {
            double commission = 0.0;

            Occupation occupation = employee.getOccupation();

            if(occupation.equals(Occupation.ATTENDANT)){
                commission = value * 0.1;
            }
            else if(occupation.equals(Occupation.SELLER)){
                commission = value * 0.15 + 5;
            }
            else if(occupation.equals(Occupation.MANAGER)){
                commission = value * 0.20 + 10;
            }

            return commission;
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
