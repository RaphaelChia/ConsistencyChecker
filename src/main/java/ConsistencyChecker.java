import RequestFunctions.PrebuiltRequests;
import builders.CandleUrlBuilder;
import builders.TradeUrlBuilder;
import constants.Timeframes;
import entities.*;
import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static constants.RegexPatterns.tickerMax;
import static constants.RegexPatterns.tickerMin;

public class ConsistencyChecker {
    public static void main(String[] args) {
        //Clearing the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try{
           if(args.length<1){
               System.out.println("No ticker sepcified.");
               return;
           }
            String insName = args[0];
            String timeframe = args.length>1?args[1]:Timeframes.m30;
            int numBars = args.length>2?Integer.parseInt(args[2]):5;
            Timeframes.getMinutes(timeframe); // Checking for valid timeframe
            if(numBars<=0){
                System.out.println("Number of bars to reconcile must be positive.");
                return;
            }
            long start = System.currentTimeMillis(); //Starting the milisecond timer
            System.out.println("Ticker to check: "+insName);
            System.out.println("Timeframe to check: "+timeframe);
            System.out.println("NUmber of bars to reconcile: "+numBars);
            // Build the candle url with GET
            CandleUrlBuilder cdlBuilder = new CandleUrlBuilder()
                    .setTimeFrame(timeframe)
                    .setInstrumentName(insName);
            TradeUrlBuilder tBuilder = new TradeUrlBuilder()
                    .setInstrumentName(insName);

            CryptoComHTTPResponse candleResp = PrebuiltRequests.submitRequest(cdlBuilder.getURL());
            CryptoComHTTPResponse tradeResp = PrebuiltRequests.submitRequest(tBuilder.getURL());

            // If any data is null means there has been an error.
            if(((TradeHTTPResponse)tradeResp).getResult().getData()==null || ((TradeHTTPResponse)tradeResp).getResult().getData().isEmpty()
            || ((CandleHTTPResponse)candleResp).getResult().getData()==null || ((CandleHTTPResponse)candleResp).getResult().getData().isEmpty()){
                System.out.println("Unable to perform reconciliation. The API response is empty. Please check your settings and retry.");
                return;
            }

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;

            System.out.printf("Time elapsed: %d ms%n",timeElapsed);
            System.out.printf("Trade result data size: %d%n",((TradeHTTPResponse)tradeResp).getResult().getData().size());
            System.out.printf("Candlestick result data size: %d%n",((CandleHTTPResponse)candleResp).getResult().getData().size());

            List<Trade> tradeData = ((TradeHTTPResponse)tradeResp).getResult().getData();
            List<Candlestick> candleData = ((CandleHTTPResponse)candleResp).getResult().getData();
            Reconciliation recon = new Reconciliation();
            recon.reconcile(numBars,tradeData,candleData,timeframe);
        }catch(InvalidTickerPatternException ITE){
            System.out.printf("%s Tickers should be within %s-%s characters long.%n",ITE.getMessage(),tickerMin,tickerMax);
        }catch(InvalidTimeframeException ITE){
            System.out.printf("Timeframe error.%n%s",ITE.getMessage());
        }catch(IOException ioe){
            System.out.printf("IO Exception Encountered. %n%s",ioe.getMessage());
        }catch(InterruptedException ie){
            System.out.printf("Request was interrupted. %n%s",ie.getMessage());
        }catch(NumberFormatException nfe){
            System.out.println("Number of bars should be a number.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
