package builders;

import exceptions.InvalidTickerPatternException;

import static constants.RegexPatterns.tickerPattern;
import static constants.httpConstants.getTradesUrl;

public class TradeUrlBuilder extends TickerRelatedUrlBuilder {

    private final String baseURL = getTradesUrl;
    private String instrumentName;


    @Override
    public String getURL() {
        StringBuilder sb = new StringBuilder();
        sb.append(baseURL);
        sb.append(instrumentName==null || instrumentName.isEmpty()?"":"?instrument_name="+instrumentName);
        System.out.println("Trades API URL: "+sb);
        return sb.toString();
    }

    public TradeUrlBuilder setInstrumentName(String instrumentName) throws InvalidTickerPatternException {
        if(!tickerPattern.matcher(instrumentName).find()) throw new InvalidTickerPatternException("");
        this.instrumentName=instrumentName;
        return this;
    }

    @Override
    public void reset() {
        instrumentName = null;
    }
}
