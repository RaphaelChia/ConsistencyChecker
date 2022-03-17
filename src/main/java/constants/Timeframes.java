package constants;

import exceptions.InvalidTimeframeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timeframes {
    public static final String m1 = "1m";
    public static final String m5 = "5m";
    public static final String m15 = "15m";
    public static final String m30 = "30m";
    public static final String h1 = "1h";
    public static final String h4 = "4h";
    public static final String h6 = "6h";
    public static final String h12 = "12h";
    public static final String D1 = "1D";
    public static final String D7 = "7D";
    public static final String D14 = "14D";
    public static final Map<String,Integer> inMinutes = new HashMap<>();


    public static int getMinutes(String tf) throws InvalidTimeframeException {
        //Singleton pattern to only populate the first time this function is called
        if(inMinutes.isEmpty()){
            inMinutes.put(m1,1);
            inMinutes.put(m5,5);
            inMinutes.put(m15,15);
            inMinutes.put(m30,30);
            inMinutes.put(h1,60);
            inMinutes.put(h4,240);
            inMinutes.put(h6,360);
            inMinutes.put(h12,720);
            inMinutes.put(D1,1440);
            inMinutes.put(D7,10080);
            inMinutes.put(D14,20160);
        }
        if(inMinutes.get(tf)==null) throw new InvalidTimeframeException(tf+" is not supported.\n");
        return inMinutes.get(tf);
    }
}
