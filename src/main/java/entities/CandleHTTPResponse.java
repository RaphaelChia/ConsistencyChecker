package entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class CandleHTTPResponse extends CryptoComHTTPResponse {
    private GetCandlestickResponseResult result;
    private String method;


    public GetCandlestickResponseResult getResult() {
        return result;
    }

    public void setResult(GetCandlestickResponseResult result) {
        this.result = result;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
