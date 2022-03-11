package builders;

import exceptions.InvalidTickerPatternException;

public abstract class CryptoComUrlBuilder {
    // PLS PLS PLS GO AND DO A PROPER BUILDER. Take in all params as separate attributes, then when build, will put all together.
    public abstract String getURL();
    public abstract void setInstrumentName(String instrumentName) throws InvalidTickerPatternException;

}
