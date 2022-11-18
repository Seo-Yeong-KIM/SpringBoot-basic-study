package com.example.testprojcet.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// JUnit5가 가진 테스트 라이프 사이클
public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("## afterAll Annotation 호출 ##");
        System.out.println();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println("## afterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("## test1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2!!!") // 테스트 이름 설정 가능
    void test2() {
        System.out.println("## test2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled // 테스트 실행 X
    void test3() {
        System.out.println("## test3 시작 ##");
        System.out.println();
    }

}