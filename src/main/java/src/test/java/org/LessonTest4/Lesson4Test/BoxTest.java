package src.test.java.org.LessonTest4.Lesson4Test;

import Lesson4.Box;
import Lesson4.BoxIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class BoxTest {
    static Box box;

    @Nested
    class WhenEmpty {
        @BeforeEach
        void createBox() {
            box = new Box();
        }

        @Test
        void checkExceptionWhenTryToShuffleEmptyBox() {
            assertThatNullPointerException().isThrownBy(() -> box.suffleBalls());
        }

        @Test
        void testOurException() {
            assertThatExceptionOfType(BoxIsEmptyException.class).isThrownBy(() -> box.deleteBall());
        }

        @Nested
        class WhenWithBalls {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void ballAdditionTest() {
                int ballsCount = box.getBallsCounter();
                box.addBall();
                Assertions.assertEquals(box.getBallsCounter(), ballsCount + 1);
            }
        }
    }
}

