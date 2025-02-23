package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    public Car() {
        this.carId = null;
        this.carName = null;
        this.carColor = null;
        this.carQuantity = 0;
    }

    public Car(String carId, String carName, String carColor, int carQuantity) {
        this.carId = carId;
        this.carName = carName;
        this.carColor = carColor;
        this.carQuantity = carQuantity;
    }

    public Car update(Car updateCar) {
        this.setCarName(updateCar.getCarName());
        this.setCarColor(updateCar.getCarColor());
        this.setCarQuantity(updateCar.getCarQuantity());

        return this;
    } 
}