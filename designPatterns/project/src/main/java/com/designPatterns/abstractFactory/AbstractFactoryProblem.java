package com.designPatterns.abstractFactory;

public class AbstractFactoryProblem {
    private interface GeometricShape {
        void draw();
    }

    private static class Line implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Line Drawn.");
        }
    }

    private static class Circle implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Circle Drawn.");
        }
    }

    private static class Sphere implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Sphere Drawn.");
        }
    }

    public static void main(String[] args) {
        //Create Factory for 2D Shapes
        GeometricShape line = new Line();
        line.draw();

        //Create Factory for 3D Shapes
        GeometricShape sphere = new Sphere();
        sphere.draw();

        //PROBLEM: If must change all the 2D shapes in our system to 3D? We would start to looking at all classes
        //and changing all 2D shapes to 3D? If we need a dynamic behavior that can make our system change between 3D or 2D
        //shape, we can use an Abstract Factory (A Factory of factories) to overcomes this problem.
    }
}
