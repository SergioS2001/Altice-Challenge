import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;

public class BillingAccount{
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
        super();
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


    /**2 isso vai passar para a billing account, recebe essa instancia e vai fazer as suas verificacoes e ver em que tipo de servico, dentro do A ou B ele vai ficar**/

     public void VerificationOfService(ChargingRequest request){
         char Service = request.Service;

         if (Service == 'A'){
             ServiceA(request);
         }
         else{
             ServiceB(request);
         }
     }

    /**
     *
     * counterA - contagem de SU's do Serviço A;
     * o counterB - contagem do numero de pedidos do Serviço B, sob tarifário Beta1;
     * o counterC- contagem de requisições em roaming
     * o counterD- registro da data da ultima requisição feita
     */

     public int ServiceA(ChargingRequest request){
         String MSISND = request.MSISDN;
         CounterA = request.RSU;
         Timestamp time = request.TimeStamp;
         LocalDateTime LocalTime = time.toLocalDateTime();
         int DayOfWeek = LocalTime.getDayOfWeek().getValue();
         int Hours = LocalTime.getHour();
         boolean Roaming = request.Roaming;

         //alpha 1
         if(CounterA < 100){
             //if para verificar que so da aos dias da semana
             if(DayOfWeek >= 1 && DayOfWeek<=5){

                 if (Roaming){
                     CounterC+=2;
                 }
                 if (Hours >= 20 || Hours <= 6){
                     CounterA +=0.5;
                 }

                 CounterA += 1;

                 if(CounterA < 10){
                     CounterA-=0.25;
                 }
                 if (CounterC<50){
                     CounterC -=0.1;
                 }

                 System.out.println("The type of Service is Alpha 1");
                 System.out.println("The bucket that will be charged");

                  if (Roaming){
                      System.out.println("Bucket B");
                  }
                  else if(Roaming && CounterC > 5){
                      System.out.println("Bucket B");
                  }
                  else {
                      System.out.println("Bucket A");
                  }
             }
         }
         //alpha2
         if(Bucket2 < 10 || Roaming){
             System.out.println("Invalid for Alpha 2");
             return -1;
         }
         else{
             if (Hours >= 20 || Hours <= 6){
                 CounterA +=0.25;
             }
             if (CounterB > 10){
                 CounterA -=0.5;
             }
             if (Bucket2 > 15){
                 CounterA -=0.05;
             }
             CounterA+=0.5;
             //verificar como posso fazer para que o pagamento seja apenas no bucket2
             System.out.println("Value charged in Bucket2");
         }

         //alpha3
         if(Roaming && Bucket3 > 10){

             if(DayOfWeek > 5 && DayOfWeek < 7){
                 CounterA += 0.25;
             }
             if(CounterB > 10){
                 CounterA -=0.2;
             }
             if(CounterB > 15){
                 CounterA -=0.05;
             }
             //pagamento apenas no bucket3
         }

         //retornar o pagamento
         return 0;
     }


    /**
     *
     * counterA - contagem de SU's do Serviço A;
     * o counterB - contagem do numero de pedidos do Serviço B, sob tarifário Beta1;
     * o counterC- contagem de requisições em roaming
     * o counterD- registro da data da ultima requisição feita
     */


     public int ServiceB(ChargingRequest request){
         String MSISND = request.MSISDN;
         CounterA = request.RSU;
         Timestamp time = request.TimeStamp;
         LocalDateTime LocalTime = time.toLocalDateTime();
         int DayOfWeek = LocalTime.getDayOfWeek().getValue();
         int Hours = LocalTime.getHour();
         boolean Roaming = request.Roaming;

         //beta 1
         if(DayOfWeek >= 1 && DayOfWeek<=5 || DayOfWeek >= 6 && Hours >=20 && Hours <= 6)
         {

             if (Roaming){
                 CounterC+=0.2;
             }
             if(Hours >= 20 && Hours <= 6){
                 CounterB+=0.05;
             }
             CounterB+=0.1;

             if(CounterA > 10){
                 CounterB-=0.025;
             }
             if(Bucket3 > 50){
                 CounterB-=0.01;
             }
             if (Roaming){
                 System.out.println("Debit in bucket3");
             }
             if(Roaming && CounterB>5){
                 System.out.println("Debit in bucket2");
             }
             System.out.println("Debit in bucketA");
         }

         //beta2
         if(Roaming || Bucket2 > 10) {
             System.out.println("Invalid for beta2");
         }else{

             if(Hours >= 20 && Hours <= 6){
                 CounterB+=0.025;
             }
             CounterB+=0.05;

             if(CounterB > 10){
                 CounterB-=0.02;
             }
             if(Bucket2 > 15){
                 CounterB-=0.05;
             }
             //apenas pode debitar no bucket2 ou rejeita
             System.out.println("Debit in bucketB");
         }

         //beta3
         if(Roaming == false || Bucket3 > 10) {
             System.out.println("Invalid for beta2");
         }else{

             if(Hours >= 20 && Hours <= 6){
                 CounterB+=0.025;
             }
             CounterB+=0.1;

             if(CounterB > 10){
                 CounterB-=0.02;
             }
             if(Bucket2 > 15){
                 CounterB-=0.05;
             }
             //apenas pode debitar no bucket3 ou rejeita
             System.out.println("Debit in bucketC");
         }

        //no fim retornar um valor de pagamento
        return 0;
     }

    public static void main(String[] args) {

    }
}
