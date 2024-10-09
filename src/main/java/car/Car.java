package car;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private String regNumber;
    private BigDecimal rentalPrice;
    private boolean isElectric;
    private Brand brand;

    public Car(String regNumber, BigDecimal rentalPrice, boolean isElectric, Brand brand) {
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.isElectric = isElectric;
        this.brand = brand;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", isElectric=" + isElectric +
                ", brand=" + brand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isElectric == car.isElectric && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPrice, car.rentalPrice) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPrice, isElectric, brand);
    }
}
