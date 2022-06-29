


class Wallet {
    id, amount, userId, createdBy, updatedBy, creatdAt, updatedAt

    public addMoneyToWallet(userId);
    public deductMoneyFromWallet(userId);
}

enum OTPStatus {
    SUCCESS, NOT_VERIFIED, EXPIRED;
}

class OTPValidation {
    id, walletId, userId, otp, otpStatus, duration, startTime, createdAt, updatedAt, createdBy, updatedBy
    public int generateOTP();   
    public Boolean validateOTP(otp);
}

enum TransactionStatus {
    INITIATED, FAILED, SUCCESS, IN_PROGRESS, OTP_VALIDATION; 
}

class Transaction {
    id, transactionStatus, userId, createdAt, updatedAt, createdBy, updatedBy 
}

class WalletHistory {
    id, walletId, transactionDetails, transactionStatus, amount, createdAt, updatedAt, createdBy, updatedBy 

    public Boolean addTransaction(walletId, body);
}