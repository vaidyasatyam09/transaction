# TransactionsStatistics
This is maven based Spring-boot application which has a transaction API to post transaction, which will be input for this application.
Also a statistics API which will return the statistics of the transaction recieved with timestamp within last 60 seconds.

1.POST : /transactions : for each new transaction a new object is added in the PriorityQueue where comparator is provided to compare based on timestamp of transaction.

2.GET : /statistics : This will give the aggregated statistics of all timestamps present in the PriorityQueue. Statistics object will be returned which has sum,min,max,count and avg of the last 60 seconds of the transactions.

DataStructure used in the application is PriorityQueue to make it easy to remove the transaction from the queue which are of timestamp older than 60sec.

Running Test Cases :

1.TransactionControllerTest.java : Run all the test cases
2.TransactionServiceTest : Run all the test cases

Steps to deploy and run:
mvn spring-boot:run -Dserver.port=
