package com.designpatterns.decorator;

import lombok.AllArgsConstructor;

public class DecoratorSolution {

    //Component interface - commom interface to allow all decorators call them father's method. The father's method will
    //call the implementation from the concrete component class
    private interface ChristmasTree {
        String decorate();
    }

    //ConcreteComponent
    private static class ChristmasTreeImpl implements ChristmasTree{

        @Override
        public String decorate() {
            return "Christmas tree";
        }
    }

    //Decorator - Abstract class that will forward the class of the method decorate() to its father.
    @AllArgsConstructor
    private abstract static class TreeDecorator implements ChristmasTree {
        private ChristmasTree tree;

        @Override
        public String decorate() {
            return tree.decorate();
        }
    }

    //ConcreteDecoratorA
    private static class BubbleLights extends TreeDecorator {
        private BubbleLights(ChristmasTree tree){
            super(tree);//This default constructor calls the constructor from TreeDecorator to initialize the ChristmasTree
        }

        public String decorate() { //It calls decorate() father's method (TreeDecorator.decorate()) and applies bubbleLights
            return super.decorate() + decorateWithBubbleLights();
        }

        private String decorateWithBubbleLights() {
            return " with Bubble Lights";
        }
    }

    //ConcreteDecoratorB
    private static class Garland extends TreeDecorator {
        private Garland(ChristmasTree tree){
            super(tree);//This default constructor calls the constructor from TreeDecorator
        }

        public String decorate() {//It calls decorate() father's method (TreeDecorator.decorate()) and applies garland
            return super.decorate() + decorateWithGarland();
        }

        private String decorateWithGarland() {
            return " with Garland";
        }
    }

    public static void main(String[] args) {
        ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
        System.out.println(tree1.decorate());

        ChristmasTree tree2 = new BubbleLights(new Garland(new ChristmasTreeImpl()));
        System.out.println(tree2.decorate());
    }
}
