package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    private CarRepositoryImpl repository;

    @Test
    void testCreateAndFind() {
        String predefinedId = "predefined-id";
        Car car = new Car(predefinedId, "Test Car", "Red", 1);
        Car createdCar = repository.create(car);
        assertEquals(predefinedId, createdCar.getCarId(), 
                "If carId is provided, it should remain unchanged");
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Car> iterator = repository.findAll();
        assertFalse(iterator.hasNext(), "Repository should be empty initially");
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car("1", "Car One", "Blue", 2);
        Car car2 = new Car("2", "Car Two", "Green", 3);
        repository.create(car1);
        repository.create(car2);

        Iterator<Car> iterator = repository.findAll();
        assertTrue(iterator.hasNext());
        Car first = iterator.next();
        Car second = iterator.next();
        assertEquals("1", first.getCarId());
        assertEquals("2", second.getCarId());
        assertFalse(iterator.hasNext());
    }

    @Nested
    class FindByIdTests {
        @Test
        void testFindById() {
            Car car = new Car("1", "Test Car", "Red", 1);
            repository.create(car);

            Car found = repository.findById("1");
            assertNotNull(found, "Car should be found by id");
            assertEquals("Test Car", found.getCarName());
        }

        @Test
        void testFindByIdNotFound() {
            Car car = new Car("1", "Test Car", "Red", 1);
            repository.create(car);

            Car notFound = repository.findById("non-existent");
            assertNull(notFound, "Should return null if car with the id does not exist");
        }
    }

    @Nested
    class EditTests {
        @Test
        void testEdit() {
            Car car = new Car("1", "Old Car", "Red", 1);
            repository.create(car);

            Car updateCar = new Car("1", "New Car", "Blue", 2);
            Car updated = repository.update("1", updateCar);
            assertNotNull(updated, "Edit should return the updated car when it exists");
            assertEquals("New Car", updated.getCarName());
            assertEquals("Blue", updated.getCarColor());
            assertEquals(2, updated.getCarQuantity());
        }

        @Test
        void testEditNotFound() {
            Car car = new Car("1", "Old Car", "Red", 1);
            repository.create(car);

            Car updateCar = new Car("2", "New Car", "Blue", 2);
            Car updated = repository.update("2", updateCar);
            assertNull(updated, "Edit should return null for non-existent id");
        }
    }

    @Nested
    class DeleteTests {
        @Test
        void testDelete() {
            Car car1 = new Car("1", "Car One", "Red", 1);
            Car car2 = new Car("2", "Car Two", "Green", 2);
            repository.create(car1);
            repository.create(car2);

            Car deleted = repository.delete("1");
            assertNotNull(deleted, "Deleted car should not be null for existing id");

            Car found = repository.findById("1");
            assertNull(found, "Deleted car should no longer be found");

            Iterator<Car> iterator = repository.findAll();
            assertTrue(iterator.hasNext());
            Car remaining = iterator.next();
            assertEquals("2", remaining.getCarId());
            assertFalse(iterator.hasNext());
        }

        @Test
        void testDeleteNotFound() {
            Car car = new Car("1", "Car One", "Red", 1);
            repository.create(car);

            Car deleted = repository.delete("non-existent");
            assertNull(deleted, "Deletion of non-existent id should return null");

            Iterator<Car> iterator = repository.findAll();
            assertTrue(iterator.hasNext());
            Car remaining = iterator.next();
            assertEquals("1", remaining.getCarId());
            assertFalse(iterator.hasNext());
        }
    }

    @Test
    void testCreateGeneratesUuidIfNull() {
        Car car = new Car(); // carId is null
        car.setCarName("Test Car");
        car.setCarColor("Red");
        car.setCarQuantity(1);
        Car createdCar = repository.create(car);

        assertNotNull(createdCar.getCarId(), "UUID should be generated if carId is null");
        assertTrue(isValidUUID(createdCar.getCarId()), "The generated carId should be a valid UUID");
    }

    // Helper method to validate the UUID string
    private boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}