package com.designpatterns.decorator;

public class DecoratorProblem {

    private interface Tree {
        String decorate();
    }

    private static class TreeWithoutDecorators implements Tree {
        @Override
        public String decorate() {
            return "Christmas tree";
        }
    }

    private static class TreeWithBubbleLights implements Tree {
        @Override
        public String decorate() {
            return "Christmas tree with BubbleLights";
        }
    }

    private static class TreeWithGarland implements Tree {
        @Override
        public String decorate() {
            return "Christmas tree with Garland";
        }
    }

    private static class TreeWithBubbleLightsAndGarland implements Tree {
        @Override
        public String decorate() {
            return "Christmas tree with BubbleLights with Garland";
        }
    }

    public static void main(String[] args) {
        Tree normalTree = new TreeWithoutDecorators();
        Tree treeWithBubbleLights = new TreeWithBubbleLights();
        Tree treeWithGarland = new TreeWithGarland();
        Tree treeWithBubbleLightsAndGarland = new TreeWithBubbleLightsAndGarland();

        System.out.println(normalTree.decorate());
        System.out.println(treeWithBubbleLights.decorate());
        System.out.println(treeWithGarland.decorate());
        System.out.println(treeWithBubbleLightsAndGarland.decorate());

        //PROBLEM: We this inheritance from the interface we must to implement 4 different classes for just 2 decorators.
        //This case is a good option apply a decorator to avoid this explosion of subclasses by inheritance.
    }
}
