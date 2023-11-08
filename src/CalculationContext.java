/**
 * Part of the Strategy Behaviour pattern
 * Here is where the context for the calculation is
 * the main part is to define what the strategy is, in this case to calculate the tariff with the request and bill sent
 */
class CalculationContext {
    private final TariffCalculationStategy strategy;

    public CalculationContext(TariffCalculationStategy strategy) {
        this.strategy = strategy;
    }

    public int ExecuteCalculation(ChargingRequest request, BillingAccount billingAccount){
        return strategy.calculate(request, billingAccount);
    }
}
