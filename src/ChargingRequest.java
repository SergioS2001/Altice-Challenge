import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


public class ChargingRequest {
    String requestID;
    Timestamp timeStamp;
    //if its Tarifario A ou Tarifario B
    char service;
    boolean roaming;
    String MSISDN;
    int RSU;

    public ChargingRequest(String requestID, Timestamp timeStamp, char service, boolean roaming, String MSISDN, int RSU) {
        this.requestID = requestID;
        this.timeStamp = timeStamp;
        this.service = service;
        this.roaming = roaming;
        this.MSISDN = MSISDN;
        this.RSU = RSU;
    }

    /**
     * Function to create a new charging request, by User input
     * In the end create a new instance of the class with the determined info
     * The requestID is a Unique Request Identifier
     * @return
     */
    public static ChargingRequest CreateNewChargingRequest(){

             Scanner scan = new Scanner(System.in);
             String requestID;
             char service;
             boolean roaming;
             String phoneNumber;
             int RSU;
             Timestamp timestamp;

             // Generate a unique RequestID
             requestID = UUID.randomUUID().toString();

             System.out.println("Please enter your phone number: ");
             phoneNumber = scan.nextLine();

             System.out.println("Enter type of Service (A or B): ");
             service = scan.next().charAt(0);

             System.out.println("How many Service Units?: ");
             RSU = scan.nextInt();

             System.out.println("Will you use roaming? (True or False): ");
             roaming = scan.hasNextBoolean();

             timestamp = new Timestamp(System.currentTimeMillis());

             System.out.println("Charging Request create successfully\n\n");

             return new ChargingRequest(requestID, timestamp, service, roaming, phoneNumber, RSU);

         }

    /**
     * Similar function to the above but with pre defined info, besides timestamp
     * @param Service
     * @param roaming
     * @param MSISDN
     * @param RSU
     * @return
     */
    public static ChargingRequest CreateNewChargingRequestByInput(char Service, boolean roaming, String MSISDN, int RSU, Timestamp timeStamp){
        String RequestID;
        RequestID = UUID.randomUUID().toString();
        System.out.println("Charging Request create successfully\n\n");

        return new ChargingRequest(RequestID, timeStamp, Service, roaming, MSISDN, RSU);
    }



        public static void main(String[] args) {
        ChargingRequest newRequest = CreateNewChargingRequest();
       // System.out.println(newRequest.toString());
        System.out.println("New Charging Request created:");
        System.out.println("RequestID: " + newRequest.requestID);
        System.out.println("Timestamp: " + newRequest.timeStamp);
        System.out.println("Service: " + newRequest.service);
        System.out.println("Roaming: " + newRequest.roaming);
        System.out.println("MSISDN: " + newRequest.MSISDN);
        System.out.println("RSU: " + newRequest.RSU);
    }
}
