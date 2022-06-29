Property management system


Database: property
    - tables
        - Property
            - id, name, description, category_id, property_details_id, owner_id, createdAt, updatedAt, createdBy, updatedBy 
        - Category
            - id, name,  createdAt, updatedAt, createdBy, updatedBy
        - Property_Details
            - id, bathrooms, kichens, toilets, createdAt, updatedAt, createdBy, updatedBy
        - Property_Monthly_Data
            - id, property_id, paidDate, paidType, createdAt, updatedAt, createdBy, updatedBy


int arr[] = new int{1, 2, 3, 4, 5, 6, 3, 2, 4, 3};

@Entity
class Person {
    String phoneNo;
    String email;
    Address address;
}

@Entity
class Student extends Person {
    Long id;
    String fName;
    String lName;
    String depart;
}

@Entity
class Faculty extends Person {
    Long id;
    String fName;
    String lName;
    String depart;
}

