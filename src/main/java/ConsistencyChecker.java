import builders.CandleUrlBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Timeframes;
import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static constants.RegexPatterns.tickerMax;
import static constants.RegexPatterns.tickerMin;

public class ConsistencyChecker {
    public static void main(String[] args) {
        System.out.println("hello wd");

        try{
            // Build the candle url with get params
            CandleUrlBuilder cdlBuilder = new CandleUrlBuilder();
            cdlBuilder.setTimeFrame(Timeframes.m5);
            cdlBuilder.setInstrumentName("BTC_USDT");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(cdlBuilder.getURL()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper om = new ObjectMapper()
;        }catch(InvalidTickerPatternException ITE){
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
