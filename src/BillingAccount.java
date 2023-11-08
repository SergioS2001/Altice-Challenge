import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;

public abstract class BillingAccount implements TariffCalculationStategy {
    String MSISDN;
    int bucket1;
    int bucket2;
    int bucket3;

    int counterA; //contagem de SU's do Serviço A
    int counterB; //contagem do numero de pedidos do Serviço B, sob tarifário Beta1
    int counterC;//contagem de requisições em roaming
    Date counterD;//registro da data da ultima requisição feita

    String[] tarifarioServicoA = {"Alpha1", "Alpha2", "Alpha3"};

    String[] tarifarioServicoB = {"Beta1", "Beta2", "Beta3"};

    /**Strategy Configuration**/
    static TariffCalculationStategy alpha1Strategy = new Alpha1CalculationStrategy();
    static CalculationContext contextA1 = new CalculationContext(alpha1Strategy);
    static TariffCalculationStategy alpha2Strategy = new Alpha2CalculationStrategy();
    static CalculationContext contextA2 = new CalculationContext(alpha2Strategy);
    static TariffCalculationStategy alpha3Strategy = new Alpha3CalculationStrategy();
    static CalculationContext contextA3 = new CalculationContext(alpha3Strategy);

    static TariffCalculationStategy beta1Strategy = new Beta1CalculationStrategy();
    static CalculationContext contextB1 = new CalculationContext(beta1Strategy);
    static TariffCalculationStategy beta2Strategy = new Beta2CalculationStrategy();
    static CalculationContext contextB2 = new CalculationContext(beta2Strategy);
    static TariffCalculationStategy beta3Strategy = new Beta3CalculationStrategy();
    static CalculationContext contextB3 = new CalculationContext(beta3Strategy);


    public BillingAccount(String MSISDN, int bucket1, int bucket2, int bucket3, int counterA, int counterB, int counterC, Date counterD) {
        this.MSISDN = MSISDN;
        this.bucket1 = bucket1;
        this.bucket2 = bucket2;
        this.bucket3 = bucket3;
        this.counterA = counterA;
        this.counterB = counterB;
        this.counterC = counterC;
        this.counterD = counterD;

    }

    /**
     * Verify which Service was selected by the User
     * @param request
     * @param billingAccount
     */
    public static void VerificationOfService(ChargingRequest request, BillingAccount billingAccount) {
        char Service = request.service;
        System.out.println(Service);
       if (Service == 'A') {
           ServiceA(request, billingAccount);
       }
       else if(Service == 'B') {
           ServiceB(request, billingAccount);
       }
    }

    /**
     * Inisde Service A, it will decide which Tariff is eligable and the various constraints
     * Switch Case to decide which strategy will be implemented
     * @param request
     * @param billingAccount
     */
    public static void ServiceA(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterA = request.RSU;
        boolean roaming = request.roaming;
        String tariff = "";

        // Determine which tariff to use
        if (!roaming && billingAccount.bucket2 > 10) {
            tariff = billingAccount.tarifarioServicoA[1]; // Alpha2
        } if (IsWeekDay(request) && billingAccount.counterA < 100) {
            tariff = billingAccount.tarifarioServicoA[0]; // Alpha1
        } else if (roaming && billingAccount.bucket3 > 10) {
            tariff = billingAccount.tarifarioServicoA[2]; // Alpha3
        }

        // Depending on the chosen tariff, execute the corresponding method
        switch (tariff) {
            case "Alpha1" -> {
                System.out.println("Entered alfa1");
                contextA1.ExecuteCalculation(request, billingAccount);
            }
            case "Alpha2" -> {
                System.out.println("Entered alfa2");
                contextA2.ExecuteCalculation(request, billingAccount);
            }
            case "Alpha3" -> {
                System.out.println("Entered alfa3");
                contextA3.ExecuteCalculation(request, billingAccount);
            }
            default -> System.out.println("ServiceA -Error  in Alpha");
        }
    }


    /**
     * Inisde Service B, it will decide which Tariff is eligable and the various constraints
     * Switch Case to decide which strategy will be implemented
     * @param request
     * @param billingAccount
     */
    public static void ServiceB(ChargingRequest request, BillingAccount billingAccount) {
        billingAccount.counterA = request.RSU;
        boolean roaming = request.roaming;
        String tariff = "";

        // Determine which tariff to use
        if (IsWeekDay(request) && IsNightHours(request)) {
            tariff = billingAccount.tarifarioServicoB[0]; // Beta1
        } else   if (!roaming) {
            tariff = billingAccount.tarifarioServicoB[1]; // Beta2
        } else{
            tariff = billingAccount.tarifarioServicoB[2]; // Beta3
        }


        // Depending on the chosen tariff, execute the corresponding method
        switch (tariff) {
            case "Beta1" -> {
                System.out.println("Entered Beta1");
                contextB1.ExecuteCalculation(request, billingAccount);
            }
            case "Beta2" -> {
                System.out.println("Entered Beta2");
                contextB2.ExecuteCalculation(request, billingAccount);
            }
            case "Beta3" -> {
                System.out.println("Entered Beta3");
                contextB3.ExecuteCalculation(request, billingAccount);
            }
            default -> System.out.println("ServiceB -Error in Beta");
        }
    }

    /**
     * Aux Function to help in the constraints in each Service
     * Checks if the timestamp is at night time, after 8pm until 6am
     * @param request
     * @return
     */
    public static boolean IsNightHours(ChargingRequest request) {
        Timestamp time = request.timeStamp;
        LocalDateTime LocalTime = time.toLocalDateTime();
        int Hours = LocalTime.getHour();
        return Hours >= 20 && Hours <= 6;
    }

    /**
     * Aux Function to help in the constraints in each Service
     * Checks if the timestamp is on a Week Day or Weekend
     * @param request
     * @return
     */
    public static boolean IsWeekDay(ChargingRequest request) {
        Timestamp time = request.timeStamp;
        LocalDateTime LocalTime = time.toLocalDateTime();
        int DayOfWeek = LocalTime.getDayOfWeek().getValue();
        return DayOfWeek >= 1 && DayOfWeek <= 5;
    }


    public static class CreateBillingAccount extends BillingAccount {
        public CreateBillingAccount(String MSISDN, int bucket1, int bucket2, int bucket3, int counterA, int counterB, int counterC, Date counterD) {
            super(MSISDN, bucket1, bucket2, bucket3, counterA, counterB, counterC, counterD);
        }

        public static void main(String[] args) {
            ChargingRequest request = ChargingRequest.CreateNewChargingRequest();
            CreateBillingAccount billingAccount = new CreateBillingAccount("123456", 10, 10, 10, 10, 0, 0, request.timeStamp);
            VerificationOfService(request, billingAccount);
        }

        @Override
        public int calculate(ChargingRequest request, BillingAccount billingAccount) {
            return 0;
        }
    }
}
