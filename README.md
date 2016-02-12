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
```
	cd super-simple-stocks
	mvn install
```
#Artifacts
The build produces a single artifact super-simple-stocks.jar
