package builders;

import exceptions.InvalidTickerPatternException;

import static constants.RegexPatterns.tickerPattern;
import static constants.httpConstants.getTradesUrl;

public class TradeUrlBuilder extends CryptoComUrlBuilder{

    private final String baseURL = getTradesUrl;
    private String url;

    public TradeUrlBuilder() {
        this.url = this.baseURL;
    }

    @Override
    public String getURL() {
        return this.url;
    }

    public void setInstrumentName(String instrumentName) throws InvalidTickerPatternException {
        if(!tickerPattern.matcher(instrumentName).find()) throw new InvalidTickerPatternException("");
        this.url +=  this.url.endsWith("get-trades")?"?":"&";
        this.url += "instrument_name="+instrumentName.toUpperCase();
    }
}
