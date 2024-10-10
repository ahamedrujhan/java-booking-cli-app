package booking;

import car.Car;

public class BookingService {
    private final BookingDao bookingDao = new BookingDao();

public Car[] getCars(Car[] cars) {
    //No Cars available in the system
    if(cars.length == 0) {
        return new Car[0];
    }
    Booking[] bookings = bookingDao.getBooking();
    //No bookings available
    if(bookings.length == 0) {
        return cars;
    }
    var availableCarCount = 0;
    //Get available car count
    for(Booking booking : bookings) {
        boolean booked = false;

        for(Car car: cars) {
            if(bookings == null || !booking.getCar().equals(car)) {
                continue;
            }
            booked = true;
        }
        if(booked) {
            ++availableCarCount;
        }
    }
Car[] availableCars = new Car[availableCarCount];
    var index = 0;
    //get available cars
    for(Car car : cars) {
        boolean booked = false;

        for(Booking booking : bookings) {
            if(booking == null || !booking.getCar().equals(car) ) {
                continue;
            }
            booked = true;
        }
        if(!booked) {
            availableCars[index++] = car;
        }
    }
    return availableCars;

}

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

}
