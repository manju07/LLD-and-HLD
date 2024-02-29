 Seller data update(Seller app outside world)
  -  name, email, phone number

UI for Seller
Internal tools can use to update seller data(Walmart internal tool)

After update of seller -> FMS -> Walmart.com

Event Driven System

Seller-App
Http method: Put
Details: updating Seller data 
URL: /v1/seller-app/seller
Body: json data


Activity Tasks
 - Supporting multiple files
 - Supporting multiple column headers
 - Supporting multiple delimeters
 - Validation on column headers and data content within the column.
 - As a response, if there is any problem with the data or column header we do send a response file with the error message.
 - If there is no issue with the uploaded file, succes message is displyed in the UI.

 Busines Folks(Front-End App)
 - UI App

Backend-App(Product-Service)
- set of apis exposed
- cron job - Thread pool
- 

Database
DB name - New_Product
Table name -  Tasks
    - uId, taskId, createdAt, status, fileDetils,


