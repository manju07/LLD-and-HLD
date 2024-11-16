- data types
    - string
        - string - string
        - methods
            - set key value
                - ex: set name manjunath 
            - get key
               - get name
            - del key

    - hashmap
        - key - hashmap [field, value ... ]
        - methods
            - hmset key file value field value
                - hmset key name manjunath   
            - hgetall key

    - list
        - key - array
        - lpush key v1 v2
        - lrange key startIndex endIndex

    - set
        - key - set
        - sadd key s1 s2
        - smembers key

    - sorted set
        - arranging by score
        - key - [{score, value}]
        - zadd ss1 0 m1 1 a1
        - zrangebyscore ss1  0 1
        
