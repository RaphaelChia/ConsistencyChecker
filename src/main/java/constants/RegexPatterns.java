package constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexPatterns {
    public static final int tickerMin = 2;
    public static final int tickerMax = 6;
    public static final Pattern tickerPattern = Pattern.compile("^([A-Z]{"+tickerMin+","+tickerMax+"}_[A-Z]{"+tickerMin+","+tickerMax+"})$",Pattern.CASE_INSENSITIVE);
//    public static final Pattern timeframePattern = Pattern.compile("^(([1-9]|[1][0-9])[a-zA-Z])$");
}
