package com.fomkina.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 15:57
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppTest {
}
