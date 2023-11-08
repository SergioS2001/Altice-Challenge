# Altice-Challenge
Sistema de Faturamento e Cobrança em  Telecomunicações com Tarifários  Dinâmicos - Java

This project is made with the Strategy Behaviour Pattern. 


The project is the differet classes:

class AlphaCalculationStrategy -  Here is where the calculation of each type of tariff for the A service is made


class BetaCalculationStrategy -   Here is where the calculation of each type of tariff for the B service is made


abstract class BillingAccount - Here is where it will be calculated the type of Service that will be Used, and aux functions to help


class CalculationContext - Here is where the context for the calculation is, to define what the strategy is and in this case to calculate the tariff with the request and bill sent


class ChargingReply - To send inputs based on the status of Charging


class ChargingRequest - Where it`s created the request with the different type of information, 1 version for user input and another with pre determined info for test cases


class ClientDataRecor - Class that holds the main info about a client and its history. Main function is to list the information of the User


interface TariffCalculationStategy - Interface to implement the calculation of the diffenrent tariffs


class Test - Test function to try different tariff outcomes



FlowChart built to help in the creation of classes and their interactions

![fluxograma](https://github.com/SergioS2001/Altice-Challenge/assets/57481974/ee41d071-046c-4708-acf8-6f2b53c00a6d)




### Charging Request Flow

1. **Create Charging Request:**  
   The process begins with the creation of a charging request.

2. **Billing Account Evaluation:**  
   The charging request is sent to the billing account, which evaluates the request and determines the type of service that will be used based on the user's account and request details.

3. **Tariff Calculation:**  
   The chosen billing strategy takes into account the request information, including factors like roaming status, balance in the user's account, and service-specific criteria. It calculates which tariff to use and how much will be charged.

4. **Charging Response:**
   - If the calculation is successful and the user is eligible, the charging reply will be "OK," indicating that the requested service has been charged correctly.
   - If the user is not eligible or an error occurs during the calculation, the charging reply will be "Non-Eligible."

5. **Client Data Recording:**  
   The result, whether "OK" or "Non-Eligible," is added to the client's data record. This record can include user information and the outcome of the charging request.


