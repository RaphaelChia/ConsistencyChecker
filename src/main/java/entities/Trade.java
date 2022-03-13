package entities;

import java.math.BigDecimal;

/**
 * Trade class contains the information of the trade being executed for a particular currency.
 *
 */
public class Trade {
    private long dataTime;
    private String d; //Trade id
    private String s; //Side either BUY or SELL
    private BigDecimal p; //Trade price
    private BigDecimal q; //Trade Quantity
    private long t; //Unix timestamp of trade, millisecond
    private String i; //Ticker symbol.

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }
}
