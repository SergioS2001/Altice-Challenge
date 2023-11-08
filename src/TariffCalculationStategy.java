/**
 * Applied to implement the Strategy Design Pattern
 * Interface to implement the calculation of the diffenrent tariffs
 * Later can be implement more functions to deal with new features
 */
interface TariffCalculationStategy {
    int calculate(ChargingRequest request, BillingAccount billingAccount);
}
