# media-metadata-service
A spring-boot application that consumes raw data media content meta-data from an external source and provides the data in the same format after applying required filters to it.
Java 8 or higher is required to execute the program.

How to compile and deploy the service:
----------------------------------------

The application can be executed in different ways.

1.	The easiest way to run the application is to execute the jar file bundled in the public repository.
Execute the jar from your command line using the below command
java -jar <path to jar>\ media-metadata-service-0.0.1.jar

2.	Download the source code and pom file to your local machine and execute from command line using the below maven command.
  
  mvn spring-boot:run
  
	Unit tests can be executed by running the below maven command
	
  mvn test
  
3.	Clone the repository to your favorite IDE and execute using IDE tools.

