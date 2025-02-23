
package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepositoryImpl implements CarRepository {
	static int id = 0;
	private List<Car> carData = new ArrayList<>();

	public Car create(Car car) {
		if (car.getCarId() == null) {
			UUID uuid = UUID.randomUUID();
			car.setCarId(uuid.toString());
		}
		carData.add(car);
		return car;
	}

	public Iterator<Car> findAll() {
		return carData.iterator();
	}

	public Car findById(String id) {
		for (Car car : carData) {
			if (car.getCarId().equals(id)) {
				return car;
			}
		}
		return null;
	}

	public Car update(String id, Car updateCar) {
		for (Car car : carData) {
			if (car.getCarId().equals(id)) {
				car.update(updateCar);
				return car;
			}
		}
		return null;
	}

	public Car delete(String id) {
		Car car = carData.stream()
                .filter(p -> p.getCarId().equals(id))
                .findFirst()
                .orElse(null);
        carData.remove(car);
        return car;
		
	}
}
