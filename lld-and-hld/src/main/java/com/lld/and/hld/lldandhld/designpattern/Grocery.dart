Design a system to support the menu page of a grocery app, where users can see the availability of any grocery item. 

i.e : if the user opens the app's menu at 8 AM and if "bread" is available at 8 am then he will see bread available and also see the message that "available till 9 AM today". 
If another user opens the app's menu at 10 PM and searches for bread, he will see bread is unavailable and see the message that "next available at 7 AM tomorrow". 

Functional Requirements: 

1. Users should see the appropriate messages.
2. availability can vary from one day or one week. (in case of more than one day message will say "next available at 8 PM on Wednesday")


Database: Grocery
 - Tables
    - Item:
        - id, name, description, category_id, createdBy, updatedBy,createdAt, updatedAt
        - 1, bread, morning sell, 1, 

    - ItemConfig
        - id, item_id, quontity,  availabilityStartTime, availabilityEndTime, createdBy, updatedBy, createdAt, updatedAt
        - 1, item_1, 10, 7:00, 10:00, 

    - ItemAvailabilityConfig
        - id, item_id, Type, avaialableDays
        - 1, item_1,  Daily, []
        Weekly[3, 5], Monthly[1-31] [1, 4, 7, 10], Yearly[1-365] [300, 364]
 
    - Category:
        - id, name, description
        - 1, grocery, grocery

    - SupplierItemMapping
        - id, supplier_id, item_id
        - 1, supplier_1, item_1

Supplier
 -  Bread, 10, 8 to 10
 -  Milk, 100, 6 to 10


Service: grocery-service
    - User APIs:
        - searching item
            - URL: /grocery-service/v1/api/item/search?name=bread
            - Http method: GET
            - content-type: application/json
            - Response 
                {
                    id: item_1,
                    name: "bread",
                    description: "description",
                    message: "Avaialable till 9AM" / "Next availability 7AM",
                    quantity: 8
                }
      


Service: template-message-service
    - Finding availability by item
        - URL: /template-message-service/v1/api/item/item_1/
        - HTTP Get
        - Response 
        {
            message: message: "Avaialable till 9AM" / "Next availability 7AM",
        }

enum AvailabilityType {
    DAILY, Weekly, Monthly;
}

public String constructMessageByItemId(Long itemId) {
    ItemAvailabilityConfig itemAvailabilityConfig = itemAvailabilityConfigRepository.getByItemId(itemID);
    String message = null;
    if(AvailabilityType.DAILY.toString().equals(itemAvailabilityConfig.getType)) {
        // time compare

    } else if(AvailabilityType.Weekly.toString().equals(itemAvailabilityConfig.getType)) {
        // 

    } else if(AvailabilityType.Monthly.toString().equals(itemAvailabilityConfig.getType)) {
        //
    } 
    return message;
} 


 