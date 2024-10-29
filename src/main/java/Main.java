import booking.Booking;
import booking.BookingService;
import car.Car;
import user.User;
import user.UserService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // create Scanner Object to receive from terminal
        Boolean keepLooping = true;
        UserService userService = new UserService(); // user service instance
        BookingService bookingService = new BookingService(); // booking service instance;
        while (keepLooping) {
            try {
                showMenu(); //Showing the Menu
                String choice = scanner.nextLine(); // received value from terminal
                switch (Integer.parseInt(choice)) {
                    case 1 -> bookCar(userService,bookingService,scanner);
                    case 2 -> displayUserBookedCars(userService, bookingService,scanner);
                    case 3 -> allBookings(bookingService);
                    case 4 -> displayAvailableCars(bookingService,false);
                    case 5 -> displayAvailableCars(bookingService,true);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepLooping = false;
                    default -> System.out.println(choice + " is not a valid option ❌");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private static void showMenu() {
        System.out.println("\n" +
                "                1️⃣ - Book Car\n" +
                "                2️⃣ - View All User Booked Cars\n" +
                "                3️⃣ - View All Bookings\n" +
                "                4️⃣ - View Available Cars\n" +
                "                5️⃣ - View Available Electric Cars\n" +
                "                6️⃣ - View all users\n" +
                "                7️⃣ - Exit");
    }

    private static void displayAvailableCars(BookingService bookingService, boolean isElectric) {
        Car[] availableCars = isElectric ? bookingService.getAvailableElctricCar() : bookingService.getAvailableCars();

        if(availableCars.length == 0) {
            System.out.println("❌ No cars available for renting");
            return;
        }
            System.out.println(Arrays.toString(availableCars));
    }

    private static void displayAllUsers(UserService userService) {
        User[] users = userService.getUsers();

        if(users.length == 0) {
            System.out.println("❌ No users in the system");
            return;
        }
        System.out.println(Arrays.toString(users));

    }

    private static void displayUserBookedCars (UserService userService, BookingService bookingService, Scanner scanner) {
        displayAllUsers(userService); // display All users
        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        User user = userService.getUserById(UUID.fromString(userId));
        if(user == null) {
            System.out.println("❌ No user found with id " + userId);
            return;
        }
        Car[] userCars = bookingService.getUserBookedCars(user.getId());
        if(userCars.length == 0) {
            System.out.printf("❌ user %s has no cars booked", user);
            return;
        }
        System.out.println(Arrays.toString(userCars));


    }

    private static void allBookings(BookingService bookingService) {
        Booking[] bookings = bookingService.getBookings();

        if(bookings.length == 0) {
            System.out.println("No bookings available 😕");
            return;
        }

        System.out.println(Arrays.toString(bookings));
    }

    private static void bookCar(UserService userService, BookingService bookingService, Scanner scanner) {
        displayAvailableCars(bookingService,false); // show all available cars
        System.out.println("➡️ select car reg number");
        String regNumber = scanner.nextLine();
        //show all users
        displayAllUsers(userService);

        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));
            if(user == null) {
                System.out.println("❌ No user found with id " + userId);
            }
            else {
                UUID bookingId = bookingService.bookCar(user, regNumber);
                String confirmMessage = " 🎉Successfully booked car with reg number %s \n for user %s \n Booking ref %s".formatted(regNumber, user, bookingId);
                System.out.println(confirmMessage);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
