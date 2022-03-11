import RequestWrapper.PrebuiltRequests;
import builders.CandleUrlBuilder;
import builders.TradeUrlBuilder;
import constants.Timeframes;
import entities.CandleHTTPResponse;
import entities.CryptoComHTTPResponse;
import entities.TradeHTTPResponse;
import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;

import java.io.IOException;

import static constants.RegexPatterns.tickerMax;
import static constants.RegexPatterns.tickerMin;

public class ConsistencyChecker {
    public static void main(String[] args) {

        try{
            // Build the candle url with GET
            long start = System.currentTimeMillis();
            CandleUrlBuilder cdlBuilder = new CandleUrlBuilder()
                    .setTimeFrame(Timeframes.m5)
                    .setInstrumentName("BTC_USDT");
            CryptoComHTTPResponse resp = PrebuiltRequests.submitRequest(cdlBuilder.getURL());
            TradeUrlBuilder tBuilder = new TradeUrlBuilder()
                    .setInstrumentName("BTC_USDT");
            CryptoComHTTPResponse resp2 = PrebuiltRequests.submitRequest(tBuilder.getURL());
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.printf("Time elapsed: %d ms%n",timeElapsed);
            System.out.println(((TradeHTTPResponse)resp2).getResult().getData().length);
            System.out.println(((CandleHTTPResponse)resp).getResult().getData().length);
        }catch(InvalidTickerPatternException ITE){
            System.out.printf("%s Tickers should be within %s-%s characters long.%n",ITE.getMessage(),tickerMin,tickerMax);
        }catch(InvalidTimeframeException ITE){
            System.out.printf("%s Timeframe error.%n",ITE.getMessage());
        }catch(IOException ioe){
            System.out.printf("IO Exception Encountered. %n%s",ioe.getMessage());
        }catch(InterruptedException ie){
            System.out.printf("Request was interrupted. %n%s",ie.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
