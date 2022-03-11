package entities;

public class GetTradeResponseResult extends Result{
    private Trade[] data;
    private String instrument_name;

    public String getInstrument_name() {
        return instrument_name;
    }

    public void setInstrument_name(String instrument_name) {
        this.instrument_name = instrument_name;
    }

    public Trade[] getData() {
        return data;
    }

    public void setData(Trade[] data) {
        this.data = data;
    }
}
