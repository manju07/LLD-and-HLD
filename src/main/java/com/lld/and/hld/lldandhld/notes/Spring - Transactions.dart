@Transactional
https://www.baeldung.com/spring-transactional-propagation-isolation

Types:
    - Propagation
    - Isolation
    - rollbackfor
    - norollbackfor


- Isolation (https://www.geeksforgeeks.org/transaction-isolation-levels-dbms/)
    - Phenomenas/Scenarios:
        - Dirty-read: Read uncommitted data.
            - T1 updated row but not committed.
            - T2 read data which is not committed.
            - T1 uncommit the changes
            - T2 have data which is never exists(dirty data)

        - Non-repeatable read: It retrieves different data for same row for each read.
            - C1 - T1 - read data - X
            - C1 - T2 - update data - Y 
            - C2 - T1 - read data - different value - Y

        - Phantom read: Phantom read occures when 2 same queries executed and get different results.
            - T1 - execute query, get some result
            - T2 - generate some data which match results of T1 query
            - T2 - execute query, get result of T2

    - Levels:
        - Read-Uncommitted: lower isolation level.dirty read happens here. 
        - Read-Committed: avoid dirty read, hold lock on read / write row for updating or deleting.
        - Repeatable-Read: avoid non-repeatable read, hold locks on all read / write rows for updating or deleting.
        - Serializable: highest isolation level. concurrently executings make them to execute serially or sequentially.




