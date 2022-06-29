Recharge Service -  Java using spring boot

3 SQS queues
  - S1 - Holding Income requests  
  - S2 - Holding In-Progress requests
  - S3 - Holding failed requests  

Processes
 -  ProcessingIncomingRequestsProcessor
     - consuming messages from the first queue S1 - Success, In-Progress, Failed
 - InProgressRequetsProcessor - S2
    -  Checking the status of in-progress requests.  - Success, Failed
 - FailedRequestsProcessor - S3
    - Retry mechanism - Failed, success, in-progress



