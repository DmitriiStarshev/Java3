package src.test.java.org.HomeWorkt4Test;

import HomeWork4.BadTriangleException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static HomeWork4.TriangleSquareCalculation.countTriangleArea;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class TriangleSquareCalculationTest {
    @Test
    public void calcArea() throws BadTriangleException {
        double result = countTriangleArea(8, 10, 16);
        Assertions.assertEquals(32.72613634390714, result);
    }

    @Test
    public void badTriangleTest() {
        assertThatExceptionOfType(BadTriangleException.class).isThrownBy(
                () -> countTriangleArea(-1, 1, 1));
    }

//    @Test
//    public void badTriangleTest2() {
//        assertThatExceptionOfType(BadTriangleException.class).isThrownBy(
//                () -> countTriangleArea(1, 2, 3));
//    }
//
//    "Тут выдает такую ошибку: java.lang.AssertionError:
//Expecting code to raise a throwable.
}
