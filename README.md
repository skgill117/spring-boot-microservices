Overview
The Loan Eligibility and Disbursement Platform is designed to allow users to check their eligibility for loans and receive instant loan approval and disbursement with minimal delays (hops). The platform integrates services such as credit score validation, KYC verification, loan approval, and disbursement to ensure an efficient and seamless user experience.

The system is developed using Spring Boot and provides a RESTful API to interact with different components of the loan system, such as eligibility checks, credit score verification, and loan disbursement.


Technologies Used

Java 17
Spring Boot 3.x
Spring Security (for authentication and authorization)
Spring WebFlux (for reactive API processing)
MySQL/PostgreSQL (database for storing loan data)
RestTemplate/WebClient (for external API calls)


# Added Resilience4j for rate limiting and also implement circuit breaker using this library.



Prerequisites
Before you start, ensure you have the following installed:

Java 17
Maven 3.6+


Build the Project:
  mvn clean install


Run the Application:
  mvn spring-boot:run