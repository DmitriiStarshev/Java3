package src.test.java.org.LessonTest4.Lesson4Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsTest {
    @Test
    void testAssumption() {
        assumeTrue(1 == 2);
        assertEquals(1, 2);
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertTrue(true),
                () -> assertEquals(1, 2),
                () -> assertEquals(1, 3),
                () -> assertFalse(false)
        );
    }
}
