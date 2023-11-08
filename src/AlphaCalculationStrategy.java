/**
 * Part of the Strategy Behaviour pattern
 * Here is where the calculation of each type of tariff for the A service is made
 * in the end they return the value in the bucket that was determined
 */
class Alpha1CalculationStrategy implements TariffCalculationStategy {

    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterA = request.RSU;
        boolean Roaming = request.roaming;

        if(billingAccount.counterA < 100){
            if(BillingAccount.IsWeekDay(request)){

                if (Roaming){
                    billingAccount.counterC+=2;
                }
                if (BillingAccount.IsNightHours(request)){
                    billingAccount.counterA +=0.5;
                }

                billingAccount.counterA += 1;

                if(billingAccount.counterA > 10){
                    billingAccount.counterA-=0.25;
                }
                if (billingAccount.counterC>50){
                    billingAccount.counterC -=0.1;
                }
                System.out.println("The type of Service is Alpha 1");
                System.out.println("The bucket that will be charged");

                if (Roaming) {
                    billingAccount.bucket3 = billingAccount.counterC;

                    if (billingAccount.counterC > 5) {
                        billingAccount.bucket2 = billingAccount.counterC;
                        System.out.println("Bucket 2 = "+billingAccount.bucket2);
                        return billingAccount.bucket2;
                    }
                    System.out.println("Bucket 3 = "+billingAccount.bucket3);
                    return billingAccount.bucket3;
                }
                billingAccount.bucket1=billingAccount.counterA;
                System.out.println("Bucket 1 = "+ billingAccount.bucket1);
                return billingAccount.bucket1;
            }
        }
        System.out.println("Alpha 1 - Error in Tariff");
        return 0;
    }
}

class Alpha2CalculationStrategy implements TariffCalculationStategy {
    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterA = request.RSU;
        boolean Roaming = request.roaming;

        if(billingAccount.bucket2 < 10 || Roaming){
            System.out.println("Invalid for Alpha 2");
            return -1;
        }
        if (BillingAccount.IsNightHours(request)){
            billingAccount.counterA +=0.25;
        }
        if (billingAccount.counterB > 10){
            billingAccount.counterA -=0.5;
        }
        if (billingAccount.bucket2 > 15){
            billingAccount.counterA -=0.05;
        }
        billingAccount.counterA+=0.5;
        billingAccount.bucket2 = billingAccount.counterA;
        System.out.println("Bucket 2 = "+billingAccount.bucket2);
        return billingAccount.bucket2;
    }
}

class Alpha3CalculationStrategy implements TariffCalculationStategy {
    @Override
    public int calculate(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterA = request.RSU;

        if(billingAccount.bucket3 > 10){
            if(!BillingAccount.IsWeekDay(request)){
                billingAccount.counterA += 0.25;
            }
            if(billingAccount.counterB > 10){
                billingAccount.counterA -=0.2;
            }
            if(billingAccount.counterB > 15){
                billingAccount.counterA -=0.05;
            }
            billingAccount.bucket3 = billingAccount.counterA;
            System.out.println("Bucket 3 = "+billingAccount.bucket3);
            return billingAccount.bucket3;
        }
        System.out.println("Error in Alpha 3 Tariff");
        return 0;
    }
}

