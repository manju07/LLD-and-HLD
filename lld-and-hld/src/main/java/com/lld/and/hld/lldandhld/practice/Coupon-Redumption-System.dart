Problem Statement

Design the backend components and APIs for implementing the coupons service.

Functional Requirements

- We want to give customers coupons, that entitle them to some discount. Each physical coupon can potentially be used a number of times,
 but we need to restrict usage so that they cannot be used more than their maximum limits.

- The challenge is to design a system that tracks the usage of these coupons and ensures that each one cannot be used more times than is allowed.

- You should assume that we already have the mechanisms to know which customers have been given which coupons. 
We are not interested in the actual reward that the coupon gives the customer - only in tracking redemption.

Database: 
    -  CouponRedemption
    Collections:
        - coupon
            {
                id: 432423,
                coupon: "DASES224",
                desc: "fsdfsgsd sdgs"
                amount: 100,
                "createdTime": timestamp,
                "createdTime": timestamp,
            }

        - customer
            {
                id: 4234234,
                // name, address, phone, email
            }

        - coupon_customer 
            {
                id: 523545,
                customerId: 5345,
                couponId: 5435,
                usageCount: 1,
                couponLimit: 10
            }

        - coupon_applied_history
            {
                id: 543534
                couponCustomerId: 234,
                createTime: timestamp,
                updatedTime: timestamp
            }


Service: coupon-redemption-service

Customer APIs:
- validate coupon
    - URL  https://coupon-redemption.com/coupon-redemption-service/api/v1/coupon/validate
    - Http method GET
    - Header couponToken=53453453rfsdgsdfgsdfg
    - Body as json format
     {
        coupon: "REDF"
     }

    - Response
    {
        status: 200,
        message: true/false
    }

- apply coupon
    - URL  /coupon-redemption-service/api/v1/coupon/apply
    - Http method GET
    - Header couponToken=53453453rfsdgsdfgsdfg
    - Body as json format
     {
        coupon: "REDF"
     }
    - Response
    {
        status: 200,
        message: true/false
    }