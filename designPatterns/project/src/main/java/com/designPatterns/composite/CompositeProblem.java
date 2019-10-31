package com.designPatterns.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class CompositeProblem {

    @AllArgsConstructor
    @Data //Leaf class
    private static class Painter {
        private double daysPerHouse;

        private double estimateDaysToPaint(int housesQnt){
            return housesQnt * daysPerHouse;
        }
    }

    @AllArgsConstructor
    @Data //Client class
    private static class LandOwner {
        private List<Painter> painters;
        private int housesCount;

        private void ManageHouses(){
            double daysToPaint = getTotalVelocity() * housesCount;

            System.out.printf("\nPainting houses for %.0f day(s) \n\n", daysToPaint);
        }

        private double getTotalVelocity() {
            double sum = 0;
            for(Painter painter : painters){sum += getVelocity(painter);}
            return sum;
        }

        private double getVelocity(Painter painter) {
            return painter.estimateDaysToPaint(1);
        }
    }

    public static void main(String[] args) {
        Painter painter1 = new Painter(10.0);
        Painter painter2 = new Painter(10.0);

        LandOwner landOwner = new LandOwner(Arrays.asList(painter1, painter2), 3);
        landOwner.ManageHouses();

    /*
      Problem: What would happen if the client contract a company of painters? How would our client (LandOwner) calculate
      the quantity of days necessary to paint all the houses? Now our client has the necessity of receiving a single
      painter and a company as parameter in the constructor method. By the way, this company can have many painters
      working for them or even they can have another company with more painters (outsourcing of painters).
     */
    }
}
