package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *  This is the value of the "result" parameter from Crypto com API response.
 */
public class GetCandlestickResponseResult extends Result {

    private String instrument_name;
    private int depth;
    private String interval;
    private List<Candlestick> data;

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

    public List<Candlestick> getData() {
        return data;
    }

    public void setData(List<Candlestick> data) {
        this.data = data;
    }

    public List<Candlestick> getPastXBarsIncludeCurrent(int x){
        return this.data.subList(this.data.size()-x,this.data.size());
    }

    public List<Candlestick> getPastXBarsExcludeCurrent(int x){
        return this.data.subList(this.data.size()-1-x,this.data.size()-1);
    }
}
