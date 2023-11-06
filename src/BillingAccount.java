import java.util.Date;

public class BillingAccount {
    String MSISDN;
    int Bucket1;
    int Bucket2;
    int Bucket3;

    int CounterA;
    int CounterB;
    int CounterC;
    Date CounterD;
    String [] TarifarioServicoA = {"Alpha1", "Alpha2", "Alpha3"};
    String [] TarifarioServicoB = {"Beta1", "Beta2", "Beta3"};

    public BillingAccount(String MSISDN, int bucket1, int bucket2, int bucket3, int counterA, int counterB, int counterC, Date counterD) {
        this.MSISDN = MSISDN;
        Bucket1 = bucket1;
        Bucket2 = bucket2;
        Bucket3 = bucket3;
        CounterA = counterA;
        CounterB = counterB;
        CounterC = counterC;
        CounterD = counterD;
      //  TarifarioServicoA = tarifarioServicoA;
      //  TarifarioServicoB = tarifarioServicoB;
    }

    public static void main(String[] args) {

    }

    /**2 isso vai passar para a billing account, recebe essa instancia e vai fazer as suas verificacoes e ver em que tipo de servico, dentro do A ou B ele vai ficar**/

     public void VerificationOfService(ChargingRequest request){}


}
