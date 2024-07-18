
- Mappings
    - One-To-One Mapping
        - lets say customer has an address, we need to have One-To-One mapping b/w customer and address tables.
            
            - Customer table owns the address details

                Customer:
                    @OneToOneMapping(cascade = CascadeType.ALL)
                    @JoinColumn(name = "address_id", referencedColumnName="id")
                    private Address address;

                Address:
                    @OneToOneMapping(mappedBy="address")
                    private Address address;

            - Using shared primary key - If we want to optimize the memory and re-utilize the customer_id generated in customer table and going as a foreign key to address table but in address table considered as primary.
                - https://www.baeldung.com/jpa-one-to-one
                @PrimaryKeyJoinColumn

            - Customer table owns the address details in separate table called customer_address(customer_id, address_id)
                
                Customer:
                    @OneToOneMapping(cascade = CascadeType.ALL)
                    @JoinTable(
                    name = "customer_address",
                    joinColumns = { joinColumn(name = "user_id", referencedColumnName="id")}
                    inverseJoinColumns = { joinColumn(name = "address_id", referencedColumnName="id")})
                    private Address address;

                Address:
                    @OneToOneMapping(mappedBy="address")
                    private Address address;


    - OneToMany
    - ManyToOne
    - ManyToMany

- Cascade Types (https://www.baeldung.com/jpa-cascade-types)
    - JPA Types
        - all
        - persist
        - merge
        - remove
        - detach
        - refresh

    - Hibernate Types
        - delete
        - replicate
        - save_update
        - lock

- Persistence Context (https://www.baeldung.com/jpa-hibernate-persistence-context#test_cases_extended_persistence_context)
    - Using EntityManager can do CRUD operations with entities.
        -  persist() - save, merge() - update, remove, find, flush(), clear() 
        - After performing persist/merge, update to persistence context
        - Then flush() update to perstent storage(DB)

        - Flow:
            - application -> persistence context -> DB


    - Types
        - Transaction Persistence Context(Default):  available to one transaction. after finishing the transaction, entities pushed/flushed to DB.

        - Extended-Scoped Persistence Context: entities are available b/w multiple transactions.
            - Without a transaction, able to persist an entity, but cannot flush to persistent storage.
            - With a transaction, able to persist an entity to persistent context and persistent storage.
            
            ex:
            @PersistenceContext(type = PersistenceContextType.EXTENDED)
            private EntityManager entityManager;
            
            

