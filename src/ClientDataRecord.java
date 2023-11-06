

import java.sql.Timestamp;

public class ClientDataRecord {
    Timestamp TimeStamp;
    String MSISDN;
    //verify
    int ServiceID;
    //verify
    String ChargingRequest;
    String ChargingReply;
    int Buckets;
    int Counters;

    String Tarifario;

    public ClientDataRecord(Timestamp timeStamp, String MSISDN, int serviceID, String chargingRequest, String chargingReply, int buckets, int counters, String tarifario) {
        TimeStamp = timeStamp;
        this.MSISDN = MSISDN;
        ServiceID = serviceID;
        ChargingRequest = chargingRequest;
        ChargingReply = chargingReply;
        Buckets = buckets;
        Counters = counters;
        Tarifario = tarifario;
    }

    public static void main(String[] args) {

    }

    public void PrintInfo(ClientDataRecord client){}
}
