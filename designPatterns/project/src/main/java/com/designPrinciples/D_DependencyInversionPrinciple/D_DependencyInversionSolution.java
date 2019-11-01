package com.designPrinciples.D_DependencyInversionPrinciple;

public class D_DependencyInversionSolution {

    private static class Windows98Machine {
        private final Keyboard keyboard;

        public Windows98Machine(Keyboard keyboard) {
            this.keyboard = keyboard;
        }
    }

    private static class StandardKeyboard implements Keyboard {}

    private static class GamerKeyboard implements Keyboard {}

    private interface Keyboard {}
}
