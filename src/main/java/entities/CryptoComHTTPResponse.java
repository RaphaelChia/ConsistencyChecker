package entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This entity will help generalize the return object from crypto com API.
 * A jackson serializer will automatically look for the @JsonSubTypes that fits the
 * incoming object JSON criteria, and map it to the object.
 * In order to add more objects for the polymorphic serialization,
 * Set up your objects exactly to the HTTP JSON response of the API,
 * and add another row of
 * @JsonSubTypes.Type(value=yourobjectname.class, name ="whatever the value stored in the method property")
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "method")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CandleHTTPResponse.class, name = "public/get-candlestick"),
        @JsonSubTypes.Type(value = TradeHTTPResponse.class, name = "public/get-trades")
})
public abstract class CryptoComHTTPResponse {
    public String code;
    // The reason why i cannot put result over here even though its always repeated in http response is because
    // result have different kind of body, which require a polymorphic deserialization, which require a
    // property that specifies which object to be mapped to.
    // And in the result object, there is no unique field to consistently determine, what kind of http response
    // result is this.
}
