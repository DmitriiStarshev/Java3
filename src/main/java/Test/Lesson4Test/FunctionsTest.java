package Test.Lesson4Test;

import Lesson4.Functions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.stream.Stream;


public class FunctionsTest {
    Functions functions = new Functions();
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    @BeforeAll
    static void beforeAll() {
        //System.out.println("Общее предусловие");
        logger.info("Общее предусловие");
        logger.debug("debug level");
        logger.error("error");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Условие перед каждым тестом");
    }

    @Test
    void testGivenEvenNumberWhenCheckIsEvenThenTrue() {
        boolean result = functions.isNumberEven(2);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка метода isPrime с непростым числом")
    @RepeatedTest(5)
    void testNotPrimeNumber() {
        Assertions.assertFalse(functions.isPrime(4));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123321", "567765"})
    void testIsPalindromeMethod(String word) {
        Assertions.assertTrue(functions.isPalindrome(word));
    }

    @ParameterizedTest
    @CsvSource({"123321, true", "1233212, false"})
    void testIsPalindomeMethod2(String word, boolean result) {
        Assertions.assertEquals(functions.isPalindrome(word), result);
    }

    @ParameterizedTest
    @MethodSource("testParametrizedByObject")
    void testParametrizedByObject(Cat cat, Boolean result) {
        System.out.println(cat);
    }

    private static Stream<Arguments> testParametrizedByObject() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of(new Cat("name1", 12), true),
                Arguments.of(new Cat("name2", 22), false)
        );
    }

    @AfterEach
    void tearDown() {
        System.out.println("Тест завершился");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("После всех тестов");
    }
}