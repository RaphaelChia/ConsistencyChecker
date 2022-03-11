import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static constants.RegexPatterns.tickerPattern;
import static org.junit.jupiter.api.Assertions.*;

public class MyTest {
    @Test
    public void tickerPatternFailMatchTest(){
        Pattern p = tickerPattern;
        assertFalse(p.matcher("BTC(USDT").find());
        assertFalse(p.matcher("BTC*USDT").find());
        assertFalse(p.matcher("BTC+USDT").find());
        assertFalse(p.matcher("BTC!USDT").find());
        assertFalse(p.matcher("BTCBCBC_USDT").find());
    }

    @Test
    public void tickerPatternPassMatchTest(){
        Pattern p = tickerPattern;
        assertTrue(p.matcher("BTC_USDT").find());
        assertTrue(p.matcher("BTCCCC_USDTTT").find());
        assertTrue(p.matcher("BT_USDTTT").find());
        assertTrue(p.matcher("BTCTTT_US").find());
        assertTrue(p.matcher("BTC_USDT").find());
    }
}
