package com.designPrinciples.I_InterfaceSegregationPrinciple;

public class I_InterfaceSegregationProblem {

    private interface CellPhone {
        void ring();
        void call();
        void takePicture();
    }

    private static class OldNokiaTijolao implements CellPhone {
        @Override
        public void ring() {
            //Ring
        }

        @Override
        public void call() {
            //Call
        }

        @Override
        public void takePicture() {
            throw new UnsupportedOperationException("Operation not supported :(");
        }
    }

    private static class Xiaomi implements CellPhone {
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
