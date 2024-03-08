Tiny URL System Design

Main Goal 
    - Lengthy URL (https://test.com/fdsgoihsf?gef=5345&sdfg=5345&fsdf=43345) -> Short URL (https://tinyurl/id) 

User Actions
    - Paste the URL and get the corresponding tiny URL
    - When clicked on tiny URL, redirect to original one
    

Functional Requirements:
    - Create new tiny URL
    - Redirect tiny URL to original one
    - After period of time, link expired. 
    - Provide a custom id to choose for tiny URL.

Non-Funcational Requirements:
    - Highly availabl, if not so, redirection start failing
    - Redirection should happen with minimal latency
    - Tiny URL's shouldn't be guessable.
    
Capacity Estimation:

    Traffic Estimation:
        - Creation of 1M tiny URL's per day, monthly = 30M 
            - 1000000/24/60 = 700 requests/Min
        - Read requests 100X of creation ( 1 * 100 =  100M/day), monthly 100M*30 = 300M 

    Storage:
        - Assuming object of size 500 bytes (original_url, tiny_url, other details)
        - Considering, we store an URL for 5 years.
        - DB Size = 500 bytes * 30M * 12 * 5 = 1 TB

    Memory:
        - Cache Size(20% of DB) =  200 GB
    
    Bandwidth:  
        - Create
            - 700 requests * 500 = 400 KB/Min
        - Redirection
            - 700 * 100 * 500 = 40k KB/Min


API's:
    - createTinyURL(UUID userId, String URL): String(tinyURL)
    - redirectURL(String URL) -> route to tiny URL


Data Models:

    - user (id, f_name, l_name, email_Id, phone_number, created_at, updated_at)
    - tiny_url_data (id, tiny_url_id, original_url, user_id, created_at, updated_at) 
    - tiny_data_availability(id, tiny_url_data_id, creation_time, duration)


High level System Design:

    - user-service
    - tiny-url-service
    - read-tiny-url-service
    - unique-id-generator
    - data-syncher (Sql - NoSql)


    User-Management:
        User -----> load_balancer -----> user-service (createTinyURL) ------> DB (SQL - MySQL/Oracle)

    Tiny URL creation
        User -----> load_balancer -----> tiny-url-service  ------>  DB (NoSql - Cosmos/Casssandra)

    Redirect to Tiny URL
        User -----> load_balancer -----> read-tiny-url-service  ------> Cache(Redis/Memcache) ---> DB (NoSql - Cosmos/Casssandra)

unique-id-generator:
    Approaches:
    - UUID - Repeatation of ID is less, but possibility there.

    - Hashing on given URL + User_Id ->
 

    - Id is combination of data_center_id(1 bit) + compute_id(3 bits) + UUID (4-4-4-4 = 16 bytes) = 17 bytes
        
        Samples:
            1-5-fsdf3-435f-345f-545 --> hex

        1 bit -> Z^1 = 2 = [0-1]
        3 bits -> 2^3 = 8 = [0-7]
        16 bytes -> 2^16 = 65,536 = [0-65,535]

        = 2 * 8 *  64^16 = 1.2676506002×10³⁰  combinations


    - Generate random strings of length 6 using base64(a-z,A-Z,0-9,'-','.'), so that we can have 64^6 = 68,71,94,76,736  = 68B combinations



 

