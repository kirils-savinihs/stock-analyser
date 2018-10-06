# stock-analyser

This project gathers, calculates and sends information about stocks and then gives it to various clients via their e-mails. 
The information provided by this service allows clients to decide whether they want to invest or sell stocks. 
This service is supposed to be a daily information provider to clients that are interested in it.

## Getting Started

Clone or download this repository to your device. Open the project in IntelliJ Idea, Eclipse or other java editing program.

### Prerequisites

1)Working MySQL database
2)MySQL Workbench
2.1)Create a database

```
Create database database_activity with a table Clients which has a 
column named eMail where you have stored Client’s e-mails.
Then store some e-mails in it.
```

3)Change in StockSorting StockSorting constructor the password value in "abcd1234" according to your password

```
public StockSorting() {
		//put my password
		this.database = new DatabaseManager("abcd1234");
		this.companyList = database.getAll();
	}
```

## Project clases

This project is split in three parts(back end, the main, front end) and uses 4 classes that are in charge of a certain aspect 
of the code.

### CompanyStockData & DatabaseManager

These classes is responsible for backend to get stock data using IEX Trading API  to gather it, store it in a local MySQL database.

### StockSorting

This class is responsible for the main process of the project to calculate useful information of the raw data and gather it in a
matter that can be send to clients and send the ready data to the JavaMail class.

### JavaMail

This class is responsible for front end to make sure that the calculated information is send to client’s e-mail in a 
pleasant user interface. To do so this class uses MySQL and Java Mail API and CSS.


## Running the tests

This project uses Junit tests to tests the program for errors.

### Break down into end to end tests

* CompanyStockData & DatabaseManage test methods

```
They check what
Insert methods names
```

* StockSorting test methods

```
They check what
Insert methods names
```

* JavaMail test methods

```
//Checks that exceptions in code are done properly
test00Exceptions()
//Checks if data of recipients is gathered
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
* **Spring framework**
* **IEX Trading API**
* **Add more if there is**

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




