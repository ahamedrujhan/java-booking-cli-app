package car;

import java.math.BigDecimal;

public class CarDAO {

    private static final Car[] CARS = {
            new Car("1234",new BigDecimal("1200.765"),false, Brand.AUDI),
            new Car("6743",new BigDecimal("234.56"),false,Brand.VW),
            new Car("5432",new BigDecimal("1400"),true, Brand.TESLA)
    };

    public  Car[] getAllCars() {
        return CARS;
    }

}
