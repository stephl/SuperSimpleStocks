# SuperSimpleStocks
A sample Java implementation of the "Super Simple Stocks" exercise.  
* For a given stock:
  * Given a market price as input, calculate the dividend yield
  * Given a market price as input,  calculate the P/E Ratio
  *	Record a trade, with timestamp, quantity of shares, buy or sell indicator and trade price
  *  Calculate Volume Weighted Stock Price based on trades in past 15 minutes
* Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

# Project Layout
* super-simple-stocks - this is a maven and eclipse project containing Java source code
* src/main/java/**/model - a set of simple model classes representing stocks and trades
* src/main/java/**/dataacess - simple implementation of "persistence" that can be used to store and retrieve model objects
* src/main/java/**/service - the top level service API providing the application functionality


# Dependencies
super-simple-stock has a test time dependency on org.junit. 

# Building
The source is an eclipse project and can be built within eclipse using the m2e plugin (Maven).  During development Eclipse version 5.4.0 with Maven 3.3.3 was used.

To build outwith eclipse, use maven from the command line. i.e.
```s
	cd super-simple-stocks
	mvn install
```
# Artifacts
The build produces a single artifact super-simple-stocks.jar

# Running
A simple CLI version of the app can be ran using:
```
	java -classpath super-simple-stocks-0.0.1.jar com.jpm.simplestocks.app.SuperSimpleStockApp  
```
Enter: 
HELP to view this help page
GET to view current stocks
PE <symbol> <price> to see the PE Ratio for the given stock and price 
YIELD <symbol> <price> to see the Dividend Yield for the given stock and price 
BUY <symbol> <quantity> <price> to buy 
SELL <symbol> <quantity> <price> to sell 
PRICE <symbol> to see the stock price for the given stock 
INDEX to see the All Share Index
EXIT to exit the system
