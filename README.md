# Altice-Challenge
Sistema de Faturamento e Cobrança em  Telecomunicações com Tarifários  Dinâmicos - Java

This project is made with the Strategy Behaviour Pattern. 


The project is the differet classes:

class AlphaCalculationStrategy -  * Here is where the calculation of each type of tariff for the A service is made
class BetaCalculationStrategy -  * Here is where the calculation of each type of tariff for the B service is made
abstract class BillingAccount - Here is where it will be calculated the type of Service that will be Used, and aux functions to help
class CalculationContext - Here is where the context for the calculation is, to define what the strategy is and in this case to calculate the tariff with the request and bill sent
class ChargingReply - To send inputs based on the status of Charging
class ChargingRequest - Where it`s created the request with the different type of information, 1 version for user input and another with pre determined info for test cases
class ClientDataRecor - Class that holds the main info about a client and its history. Main function is to list the information of the User
interface TariffCalculationStategy - Interface to implement the calculation of the diffenrent tariffs
class Test - Test function to try different tariff outcomes



FlowChart built to help in the creation of classes and their interactions

![fluxograma](https://github.com/SergioS2001/Altice-Challenge/assets/57481974/ee41d071-046c-4708-acf8-6f2b53c00a6d)


