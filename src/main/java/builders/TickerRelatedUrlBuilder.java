package builders;

import exceptions.InvalidTickerPatternException;
import exceptions.MissingParamsException;

/**
 * This abstract class is used for grouping all builders
 * while ensuring that there is the common buildURL() method.
 */
public abstract class TickerRelatedUrlBuilder implements CryptoUrlBuilder{
    // PLS PLS PLS GO AND DO A PROPER BUILDER. Take in all params as separate attributes, then when build, will put all together.
    public abstract String getURL() throws MissingParamsException;
    public abstract TickerRelatedUrlBuilder setInstrumentName(String instrumentName) throws InvalidTickerPatternException;

}
