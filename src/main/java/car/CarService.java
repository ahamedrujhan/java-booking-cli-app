package car;

public class CarService {
    public final CarDAO carDAO = new CarDAO();

    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarByRegnum(String regNum) {
        for(Car car : getAllCars()) {
         if(regNum.equals(car.getRegNumber())) {
             return car;
         }
        }
        throw new IllegalStateException(String.format("car with reg %s not found", regNum));
    }

    public Car[] getAllElectricCars() {
        var electricCarCount = 0;

        Car[] cars = getAllCars();

        if(cars.length == 0) {
            return new Car[0];
        }
        for(Car car : cars) {
            if(car.isElectric()) {
                electricCarCount++;
            }
        }
        if(electricCarCount == 0) {
            return new Car[0];
        }
        Car[] electricCars = new Car[electricCarCount];

        var index = 0;

        for(var i = 0; i< cars.length; i++) {
            if(cars[i].isElectric()) {
                electricCars[index++] = cars[i];
            }
        }
        return electricCars;
    }

}
