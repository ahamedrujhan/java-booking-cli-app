package booking;

import java.util.UUID;

public class BookingDao {
    private final static Booking[] bookings;

    static {
        bookings = new Booking[10];
    }

    public Booking[] getBooking() {
        return bookings;
    }

    //adding so this is belongs to dto
    public void book(Booking booking) {
        int nextFreeIndex = -1;

        //find next Free index

        for(var i = 0; i<bookings.length; i++) {
            if(bookings[i] == null) {
                nextFreeIndex = i;
            }
        }

        //array not empty
        if(nextFreeIndex > -1) {
            bookings[nextFreeIndex] = booking;
            return;
        }

        //array full
        Booking[] bigBooking = new Booking[bookings.length + 10];

        //copy all contents to new Array
        for(var i = 0; i< bookings.length;i++) {
            bigBooking[i] = bookings[i];
        }

        //add new bookings
        bigBooking[bookings.length] = booking;


    }

    public void cancelBooking(UUID id) {
        //implement function
    }


}
