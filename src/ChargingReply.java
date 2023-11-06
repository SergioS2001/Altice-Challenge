public class ChargingReply {
    String RequestID;
    String Result;
    int GSU;

    public ChargingReply(String requestID, String result, int GSU) {
        RequestID = requestID;
        Result = result;
        this.GSU = GSU;
    }

    public static void main(String[] args) {

    }

    public void ValueToPay(BillingAccount bill){}

    public void PrintResult(String Result){
        switch (Result) {
            case "OK" -> System.out.println("Result - OK");
            case "CreditLimitReached" -> System.out.println("Result - CreditLimitReached");
            case "NonElegible" -> System.out.println("Result - NonElegible");
            default -> System.out.println("Invalid Input");
        }
    }
}
