
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

- Cascade Types (https://www.baeldung.com/jpa-cascade-types) - this is used for how to behave on child entities.
    - JPA Types
        - ALL - Perform all operations from parent to child entities
        - PERSIST - Perform save operations from parent to child entities
        - MERGE - Perform update operations from parent to child entities
        - REMOVE - Perform delete operations from parent to child entities
        - DETACH - Perform delete entity operations from persistent context from parent to child entities.
        - REFRESH - Perform reloading/rereading the data from DB from parent to child entities.

    - Hibernate Types
        - delete - same as REMOVE
        - replicate 
        - save_update 
        - LOCK -  Perform attach entity operations to persistent context  (opposite of DETACH).

    Ex:
    @Column(cascade=CascadeType.ALL)



- Persistence Context (https://www.baeldung.com/jpa-hibernate-persistence-context#test_cases_extended_persistence_context)
    - Using EntityManager can do CRUD operations with entities.
        -  persist() - save, merge() - update, remove, find, flush(), clear() 
        - After performing persist/merge, update to persistence context
        - Then flush() update to perstent storage(DB)

        - Flow:
            - application -> persistence context -> DB
    
    - Persistence Context is a cache which entities saved/updated to DB.
        - Hibernate provides the Session interface for persistence context (https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate)
        - JPA provides the EntityManager interface for persistence context.

    - Types
        - Transaction Persistence Context(Default):  available to one transaction. after finishing the transaction, entities pushed/flushed to DB.

        - Extended-Scoped Persistence Context: entities are available b/w multiple transactions.
            - Without a transaction, able to persist an entity, but cannot flush to persistent storage.
            - With a transaction, able to persist an entity to persistent context and persistent storage.
            
            ex:
            @PersistenceContext(type = PersistenceContextType.EXTENDED)
            private EntityManager entityManager;
            
            

- Types of JPA Queries (https://www.baeldung.com/jpa-queries)

    - JPQL(Java persistence query language): execute on entities, similar to native SQL queries

        Ex:
        Employee getEmployeeById(long id) {
            Query query = entityManager.createQuery("select E from Employee e where e.id=:id");
            query.setParameter("id", id);
            return  (Employee) query.getSingleResult();
        }

        - Types:
            - Typed Query: Specify the expected output while creating query
                Employee getEmployeeById(long id) {
                    TypedQuery<Employee> typedQuery = entityManager.createQuery("select e from Employee e where e.id=:id", Employee.class);
                    typedQuery.setParameter("id", id);
                    return typedQuery.getSingleResult();
                }

            - Named Query: Specify in entity level, this is a static. use the named query name to fetch the query.
                @Entity
                @NamedQuery(name ="fetch_employee_by_id", query = "select e from Employee e where e.id=:id")
                class Employee {

                }

                Employee getEmployeeById(long id) {
                    Query namedQuery = entityManager.createNamedQuery("fetch_employee_by_id");
                    namedQuery.setParameter("id", id);
                    return (Employee) namedQuery.getSingleResult();
                }


    - Native Query: Execute raw SQL queries.
        
        Ex:

        Employee getEmployeeById(long id) {
            Query nativeQuery = entityManager.createNativeQuery("select * from employee e where e.id=:id");
            nativeQuery.setParameter("id", id);
            return  (Employee) nativeQuery.getSingleResult();
        }

    - Criteria Query: programmitically built, type-safe.

- JPA Query Params (https://www.baeldung.com/jpa-query-parameters)

    - Positional Parameters
        Employee getEmployeeById(long id, String deptName) {
            Query query = entityManager.createQuery("select E from Employee e where e.id=?1 and e.deptName=?2");
            query.setParameter(1, id);
            query.setParameter(2, deptName);
            return  (Employee) query.getSingleResult();
        }



    - Named Parameters
        Employee getEmployeeById(long id, String deptName) {
            Query query = entityManager.createQuery("select E from Employee e where e.id=:id and e.deptName=:deptName");
            query.setParameter("id", id);
            query.setParameter("deptName", deptName);
            return  (Employee) query.getSingleResult();
        }


