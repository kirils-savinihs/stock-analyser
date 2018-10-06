# stock-analyser

This project gathers, sorts and sends information about stocks and then sends it to clients via email. The information provided by this service allows clients to decide whether they want to buy or sell stocks. This service is supposed to be a daily information provider to clients that are interested in it.

## Getting Started

Clone or download this repository to your device. Open the project in IntelliJ Idea, Eclipse or other IDE for Java.

### Prerequisites

1) MySQL Community Server 8.0.12 (MySQL Workbench recommended)

2) Java 8

3) Create a table with clients in database

```
Create database database_activity with a table Clients which has a 
column named eMail where you have stored Client’s e-mails.
Then store some e-mail addresses in it.
```

3)Add your mysql root password to the constructor of StockSorting class

```
public StockSorting() {
		//put my password
		this.database = new DatabaseManager("abcd1234");
		this.companyList = database.getAll();
	}
```

## Project's classes

This project is split into four parts (data gathering, storing, sorting, sending) and uses 4 classes that are in charge of each.

### CompanyStockData & DatabaseManager

CompanyStockData & DatabaseManager are responsible for getting stock data using IEX Trading API and then storing it in a local MySQL database via JDBC.

### StockSorting

This class is responsible for sorting stocks by PE Ratio, Price to sales ratio etc for JavaMail class

### JavaMail

This class is responsible for front end to make sure that the calculated information is send to client’s e-mail in a 
pleasant user interface. To do so this class uses MySQL and Java Mail API and CSS.

This class is responsible for sending the sorted stock info to the clients' e-mail inboxes. This class uses MySQL and Java Mail API and CSS.


## Running the tests

This project uses Junit tests to test the program for errors.

### Break down into end to end tests

* CompanyStockData & DatabaseManager test methods

```
//CompanyStockData.java:
//Checks constructors for CompanyStockData
constructors()
//Checks toString method
ToStringTest()
//Checks compareTo method
comparetTo()

//DatabaseManager.java:
//resets DatabaseManager obj
setUp()
//closes jdbc connection
tearDown()
//tests connection to mysql
connect()
//tests adding to database
add()
//tests reseting the database
resetDatabase()
//tests closing the connection
closeConnection()
//tests getAll method that gets all info from the database
getAll()


```

* StockSorting test methods
// tests sorting list of stocks by sectors
testSectorSorting()
// tests sorting passed list of stocks by passed ratios
testGetBy()
// tests if method returns final sorted list of stocks for Financial Services	
testFinalFinancialSorting()
// tests if method returns final sorted list of stocks for Utilities
testFinalUtilitiesSorting()
// tests if method returns final sorted list of stocks for Consumer Defensive
testFinalConsumersSorting() 
	

* JavaMail test methods

```
//Checks that exceptions in code are done properly
test00Exceptions()
//Checks if data of recipients is gathered and can connect to databse
test01FormSQL()
Checks if data is sent with correct recipients 
test02SendMessageTRUE()
Checks if data is not sent with false recipients
test03SendMessageFalse()
Checks if data is not sent with empty recipients
test04SendMessageEmpty()
```

## Deployment

In StockSorting main method you can comment out ss.database.resetDatabase and database.add when you don’t need to update
the database.

```
   ss.database.resetDatabase();
		System.out.println("Adding companies to database");
		ss.database.add("AES", "LNT", "AEE", "AEP", "AWK", "CNP", "CMS", "ED", "D", "DTE", "DUK", "EIX", "ETR",
                "ES", "EXC", "NEE", "NI", "NRG", "PCG", //Utilities
				"AMG", "AFL", "ALL", "AXP", "AIG", "AMP", "AON", "AJG", "AIZ", "BAC", "BK", "BBT", "BRK.B", "BLK",
				"BHF", "COF", "CBOE", "SCHW", "CB", // Financials
                "MO", "ADM", "BF.B", "CPB", "CHD", "CLX", "KO", "CL", "CAG", "STZ", "CAG", "STZ", "COST",
                "COTY", "EL", "GIS", "HSY", "HRL", "GIS" // Consumers
		);
```

There is a logMain.out file which stores information about send e-mails. So you can keep track of systems (un)succesful runs. 
This information keeps the date and time of when this program sends emails.

```
2018-10-05 20:11:24 INFO  JavaMail:154 - Recipient formed.
2018-10-05 20:11:26 INFO  JavaMail:184 - Sent message successfully....
2018-10-05 20:11:26 INFO  JavaMail:154 - Recipient formed.
2018-10-05 20:11:28 INFO  JavaMail:184 - Sent message successfully....
2018-10-05 20:11:28 INFO  JavaMail:154 - Recipient formed.
2018-10-05 20:11:29 INFO  JavaMail:184 - Sent message successfully....
2018-10-05 20:14:54 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:14:57 INFO  JavaMail:178 - Sent message successfully....
2018-10-05 20:14:57 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:14:58 INFO  JavaMail:178 - Sent message successfully....
2018-10-05 20:14:58 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:14:59 INFO  JavaMail:178 - Sent message successfully....
2018-10-05 20:16:42 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:16:44 INFO  JavaMail:177 - Sent message successfully....
2018-10-05 20:16:44 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:16:46 INFO  JavaMail:177 - Sent message successfully....
2018-10-05 20:16:46 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:16:48 INFO  JavaMail:177 - Sent message successfully....
2018-10-05 20:19:13 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:19:16 INFO  JavaMail:177 - Sent message successfully....
2018-10-05 20:19:16 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:19:18 INFO  JavaMail:177 - Sent message successfully....
2018-10-05 20:19:18 INFO  JavaMail:148 - Recipient formed.
2018-10-05 20:19:19 INFO  JavaMail:177 - Sent message successfully....
```


## Built With

* **Maven**
* **JavaMail API**
* **MySQL**
* **IEX Trading API**

## Versioning

This projects development was controlled through gitHub in our created repository StockAnalyser where you can access it’s files. 
During this project there were 2 branched where we committed files.

* **Master** - Commited for back end and data analysis
* **FrontEnd** - Commited for JavaMail development

Right now they have been merged in master branch.


## Authors

* **Kirils Savinihs** - *Initial work* - [CompanyStockData & DatabaseManager and it's tests]
* **Anastasija Malaja** - *Initial work* - [StockSorting and it's tests]
* **Ingus Pallo** - *Initial work* - [JavaMail and it's tests]




