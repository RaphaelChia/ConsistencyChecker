# Maven Candle Reconciliation Project
Checking the candles consistency against the trades of a particular ticker.
This program makes use of 2 api,

https://exchange-docs.crypto.com/spot/index.html?java#public-get-candlestick

https://exchange-docs.crypto.com/spot/index.html?java#public-get-trades

to retrieve and reconcile candlesticks of various timeframes. There are afew things to take note of.
- This program will **not** reconcile the first current running bar, as it will always be an inaccurate one, due to `get-candlestick` API being a few seconds slower than `get-trades API`.
- Trade data is limited, hence for very liquid instruments like ETH_USDT, trade data returned from API is usually insufficient to form a single 1m candlestick.
### Example
Trade data will return the latest 200 trades that took place for the instrument. If we want to reconcile the past 10 x 5minute ETH_USDT bars, we would need 50minutes worth of trade data, in order to correctly reconcile. But because we are only receiving 200 trades (past 200 trade probably happened in <10minutes due to liquid instrument), it is insufficient. Hence if you attempt this, the report would then show up as `Trade data insufficient for X candles of X timeframe`.


## Setup
- Using command prompt (important)
- Ensure that maven is set up in your path variable.
- Double check by running `mvn --version`
- Java 11 and above.
- Double check by running `java --version`
- At the project root directory, run this command:
`mvn -s settings.xml clean package` If this doesn't work, run this instead -> `mvn -gs settings.xml clean package `


## Running the program

`mvn exec:java -Dexec.mainClass=ConsistencyChecker -Dexec.cleanupDaemonThreads=false -Dexec.args="BRZ_USDT 1m 5"`

### Explanation
The template command is as such:

`mvn exec:java -Dexec.mainClass=ConsistencyChecker -Dexec.cleanupDaemonThreads=false -Dexec.args="<tickersymbol> <timeframe> <number of bars to reconcile>`

Replace everything in angle brackets, here is an example of a runnable command: 

You can leave the `timeframe` and `number of bars to reconcile` blank. It defaults to `1m` and `5`. Example:

`mvn exec:java -Dexec.mainClass=ConsistencyChecker -Dexec.cleanupDaemonThreads=false -Dexec.args="BRZ_USDT"`

## Output Report Explanation
Example #1
__Instrument__:ETH_USDT
__Timeframe__: 1m
__Num of candles__: 5
```
Finished. 1/2 accurate Candlesticks.  //Total of 2 candles found in trade data. Only 1 was accurate.
Report:                                                                                                   
[Trade data insufficient for 5 candles of 1m timeframe] //Marker to inform us that, the requested 
                                                        //number of candles and timeframe cannot be
                                                        //reconciled due to lack of trade data
                                                                                                          
Error Candle:   13:57:00 Thursday, March 2022   //The candle that mismatch the trade data
Candle Values:  Open[2760.050000]       High[2761.290000]       Low[2759.860000]        Close[2760.500000]
Error Portion:  Close[2760.500000 ---> 2760.270000]  //The portion that was mismatched is the Close of that candle.
```
Example #2
__Instrument__:ETH_USDT
__Timeframe__: 1m
__Num of candles__: 5
```
Finished. 2/2 accurate Candlesticks. //Total of 2 candles found in trade data. 2 was accurate.
Report:                                                                        
[Trade data insufficient for 5 candles of 1m timeframe] //Marker to inform us that, the requested 
                                                        //number of candles and timeframe cannot be
                                                        //reconciled due to lack of trade data
```

Example #3 
__Instrument__:ETH_USDT
__Timeframe__: 1m
__Num of candles__: 1
```
Finished. 0/0 accurate Candlesticks. //Due to large volume of ETH_USDT trades suddenly, 
                                     //there is not even a single minute of candlestick in trade data
                                     //Hence no complete candlesticks found
Report:                                                                        
[Trade data insufficient for 1 candles of 1m timeframe]
```
  
### Guidelines
- Timeframe argument only accepts:
`1m, 5m, 15m, 30m, 1h, 4h, 6h, 12h, 1D, 7D, 14D`
- Number of bars must be a __positive number__.
