package com.example.testprojcet.common;

public class Constants {

    public enum ExceptionClass {

        // 사용자 정의 상수
        PRODUCT("Product"), ORDER("Order"), PROVIDER("Provider");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }

    }

}
