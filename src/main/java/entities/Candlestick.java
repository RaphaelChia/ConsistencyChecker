package entities;

public class Candlestick {
    private long t; //End of candlestick unix timestamp
    private float o;
    private float h;
    private float l;
    private float c;
    private float v; //Volume

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public float getO() {
        return o;
    }

    public void setO(float o) {
        this.o = o;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }
}
