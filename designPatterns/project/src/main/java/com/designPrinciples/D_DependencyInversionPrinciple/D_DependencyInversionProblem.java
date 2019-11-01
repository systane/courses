package com.designPrinciples.D_DependencyInversionPrinciple;

public class D_DependencyInversionProblem {

    private static class Windows98Machine {
        private final StandardKeyboard keyboard;

        public Windows98Machine() {
            this.keyboard = new StandardKeyboard();
        }
    }

    private static class StandardKeyboard {}
}
