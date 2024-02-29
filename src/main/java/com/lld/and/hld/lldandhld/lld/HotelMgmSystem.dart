Hotel Management System

Functional Requirments
- searching room
- booking room
- cancelling room if 24 hours before check-in time
- sending notifications 
- check-in and check-out by receiptionist
- add services to room
- housekeeping logs 


class Hotel {
    Long id;
    String name;
    String description;
    Address address;
    public List<Room> searchAvailableRooms();
    public List<Room> searchBookedooms();
}

class Address {
    String details;
    String pincode;
    Long pincode;
    String city;
    String district;
    String state;
    String country; 
}

class Account {
    Long id;
    String fName;
    String lName;
    String emailId;
    String phoneNumber;
    Address address;
}

class Guest {
    Account account;

    public List<Room> searchRoom(RoomType roomType, String city, Date checkInDate);
    public BookingStatus bookRoom();
}

class Receiptionist {
    Account account;

    public List<Room> searchRoom(RoomType roomType, String city, Date checkInDate);
    public BookingStatus bookRoom();
    public Boolean checkIn();
    public Boolean checkOut();
}

class HouseKeeper {
    Account account;

    public Boolean housekeeping();
}


class Room {
    Long id;
    String roomId;
    RoomType roomType;

    public List<Room> searchRoom(RoomType roomType, String city, Date checkInDate); // Receiptionist, Guest
    public Boolean bookRoom(); // Receiptionist, Guest
    public Room addRoom(); // Admin
    public Room updateRoom(); // Admin
    public Room deleteRoom(); // Admin
}

enum RoomType {
    AC, AC_STANDARD, DELUXE, STANDARD
}

class Service {
    Long id;
    Long roomId;
    ServiceType serviceType;
    String details;
    User userDetails;
    ServiceStatus serviceStatus;
}

enum ServiceType {
    FOOD_ORDER, HOUSEKEEPING
}

enum ServiceStatus {
    INITIATED, IN_PROGRESS, COMPLETED, CANCELLED
}

class BookRoom {
    int id;
    Long roomId;
    String roomDetails;
    Guest userDetails;
    BookingStatus status;
    Date checkInTime;
    Date checkOutTime;
    Date createdAt;
    Date updatedAt;
    Payment paymetDetails;
    NotificationService notificationService;

    public BookingStatus bookingStatus();
}

enum BookingStatus {
    BOOKED, PAYMENT_PENDING, CANCELLED 
}

class Payment {
    Long id;
    Long bookingId;
    PaymentStatus status;

    public PaymentStatus paymentStatus();
}

enum PaymentStatus {
    PENDING, ERROR, SUCCESS
}

class NotificationService {
    Notification notification;
    public void sendNotification(NotificationType notificationType, MessageAttribute messageAttribute) {
        switch(notificationType) {
            case WHATSAPP: 
                messageAttribute =  new MessageAttribute(messageAttribute.getFrom(), messageAttribute.getTo(), messageAttribute.getMessage());
                notification =  WHATSAPPNotication(messageAttribute);
                notification.sendNotification();
                break;
            case EMAIL: 
                messageAttribute =  new MessageAttribute(messageAttribute.getFrom(), messageAttribute.getTo(), messageAttribute.getMessage());
                notification =  EMAILNotication(messageAttribute);
                notification.sendNotification();
                break;
            case SMS: 
                messageAttribute =  new MessageAttribute(messageAttribute.getFrom(), messageAttribute.getTo(), messageAttribute.getMessage());
                notification =  SMSNotication(messageAttribute);
                notification.sendNotification();
                break;
        }
    }
}

class Notification {
    public Boolean sendNotification();
}

class WHATSAPPNotication extends Notification {
    public Boolean sendNotification(MessageAttribute messageAttribute);
}

class EMAILNotication extends Notification {
    public Boolean sendNotification(MessageAttribute messageAttribute);
}

class SMSNotication extends Notification {
    public Boolean sendNotification(MessageAttribute messageAttribute);
}

