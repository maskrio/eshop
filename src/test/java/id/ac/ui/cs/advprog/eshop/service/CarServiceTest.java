package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarName("Car 1");
        car.setCarColor("Red");
        car.setCarQuantity(10);

        when(carRepository.create(car)).thenAnswer(invocation -> {
            Car arg = invocation.getArgument(0);
            if (arg.getCarId() == null) {
                arg.setCarId("generated-id");
            }
            return arg;
        });

        Car createdCar = carService.create(car);
        assertNotNull(createdCar.getCarId(), "Car ID should not be null after creation");
    }

    @Test
    void testFindAll() {
        Car car1 = new Car("1", "Car 1", "Red", 10);
        Car car2 = new Car("2", "Car 2", "Blue", 20);
        List<Car> carList = Arrays.asList(car1, car2);
        Iterator<Car> iterator = carList.iterator();

        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(car1));
        assertTrue(result.contains(car2));
    }

    @Test
    void testFindById() {
        String carId = "1";
        Car car = new Car(carId, "Car 1", "Red", 10);
        when(carRepository.findById(carId)).thenReturn(car);

        Car result = carService.findById(carId);
        assertEquals(car, result);
    }

    @Test
    void testUpdate() {
        String carId = "1";
        Car updatedCar = new Car(carId, "Car 1", "Green", 15);
        when(carRepository.update(carId, updatedCar)).thenReturn(updatedCar);

        Car result = carService.update(carId, updatedCar);
        assertEquals(updatedCar, result);
    }

    @Test
    void testDelete() {
        String carId = "1";
        Car car = new Car(carId, "Car 1", "Red", 10);
        when(carRepository.delete(carId)).thenReturn(car);

        Car result = carService.deleteCarById(carId);
        assertEquals(car, result);
    }
}