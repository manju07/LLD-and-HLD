Bookmyshow Low Level Design

Supporting within india

Features
- supporting multiple cities
- Searching cinemas by place
- Online Ticket Booking

Hierarchy of BookMyShow App
- Cities
    - Cinema Halls
        - Multiple Screens
            - Shows
                - Seats

class City {
    Long id;
    String name;
    String district;
    String taluka;
    String state;
    List<CinemaHalls> cinemas;

    public List<CinemaHalls> getCinemaHallsByCityName(String city);
}

class CinemaHall {
    Long id;
    String name;
    String description;
    Address address;
    List<Screen> screens;

    public List<Screen> getAllScreens();
}

class Address {
    Long id;
    String houseDetails;
    String street;
    String area;
    String pincode;
    String state;
}

class Screen {
    Long id;
    String name;
    Long seatsCount;
    List<Show> show;

    public List<Show> getShowsList();
}

class Show {
    Long id;
    Date startsAt;
    Date endsAt;
    Double price;
    Movie movie;
    List<Seat> seats;

    public List<Seat> getSeats();
}

class Seat {
    Long id;
    String seatId;
    SeatStatus seatStatus;
    Payment payment;

    public Seat bookTicket();
    public SeatStatus getSeatStatus();
    public List<Seat> getAllAvailableSeats();
    public List<Seat> getAllBookedSeats(); 
}

enum SeatStatus {
    AVAILABLE, BOOKED, CANCELLED, REFUND_INTIATED, REFUNDED;
}

class Movie {
    Long id;
    String name;
    String description;
    Long duration;
    
    public Movie getMovieDetails(String name);
}

class Payment {
    Long id;
    PaymentStatus status;
    PaymentType paymentType;

    public Payment makePayment(PaymentType paymentType);
}

