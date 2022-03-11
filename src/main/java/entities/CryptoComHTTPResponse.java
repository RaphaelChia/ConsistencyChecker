package entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "method")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CandleHTTPResponse.class, name = "public/get-candlestick"),
        @JsonSubTypes.Type(value = TradeHTTPResponse.class, name = "public/get-trades")
})
public abstract class CryptoComHTTPResponse {

}
