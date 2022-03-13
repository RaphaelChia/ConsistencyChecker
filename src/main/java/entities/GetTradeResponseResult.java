package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the value of the "result" parameter from Crypto com API response.
 */
public class GetTradeResponseResult extends Result{
    private List<Trade> data;
    private String instrument_name; //This will be null if there's no specified instrument name when sending request

    public List<Trade> getData() {
        return data;
    }

    public void setData(List<Trade> data) {
        this.data = data;
    }

    public String getInstrument_name() {
        return instrument_name;
    }

    public void setInstrument_name(String instrument_name) {
        this.instrument_name = instrument_name;
    }
}
