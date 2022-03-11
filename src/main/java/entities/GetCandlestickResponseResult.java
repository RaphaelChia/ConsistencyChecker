package entities;

public class GetCandlestickResponseResult extends Result {

    private String instrument_name;
    private int depth;
    private String interval;
    private Candlestick[] data;

    public String getInstrument_name() {
        return instrument_name;
    }

    public void setInstrument_name(String instrument_name) {
        this.instrument_name = instrument_name;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Candlestick[] getData() {
        return data;
    }

    public void setData(Candlestick[] data) {
        this.data = data;
    }
}
