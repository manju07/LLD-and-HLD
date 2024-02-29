Amazon - Generate Unique Identifier
Format: XXXX-XXXXX-XXXXXXXX

N1-N2-N3
XXXX-XXXXX-XXXXXXXX = 19

XXXX-XXXXX-XXXXXXXX

NUMBER-NUMBER-NUMBER
0000-0000-00000001
0000-0000-99999999
0000-0001-00000000
.
.
.
0000-0000-10000000


Range Based storing in DB
1 - 100000 - I1
100001 - 20000 - I2
20001 - 30000 - I3


Service:
    - Generate-ID-Service
    - Rest APIs
        - Generate new Identifier
            - URL: http://domain.com/api/v1/generate-id-service/
            - Http Method: Get
            - Headers:
                {
                    content-type: "application/json",
                    client: "instant details",
                    token: token
                }
            - Response: 
                {   
                    status: 200,
                    response: {
                        id: "XXXX-XXXXX-XXXXXXXX"
                    } 
                }
        - Get Range assigned for the instance
            -         

        - Last generated id from instance

{
    id: "",
    startingPoint: 1,
    endingPoint: 10000,
    lastGenerated: 201
}



public String generateUniqueIdentifier(String instanceDetails) {
    RangeData rangeData = db.get(instanceDetails);
    if(rangeData.getLastGenerated() >= rangeData.getEndingPoint()) {
        db.getLastRecordFromTable();
    }

    int data = rangeData.lastGenerated; 
} 