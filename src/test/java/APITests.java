import RequestFunctions.PrebuiltRequests;
import builders.CandleUrlBuilder;
import builders.TradeUrlBuilder;
import constants.Timeframes;
import entities.CryptoComHTTPResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class APITests {
    @Test
    public void correctCandlestickAPITest(){
        try{
            String timeframe=Timeframes.m1;
            String insName = "ETH_USDT";
            CandleUrlBuilder cdlBuilder = new CandleUrlBuilder()
                    .setTimeFrame(timeframe)
                    .setInstrumentName(insName);
            TradeUrlBuilder tBuilder = new TradeUrlBuilder()
                    .setInstrumentName(insName);

            PrebuiltRequests.submitRequest(cdlBuilder.getURL());
            PrebuiltRequests.submitRequest(tBuilder.getURL());
        }catch(Exception e){
            fail("Not supposed to have any exception thrown for a correct API call.");
        }
    }
}
