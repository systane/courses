package com.designPrinciples.I_InterfaceSegregationPrinciple;

public class I_InterfaceSegregationSolution {

    private interface NormalCellPhone {
        void ring();
        void call();
    }

    private interface AdvancedCellPhone {
        void takePicture();
    }

    private static class OldNokiaTijolao implements NormalCellPhone {
        @Override
        public void ring() {
            //Ring
        }

        @Override
        public void call() {
            //Call
        }
    }

    private static class Xiaomi implements NormalCellPhone, AdvancedCellPhone {
        @Override
        public void ring() {

        }

        @Override
        public void call() {

        }

        @Override
        public void takePicture() {

        }
    }
}
