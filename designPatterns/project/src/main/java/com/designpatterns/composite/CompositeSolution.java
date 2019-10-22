package com.designpatterns.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class CompositeSolution {
    //Component interface
    public interface IPainter{//This is the solution. An interface can hide from client the difference between a Painter and a Collection of Painters
        double estimateDaysToPaint(int housesQnt); //Common method. Everyone (Leaf and Composite objects) that assigns this contract must implement this method
    }

    @AllArgsConstructor
    @Data //Leaf class - Atomic class in the tree relationship
    public static class Painter implements IPainter{
        private double daysPerHouse;

        public double estimateDaysToPaint(int housesQnt){
            return daysPerHouse * housesQnt;
        }
    }

    @AllArgsConstructor
    @Data //Composite class - Complex class that can hold a list of leaves.
    public static class PaintingCompany implements IPainter {
        private List<IPainter> painters;

        public double estimateDaysToPaint(int housesQnt) {
            return getTotalVelocity() * housesQnt;//TODO: Fix this calculation using Largest Divisor of N
        }

        private double getTotalVelocity() {
            double sum = 0;
            for(IPainter painter : painters){sum += getVelocity(painter);}
            return sum;
        }

        private double getVelocity(IPainter painter) {
            return painter.estimateDaysToPaint(1);
        }
    }

    @AllArgsConstructor
    @Data// Client Class
    private static class LandOwner {
        private IPainter painter; //Client class only interact with the interface (Component Class)
        private int housesCount;

        private void ManageHouses(){
            double daysToPaint = painter.estimateDaysToPaint(housesCount);

            System.out.printf("\nPainting houses for %.0f day(s) \n\n", daysToPaint);
        }
    }

    public static void main(String[] args) {
        IPainter painter1 = new Painter(20.0);
        IPainter painter2 = new Painter(10.0);
        IPainter painter3 = new Painter(5.0);

        PaintingCompany painterCompany1 = new PaintingCompany(Arrays.asList(painter1));
        PaintingCompany painterCompany2 = new PaintingCompany(Arrays.asList(painter2, painter3));

        LandOwner landOwner1 = new LandOwner(painterCompany1, 3);
        landOwner1.ManageHouses();

        LandOwner landOwner2 = new LandOwner(painterCompany2, 3);
        landOwner2.ManageHouses();

        LandOwner landOwner3 = new LandOwner(painter1, 3);
        landOwner3.ManageHouses();
    }
}
