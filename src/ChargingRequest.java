import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ChargingRequest {
    String RequestID;
    Timestamp TimeStamp;
    //if its Tarifario A ou Tarifario B
    char Service;
    boolean Roaming;
    String MSISDN;
    int RSU;

    public ChargingRequest(String requestID, Timestamp timeStamp, char service, boolean roaming, String MSISDN, int RSU) {
        RequestID = requestID;
        TimeStamp = timeStamp;
        Service = service;
        Roaming = roaming;
        this.MSISDN = MSISDN;
        this.RSU = RSU;
    }

         public static ChargingRequest CreateNewChargingRequest(){

             Scanner scan = new Scanner(System.in);
             String RequestID;
             char Service;
             boolean Roaming;
             String PhoneNumber;
             int RSU;
             Date date;
             Timestamp Timestamp;

             // Generate a unique RequestID
             RequestID = UUID.randomUUID().toString();

             System.out.println("Please enter your phone number: ");
             PhoneNumber = scan.nextLine();

             System.out.println("Enter type of Service (A or B): ");
             Service = scan.next().charAt(0);

             System.out.println("How many Service Units?: ");
             RSU = scan.nextInt();

             System.out.println("Will you use roaming? (True or False): ");
             Roaming = scan.hasNextBoolean();

             Timestamp = new Timestamp(System.currentTimeMillis());

             System.out.println("Charging Request create successfully\n\n");

             return new ChargingRequest(RequestID, Timestamp, Service, Roaming, PhoneNumber, RSU);

         }


    public static void main(String[] args) {
        ChargingRequest newRequest = CreateNewChargingRequest();
       // System.out.println(newRequest.toString());
        System.out.println("New Charging Request created:");
        System.out.println("RequestID: " + newRequest.RequestID);
        System.out.println("Timestamp: " + newRequest.TimeStamp);
        System.out.println("Service: " + newRequest.Service);
        System.out.println("Roaming: " + newRequest.Roaming);
        System.out.println("MSISDN: " + newRequest.MSISDN);
        System.out.println("RSU: " + newRequest.RSU);
    }
}
