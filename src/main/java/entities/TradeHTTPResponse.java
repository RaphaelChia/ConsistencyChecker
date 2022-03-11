package entities;

public class TradeHTTPResponse extends CryptoComHTTPResponse{
    private GetTradeResponseResult result;
    private String code;
    private String method;

    public GetTradeResponseResult getResult() {
        return result;
    }

    public void setResult(GetTradeResponseResult result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
