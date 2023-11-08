import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Test Class to determine the different possible outcomes for the tariff
 */
public class Test {
    public static void main(String[] args) {
        // Create a BillingAccount instance for testing
        BillingAccount billingAccount = new BillingAccount("123456", 10, 10, 10, 10, 0, 0, new Date()) {
            @Override
            public int calculate(ChargingRequest request, BillingAccount billingAccount) {
                return 0;
            }
        };

        BillingAccount billingAccount2 = new BillingAccount("123456", 100, 1, 120, 35, 50, 20, new Date()) {
            @Override
            public int calculate(ChargingRequest request, BillingAccount billingAccount) {
                return 0;
            }
        };

        Timestamp t1 = java.sql.Timestamp.valueOf("2023-09-23 12:10:10.0");
        Timestamp t2 = java.sql.Timestamp.valueOf("2007-09-08 20:10:10.0");
        Timestamp t3 = java.sql.Timestamp.valueOf("2012-12-23 05:10:10.0");
        Timestamp t4 = java.sql.Timestamp.valueOf("2022-11-23 19:10:10.0");
        Timestamp t5 = java.sql.Timestamp.valueOf("2023-11-10 22:10:10.0");
        Timestamp t6 = java.sql.Timestamp.valueOf("2023-11-11 23:10:10.0");



        // Create different ChargingRequest instances for testing
        ChargingRequest request1 = ChargingRequest.CreateNewChargingRequestByInput('A', false, "987654", 30, t1);
        ChargingRequest request2 = ChargingRequest.CreateNewChargingRequestByInput('A', false, "111111", 70, t2);
        ChargingRequest request3 = ChargingRequest.CreateNewChargingRequestByInput('A', true, "222222", 20, t3);
        ChargingRequest request4 = ChargingRequest.CreateNewChargingRequestByInput('B', false, "333333", 40, t4);
        ChargingRequest request5 = ChargingRequest.CreateNewChargingRequestByInput('B', true, "444444", 300, t5);
        ChargingRequest request6 = ChargingRequest.CreateNewChargingRequestByInput('B', false, "444444", 1, t6);


        // Test different scenarios
        BillingAccount.VerificationOfService(request1, billingAccount);
        BillingAccount.VerificationOfService(request2, billingAccount);
        BillingAccount.VerificationOfService(request3, billingAccount);
        BillingAccount.VerificationOfService(request4, billingAccount);
        BillingAccount.VerificationOfService(request5, billingAccount);
        BillingAccount.VerificationOfService(request6, billingAccount);

        // Test different scenarios
        BillingAccount.VerificationOfService(request1, billingAccount2);
        BillingAccount.VerificationOfService(request2, billingAccount2);
        BillingAccount.VerificationOfService(request3, billingAccount2);
        BillingAccount.VerificationOfService(request4, billingAccount2);
        BillingAccount.VerificationOfService(request5, billingAccount2);
        BillingAccount.VerificationOfService(request6, billingAccount2);

    }
}
