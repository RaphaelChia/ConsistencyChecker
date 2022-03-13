package entities;

import java.math.BigDecimal;

/**
 * CandleStick class contains the Open,High,Low,Close of a candle.
 * Big decimal is used because we're dealing with currency, hence we need the precision.
 */
public class Candlestick {
    private long t; //End of candlestick unix timestamp, millisecond
    private BigDecimal o;
    private BigDecimal h;
    private BigDecimal l;
    private BigDecimal c;
    private BigDecimal v; //Volume

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public BigDecimal getO() {
        return o;
    }

    public void setO(BigDecimal o) {
        this.o = o;
    }

    public BigDecimal getH() {
        return h;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }
}
