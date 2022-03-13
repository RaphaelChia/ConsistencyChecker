import builders.CandleUrlBuilder;
import builders.TradeUrlBuilder;
import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;
import exceptions.MissingParamsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class BuilderTest {

    TradeUrlBuilder tradeUrlBuilder = new TradeUrlBuilder();
    CandleUrlBuilder candleUrlBuilder = new CandleUrlBuilder();

    @Test
    public void TradeUrlInvalidTickerException(){
        try{
            tradeUrlBuilder.setInstrumentName("");
            fail("No exception thrown when built an erronous Trade URL");
        }catch(InvalidTickerPatternException ignored){

        }
    }

    @Test
    public void CandleUrlInvalidTickerException(){
        try{
            candleUrlBuilder.setInstrumentName("");
            fail("No exception thrown when built an erronous CandleStick URL");
        }catch(InvalidTickerPatternException ignored){

        }
    }

    @Test
    public void CandleUrlInvalidTimeFrameThrowException(){
        try{
            candleUrlBuilder.setTimeFrame("hehe");
            fail("No exception thrown when built an erronous CandleStick URL");
        }catch(InvalidTimeframeException ignored){

        }
    }

    @Test
    public void CandleUrlInvalidParamThrowException(){
        candleUrlBuilder.reset();
        try{
            candleUrlBuilder.getURL();
            fail("No exception thrown when build without instrument name and timeframe");
        }catch(MissingParamsException ignored){

        }
    }

}
