/**
 * Part of the Strategy Behaviour pattern
 * Here is where the calculation of each type of tariff for the B service is made
 * in the end they return the value in the bucket that was determined
 */
class Beta1CalculationStrategy implements TariffCalculationStategy {
    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterB = request.RSU;
        boolean Roaming = request.roaming;

        if(!BillingAccount.IsWeekDay(request) && BillingAccount.IsNightHours(request) || BillingAccount.IsWeekDay(request)) {
            if (Roaming) {
                billingAccount.counterC += 0.2;
            }
            if (BillingAccount.IsNightHours(request)) {
                billingAccount.counterB += 0.05;
            }
            billingAccount.counterB += 0.1;

            if (billingAccount.counterA > 10) {
                billingAccount.counterB -= 0.025;
            }
            if (billingAccount.bucket3 > 50) {
                billingAccount.counterB -= 0.01;
            }
            if (Roaming) {
                billingAccount.bucket3 = billingAccount.counterC;
                if (billingAccount.counterB > 5) {
                    billingAccount.bucket2 = billingAccount.counterB;
                    System.out.println("Bucket 2 = "+billingAccount.bucket2);
                    return billingAccount.bucket2;
                }
                System.out.println("Bucket3 = "+ billingAccount.bucket3);
                return billingAccount.bucket3;
            }
            billingAccount.bucket1 = billingAccount.counterB;
            System.out.println("Bucket 1 = "+billingAccount.bucket1);
            return billingAccount.bucket1;
        }
        System.out.println("Error in Beta1");
        return 0;
    }
}

class Beta2CalculationStrategy implements TariffCalculationStategy {
    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterB = request.RSU;

        if(billingAccount.bucket2 > 10) {
            System.out.println("Invalid for beta2");
        }else{
            if(BillingAccount.IsNightHours(request)){
                billingAccount.counterB+=0.025;
            }
            billingAccount.counterB+=0.05;

            if(billingAccount.counterB > 10){
                billingAccount.counterB-=0.02;
            }
            if(billingAccount.bucket2 > 15){
                billingAccount.counterB-=0.05;
            }
            billingAccount.bucket2=billingAccount.counterB;
            System.out.println("Bucket 2 = "+billingAccount.bucket2);
            return billingAccount.bucket2;
        }
        System.out.println("Error in Beta 2");
        return 0;
    }
}

class Beta3CalculationStrategy implements TariffCalculationStategy {
    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterB = request.RSU;

        if(billingAccount.bucket3 > 10) {
            System.out.println("Invalid for beta3");
        }else{
            if(BillingAccount.IsNightHours(request)){
                billingAccount.counterB+=0.025;
            }
            billingAccount.counterB+=0.1;

            if(billingAccount.counterB > 10){
                billingAccount.counterB-=0.02;
            }
            if(billingAccount.bucket2 > 15){
                billingAccount.counterB-=0.05;
            }
            //apenas pode debitar no bucket3 ou rejeita
            System.out.println("Debit in bucketC");
            billingAccount.bucket3 = billingAccount.counterB;
            System.out.println("Bucket 3 = "+billingAccount.bucket3);
            return billingAccount.bucket3;
        }
        System.out.println("Error in Beta 3");
        return 0;
    }
}
