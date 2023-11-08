public class ChargingReply {
    String requestID;
    String result;
    int GSU;

    public ChargingReply(String requestID, String result, int GSU) {
        this.requestID = requestID;
        this.result = result;
        this.GSU = GSU;
    }

    public static void main(String[] args) {

    }


    public void PrintResult(String result){
        switch (result) {
            case "OK" -> System.out.println("Result - OK");
            case "CreditLimitReached" -> System.out.println("Result - CreditLimitReached");
            case "NonElegible" -> System.out.println("Result - NonElegible");
            default -> System.out.println("Invalid Input");
        }
    }
}
