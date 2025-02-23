package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("test-car-id-123");
        car.setCarName("Test Car");
        car.setCarColor("Red");
        car.setCarQuantity(2);
    }

    @Nested
    class PositiveTests {
        @Test
        void testGetCarId() {
            assertEquals("test-car-id-123", car.getCarId());
        }

        @Test
        void testGetCarName() {
            assertEquals("Test Car", car.getCarName());
        }

        @Test
        void testGetCarColor() {
            assertEquals("Red", car.getCarColor());
        }

        @Test
        void testGetCarQuantity() {
            assertEquals(2, car.getCarQuantity());
        }
    }

    @Nested
    class NegativeTests {
        @Test
        void testGetCarIdNegative() {
            assertNotEquals("wrong-car-id", car.getCarId());
        }

        @Test
        void testGetCarNameNegative() {
            assertNotEquals("Wrong Car", car.getCarName());
        }

        @Test
        void testGetCarColorNegative() {
            assertNotEquals("Blue", car.getCarColor());
        }

        @Test
        void testGetCarQuantityNegative() {
            assertNotEquals(3, car.getCarQuantity());
        }
    }
}