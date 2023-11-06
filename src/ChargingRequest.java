import java.sql.Timestamp;

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

    public static void main(String[] args) {
        /**1 criar uma instancia de uma charging request**/


         }

}
