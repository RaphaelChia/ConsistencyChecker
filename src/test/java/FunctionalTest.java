import builders.CandleUrlBuilder;
import builders.TradeUrlBuilder;
import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FunctionalTest {
    @Test
    public void TradeUrlInvalidTickerException(){
        TradeUrlBuilder b = new TradeUrlBuilder();
        try{
            b.setInstrumentName("");
            fail("No exception thrown when built an erronous Trade URL");
        }catch(InvalidTickerPatternException ITE){

        }
    }

    @Test
    public void CandleUrlInvalidTickerException(){
        CandleUrlBuilder b = new CandleUrlBuilder();
        try{
            b.setInstrumentName("");
            fail("No exception thrown when built an erronous CandleStick URL");
        }catch(InvalidTickerPatternException ITE){

        }
    }

    @Test
    public void CandleUrlInvalidTimeFrameException(){
        CandleUrlBuilder b = new CandleUrlBuilder();
        try{
            b.setTimeFrame("hehe");
            fail("No exception thrown when built an erronous CandleStick URL");
        }catch(InvalidTimeframeException ITE){

        }
    }
}
