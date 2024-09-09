
Locking types:
- Optimistic locking: version based
- Pessimistic locking:  DB level

Optimistic locking:
    - Using @Version annotation.
    - Ex:
        @Version
        private Integer version;

    - 

Pessimistic locking:
    - locks can be either shared or exclusive.
        - If lock is exclusive
            - Prevent other transactions to read/update/delete
            - PESSIMISTIC_READ
        - If lock is shared
            - Allow other transactions to read and prevent for update/delete
            - PESSIMISTIC_WRITE