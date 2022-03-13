package builders;


import exceptions.InvalidTickerPatternException;
import exceptions.InvalidTimeframeException;
import exceptions.MissingParamsException;

import static constants.RegexPatterns.tickerPattern;
import static constants.RegexPatterns.timeframePattern;
import static constants.httpConstants.getCandleUrl;

/**
 * Must have instrument_name and timeframe or else the URL cannot work.
 */
public class CandleUrlBuilder extends TickerRelatedUrlBuilder {

    private final int defaultDepth = 1000;
    private String instrumentName;
    private String timeframe;
    private int depth=defaultDepth;

    @Override
    public String getURL() throws MissingParamsException {
        if((instrumentName == null || instrumentName.isEmpty()) || (timeframe == null || timeframe.isEmpty())){
            throw new MissingParamsException(String.format("<%s> is missing certain parameters.", getCandleUrl));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getCandleUrl);
        sb.append("?instrument_name=");
        sb.append(instrumentName);
        sb.append("&timeframe=");
        sb.append(timeframe);
        sb.append(depth>0?"&depth="+ depth:"");
        System.out.println("Candlestick API URL: "+sb);
        return sb.toString();

    }

    @Override
    public CandleUrlBuilder setInstrumentName(String instrumentName) throws InvalidTickerPatternException {
        if(!tickerPattern.matcher(instrumentName).find()) throw new InvalidTickerPatternException(instrumentName+" is invalid.");
        this.instrumentName=instrumentName;
        return this;
    }

    public CandleUrlBuilder setTimeFrame(String timeframe) throws InvalidTimeframeException {
        if(!timeframePattern.matcher(timeframe).find()) throw new InvalidTimeframeException(timeframe+" is invalid.");
        this.timeframe = timeframe;
        return this;
    }

    /**
     * Choose how many candle u want to return.
     * If you choose a depth of 1000, it might not return you 1000. It will return you what it can.
     * @param d 1-1000
     * @return
     */
    public CandleUrlBuilder setDepth(int d){
        if(d<0 || d>defaultDepth){
            depth=defaultDepth;
        } else {
            depth = d;
        }
        return this;
    }

    @Override
    public void reset() {
        instrumentName = null;
        timeframe = null;
        depth=defaultDepth;
    }
}
