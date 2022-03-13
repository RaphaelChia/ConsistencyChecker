# Candle Reconciliation
Checking the candles consistency against the trades of a particular ticker.

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

`mvn exec:java -Dexec.mainClass=ConsistencyChecker -Dexec.cleanupDaemonThreads=false -Dexec.args="<tickersymbol> <timeframe> <number of bars to reconcile>"`

Replace everything in angle brackets, here is an example of a runnable command: 

You can leave the `timeframe` and `number of bars to reconcile` blank. It defaults to `1m` and `5`. Example:

`mvn exec:java -Dexec.mainClass=ConsistencyChecker -Dexec.cleanupDaemonThreads=false -Dexec.args="BRZ_USDT"`
  
### Guidelines
- Timeframe argument only accepts:
`1m, 5m, 15m, 30m, 1h, 4h, 6h, 12h, 1D, 7D, 14D`
- Number of bars must be a __number__.
