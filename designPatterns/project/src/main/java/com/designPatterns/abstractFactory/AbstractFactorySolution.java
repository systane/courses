package com.designPatterns.abstractFactory;

public class AbstractFactorySolution {

    private enum FactoryType {
        TWO_D_SHAPE_FACTORY,
        THREE_D_SHAPE_FACTORY
    }

    public enum ShapeType {
        LINE,
        CIRCLE,
        SPHERE
    }

    //AbstractProduct (interface) to group a family of objects. In this examples we just have 1 interface grouping the
    //other concrete classes, but we can have as much interface as we need.
    private interface GeometricShape {
        void draw();
    }

    //AbstractFactory - Through this interface, the client can change the family of factories at the run time.
    //OBS: Instead of abstract class, we can use an interface with generics
    private abstract static class AbstractFactory {
        abstract GeometricShape getShape(ShapeType name);

        private static AbstractFactory getFactory(FactoryType factoryType) {
            if (FactoryType.TWO_D_SHAPE_FACTORY == factoryType)
                return new TwoDShapeFactory();
            else if (FactoryType.THREE_D_SHAPE_FACTORY == factoryType)
                return new ThreeDShapeFactory();

            return null;
        }
    }

    //ConcreteFactory1 - It creates concrete objects of 2D family factory
    private static class TwoDShapeFactory extends AbstractFactory {
        @Override
        GeometricShape getShape(ShapeType name) {
            if (ShapeType.LINE == name)
                return new Line();
            else if (ShapeType.CIRCLE == name)
                return new Circle();

            return null;
        }
    }

    //ConcreteProductA1 (A1 means the first product - A - from the first - 1- interface)
    private static class Line implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Line Drawn.");
        }
    }

    //ConcreteProductB1 (B1 means the second product - B - from the first - 1- interface)
    private static class Circle implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Circle Drawn.");
        }
    }

    //ConcreteFactory2 - It creates concrete objects of 3D family factory
    private static class ThreeDShapeFactory extends AbstractFactory {
        @Override
        GeometricShape getShape(ShapeType name) {
            if (ShapeType.SPHERE == name)
                return new Sphere();

            return null;
        }
    }

    //ConcreteProductA2 (A2 means the first product - A - from the second - 2- interface)
    private static class Sphere implements GeometricShape {
        @Override
        public void draw() {
            System.out.println("Sphere Drawn.");
        }
    }

    //Client
    public static void main(String[] args) {
        //Create Factory for 2D Shapes
        AbstractFactory factory = AbstractFactory.getFactory(FactoryType.TWO_D_SHAPE_FACTORY);
        if(factory == null){
            System.out.println("Factory for given name doesn't exist");
            System.exit(1);
        }

        //Draw 2D objects
        GeometricShape shape = factory.getShape(ShapeType.LINE);
        if(shape != null)
            shape.draw();

        //Create Factory for 3D Shapes
        factory = AbstractFactory.getFactory(FactoryType.THREE_D_SHAPE_FACTORY);
        if(factory == null){
            System.out.println("Factory for given name doesn't exist");
            System.exit(1);
        }

        //Draw 3D objects
        shape = factory.getShape(ShapeType.SPHERE);
        if(shape != null)
            shape.draw();

        //SOLUTION: With a simple parameter we can easily choose between the two family of factories.
    }
}
