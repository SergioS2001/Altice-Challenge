

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.UUID;

/**
 * Class that holds the main info about a client and its history
 * Main function is to list the information of the User
 * And create a new user with the determined info
 */
public class ClientDataRecord extends ChargingRequest {
    Timestamp timeStamp;
    String MSISDN;
    //verify
    char serviceID;
    //verify
    ChargingRequest chargingRequest;
    ChargingReply chargingReply;
    int buckets;
    int counters;

    String tarifario;

    public ClientDataRecord(Timestamp timeStamp, String MSISDN, char serviceID, ChargingRequest chargingRequest, ChargingReply chargingReply, int buckets, int counters, String tarifario) {
        super(chargingRequest.requestID, timeStamp, serviceID, chargingRequest.roaming, MSISDN, chargingRequest.RSU);
        this.timeStamp = timeStamp;
        this.MSISDN = MSISDN;
        this.serviceID = serviceID;
        this.chargingRequest = chargingRequest;
        this.chargingReply = chargingReply;
        this.buckets = buckets;
        this.counters = counters;
        this.tarifario = tarifario;
    }

    public static ClientDataRecord CreateNewClient(){

        Scanner scan = new Scanner(System.in);

        Timestamp timestamp;

        System.out.println("Please enter your phone number: ");
        String phoneNumber = scan.nextLine();

        System.out.println("Enter type of Service (A or B): ");
        char service = scan.next().charAt(0);

        timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println("Client Request create successfully\n\n");

        return new ClientDataRecord(timestamp, phoneNumber, service, CreateNewClient().chargingRequest, CreateNewClient().chargingReply, ClientDataRecord.CreateNewClient().buckets, ClientDataRecord.CreateNewClient().counters, ClientDataRecord.CreateNewClient().tarifario);
    }

    public static void main(String[] args) {
        ClientDataRecord newClient = CreateNewClient();
        System.out.println(newClient.MSISDN);
        System.out.println(newClient.RSU);
        System.out.println(newClient.service);
        System.out.println(newClient.roaming);
        System.out.println(newClient.buckets);
        System.out.println(newClient.counters);
        System.out.println(newClient.tarifario);
        System.out.println(newClient.timeStamp);
        System.out.println(newClient.requestID);
    }


}
