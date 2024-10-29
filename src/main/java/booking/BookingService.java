package booking;

import car.Car;
import car.CarService;
import user.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class BookingService {
    private final BookingDao bookingDao = new BookingDao();
    private final CarService carService = new CarService();
//public Car[] getCars(Car[] cars) {
//    //No Cars available in the system
//    if(cars.length == 0) {
//        return new Car[0];
//    }
//    Booking[] bookings = bookingDao.getBooking();
//    //No bookings available
//    if(bookings.length == 0) {
//        return cars;
//    }
//    var availableCarCount = 0;
//    //Get available car count
//    for(Booking booking : bookings) {
//        boolean booked = false;
//
//        for(Car car: cars) {
//            if(bookings == null || !booking.getCar().equals(car)) {
//                continue;
//            }
//            booked = true;
//        }
//        if(booked) {
//            ++availableCarCount;
//        }
//    }
//Car[] availableCars = new Car[availableCarCount];
//    var index = 0;
//    //get available cars
//    for(Car car : cars) {
//        boolean booked = false;
//
//        for(Booking booking : bookings) {
//            if(booking == null || !booking.getCar().equals(car) ) {
//                continue;
//            }
//            booked = true;
//        }
//        if(!booked) {
//            availableCars[index++] = car;
//        }
//    }
//    return availableCars;
//
//}

public Booking[] getBookings() {
    Booking[] bookings = bookingDao.getBooking();

    var numberOfBooking = 0;

    for (Booking booking : bookings) {
        if(booking != null) {
            ++numberOfBooking;
        }
    }
    if(numberOfBooking == 0) {
        return new Booking[0];
    }

    Booking[] carBookings = new Booking[numberOfBooking];

    var index = 0;

    for(Booking booking: bookings) {
        if(booking !=null) {
            carBookings[index++] = booking;
        }
    }
    return carBookings;
    }

    public Car[] getAvailableCars() {
    return getCars(carService.getAllCars());
    }

    public Car[] getAvailableElctricCar() {
    return getCars(carService.getAllElectricCars());
    }

    public Car[] getUserBookedCars (UUID userId) {
        Booking[] bookings = getBookings();
        int numberOfBookingForUser = 0;

        for (Booking booking : bookings) {
            if(booking != null && booking.getUser().getId().equals(userId)) {
                ++numberOfBookingForUser;
            }
        }
        if(numberOfBookingForUser == 0) {
            return new Car[0];
        }
        Car[] userCars = new Car[numberOfBookingForUser];

        int index = 0;

        for(Booking booking : bookings) {
            if(booking != null && booking.getUser().getId().equals(userId)) {
                userCars[index++] = booking.getCar();
            }
        }
        return userCars;
    }

    public UUID bookCar(User user, String regNum) {
    Car[] availableCars = getAvailableCars();
    if(availableCars.length == 0) {
        throw new IllegalStateException("No Car Available For Renting");
    }
    for (Car availbleCar : availableCars) {
        if(availbleCar.getRegNumber().equals(regNum)) {
            Car car = carService.getCarByRegnum(regNum);
            UUID bookingId = UUID.randomUUID();
             bookingDao.book(new Booking(bookingId,user,car, LocalDateTime.now()));
            return bookingId;
        }
    }
    throw new IllegalStateException("Already booked. car with regNumber " + regNum);
    }

    private Car[] getCars(Car[] cars) {
//        System.out.println(cars.length);

    //no Cars in the system
        if(cars.length == 0) {
            return new Car[0];
        }
        Booking[] bookings = bookingDao.getBooking();
//        System.out.println(bookings.length);

        //no booking in the system
        if(bookings.length == 0) {
            return cars;
        }

        int availableCarCount = 0;
        for (Car car : cars) {
            //check specific car is a part of booking
            Boolean isBooked = false;
            for(Booking booking : bookings) {

                if(booking == null || !booking.getCar().equals(car)) {
                    continue;
                }
                isBooked = true;

            }

            if(!isBooked) {
                ++availableCarCount;
            }
        }
//        System.out.println(availableCarCount);
        Car[] availableCars = new Car[availableCarCount];

        int index = 0;
        //get availble cars
        for (Car car : cars) {
            boolean isBooked = false;
            for (Booking booking : bookings) {
                if(booking == null || !booking.getCar().equals(car)) {
                    continue; //??
                }
                isBooked = true;

            }
            if(!isBooked) {
                availableCars[index++] = car;
            }
        }
//        System.out.println(Arrays.toString(availableCars));
        return availableCars;
    }



}
