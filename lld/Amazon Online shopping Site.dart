 Amazon Online shopping Site

 Seller System
 - Add product
 - update product
 - list all added product
 - Search by product

 Buyer System
 - Search by product
 - Place order
 - History
 
 Amazon
- Send notifications

class User {
    long id;
    String fName;
    String lName;
    String emailId;
    String phoneNumber;
    Address address;
}

class Seller extends User {
    ShoppingCart shoppingCart
    public Boolean addProductToShoppingCart(Product product);
    public Boolean updateProduct(Product product);
    public Boolean deleteProduct(Product product);
}

class Address {
    int id;
    String houseNumber;
    String street;
    String landMark;
    String pincode;
    String addressDetails;
    String state;
    String country;
}

class Product {
    long id;
    String name;
    String description;
    Category category;
    Long quantity;
    Double price;
    Date createdAt;
    Date updatedAt;
    Seller seller;
    List<ProductReview> productReviews;

    public List<Product> getProducts();
    public List<Product> getProductById(Long productId);
    public List<ProductReview> getProductReviews(Product product);
}

enum Category {
    GROCERY, CLOTHS, ELECTRONICS, MOBILE;
}

class Buyer extends User {
    ShoppingCart shoppingCart;
    List<Order> orders;
    AccountStatus status;

    public List<Product> searchByProduct(Product product);
    public Boolean placeOrder(List<Product> products);
    public Boolean addReview();
}

enum AccountStatus {
    ACTIVE, INACTIVE, DELETED, BLOCKED;
}

class ShoppingCart {
    List<Item> items;
    Double cartValue;
    public List<Item> getAllItems();
    public Boolean addItem(Item item);
    public Boolean updateItem(Item item);
    public Boolean deleteItem(Item item);
    public Doble getCartValue();
}

class Item {
    Product product;
    int quantity;
    Double price;

    public Item getItemDetailsByItemId();
}

class Search {
    public List<Product> searchByProduct(Product name);
    public List<Product> searchByCategory(Category category);
}

class Order {
    Long id;
    List<Item> items;
    Double amount;
    OrderStatus orderStatus;
    Buyer buyer;
    Date orderdDate;
    List<OrderLog> orderLogs; 
    NotificationSystem notificationSystem;
    Date createdAt;
    Date updatedAt;

    public OrderStatus placeOrder();
    public OrderStatus trackOrder();
    public void addOrderLogs();
    public PaymentStatus makePayment(PaymentType paymentType);
}


class ProductReview {
    Long id;
    Buyer buyer;
    int rating;
}

enum OrderStatus {
    SHIPPED, PICKED, DELIVERED, ORDERD, CANCELLED, OUT_FOR_DELIVERY
}

class Payment {
    Long id;
    PaymentType paymentType;
    PaymentStatus status;
    String orderDetails
    Double amount;
    Date createdAt;
    Date updatedAt;
}

enum PaymentType {
    CREDIT_CARD, DEBIT_CARD, UPI, CASH
}

enum PaymentStatus {
    SUCCESS, PENDING, DECLINED, CANCELLED, REFUND_INTIATED, REFUNDED
}

class OrderLog {
    Long id;
    Order order;
    OrderStatus status;
    String description;
    Date createdAt;
    Date updatedAt;
}

class NotificationObj {
    Long id;
    NotificationType notificationType;
    User user;
}

class NotificationSystem {
    Notification notification;
    MessageAttribute messageAttribute;
    public Boolean sendNotifications(NotificationObj notificationObj, MessageAttribute messageAttribute) {
        switch(notificationSystem.getNotificationType()) {
            case NotificationObj.EMAIL:
                notification = new EmailNotication();
                messageAttribute = new EmailMessageAttribute(from, to, message, body);
                break;

            case NotificationType.SMS:
                notification = new SMSNotication();
                messageAttribute = new SMSMessageAttribute(from, to, message);
                break;

            case NotificationType.WHATSAPP:
                notification = new WHATSAPPNotication();
                messageAttribute = new WhatsAppMessageAttribute(from, to, message);
                break;
        }
        return notification.sendNotifications(messageAttribute);
    } 
}


class Notification {
    public Boolean sendNotifications(MessageAttribute messageAttribute);
}

class EmailNotication extends Notification {
    public Boolean sendNotifications(MessageAttribute messageAttribute);
}

class MessageAttribute {
    String from;
    String to;
    String message;
}


enum NotificationType {
    EMAIL, SMS, WHATSAPP
}