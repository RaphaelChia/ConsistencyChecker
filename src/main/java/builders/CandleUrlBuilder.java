package builders;


import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;

import static constants.RegexPatterns.tickerPattern;
import static constants.RegexPatterns.timeframePattern;
import static constants.httpConstants.getCandleUrl;

public class CandleUrlBuilder extends CryptoComUrlBuilder{


    private final String baseURL = getCandleUrl;
    private String url = baseURL;


    @Override
    public String getURL() {
        return this.url;
    }

    @Override
    public CandleUrlBuilder setInstrumentName(String instrumentName) throws InvalidTickerPatternException {
        if(!tickerPattern.matcher(instrumentName).find()) throw new InvalidTickerPatternException(instrumentName+" is invalid.");
        this.url +=  this.url.endsWith("get-candlestick")?"?":"&";
        this.url += "instrument_name="+instrumentName.toUpperCase();
        return this;
    }

    public CandleUrlBuilder setTimeFrame(String timeframe) throws InvalidTimeframeException {
        if(!timeframePattern.matcher(timeframe).find()) throw new InvalidTimeframeException(timeframe+" is invalid.");
        this.url +=  this.url.endsWith("get-candlestick")?"?":"&";
        this.url += "timeframe="+timeframe;
        return this;
    }
}
