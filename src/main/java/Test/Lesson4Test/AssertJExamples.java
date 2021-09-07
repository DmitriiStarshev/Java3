package Test.Lesson4Test;

import Lesson4.Functions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

public class AssertJExamples {
    Functions functions = new Functions();

    @Test
    void testIsEvenWithAssertJ() {
        assertThat(Functions.isNumberEven(5)).isFalse();
    }

    @Test
    void testCollection() {
        List<String> list = new ArrayList<>(Arrays.asList("test1", "tttt", "test2"));
        assertThat(list).containsAnyOf("test1", "yyyy");
        assertThat(1).isGreaterThan(0).isLessThan(2);
    }
}