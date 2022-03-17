import constants.Timeframes;
import entities.Candlestick;
import entities.Trade;
import exceptions.InvalidTimeframeException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Reconciliation {


    private int barsToCheck;
    private int accurateCandles;
    private int timeframeInMinutes;
    private int totalActuallyChecked;
    private String resultString="";
    private List<Trade> tradeData;
    private List<Candlestick> candleData;
    /**
     * This function will reconcile and check for past number of bars to see if all OHLC are correct or not.
     * @param xNumBars the past number of bars to check. Will not check the current running bar.
     *                 e.g. its 23:22:02 now, the latest M1 candlestick time is 23:22:00,
     *                 so it will go to 23:21 and start
     */
    public void reconcile(int xNumBars, List<Trade> tradeData, List<Candlestick> candleData,String timeframe) throws InvalidTimeframeException {
        if(xNumBars>candleData.size()-1) return; //Dont run if numbars more than candle data passed in
        barsToCheck=xNumBars;
        totalActuallyChecked = 0;
        accurateCandles = 0;
        timeframeInMinutes=Timeframes.getMinutes(timeframe);
        int cdlIdx=0;
        int tradeIdx=0;
        Calendar tradeDT = Calendar.getInstance();
        Calendar candleDTCurrent = Calendar.getInstance();
        Calendar candleDTLater = Calendar.getInstance();

        //Retrieve x number of candlesticks excluding the current running one.
        List<Candlestick> candlesToCheck = candleData.subList(candleData.size()-1-xNumBars,candleData.size()-1);
        Collections.reverse(candlesToCheck);

        // Checking if there is enough trade data to perform reconciliation
        Calendar tempDT = Calendar.getInstance();
        tradeDT.setTimeInMillis(tradeData.get(tradeData.size()-1).getT()); //Set the most recent DT
        tempDT.setTimeInMillis(tradeData.get(0).getT()); //Set the most recent minus (bars * TF in minutes)
        tempDT.add(Calendar.MINUTE,-barsToCheck*timeframeInMinutes);
        if(tempDT.before(tradeDT)){
            resultString+=String.format("[Trade data insufficient for %d candles of %s timeframe]%n%n",barsToCheck,timeframe );
        }
        while(cdlIdx<candlesToCheck.size() && tradeIdx<tradeData.size()){
            Candlestick currentCdlObj = candlesToCheck.get(cdlIdx);
            candleDTCurrent.setTimeInMillis(currentCdlObj.getT());
            candleDTLater.setTimeInMillis(currentCdlObj.getT());
            candleDTLater.add(Calendar.MINUTE,timeframeInMinutes);
            BigDecimal open =null;
            BigDecimal high =null;
            BigDecimal low  =null;
            BigDecimal close= null;

            //Loop through the trade data
            while(tradeIdx<tradeData.size()){ //Exit condition for this loop is when no more trade data or, trade data moves to next candle
                Trade trObj = tradeData.get(tradeIdx);
                tradeDT.setTimeInMillis(trObj.getT());
                if(!tradeDT.before(candleDTCurrent) && tradeDT.before(candleDTLater)){
                    //Processing the OHLC
                    close = close==null?trObj.getP():close;
                    open = trObj.getP();
                    if(high==null)
                        high=trObj.getP();
                    else
                        high = trObj.getP().compareTo(high)>0?trObj.getP():high;
                    if(low==null)
                        low=trObj.getP();
                    else
                        low = trObj.getP().compareTo(low)<0?trObj.getP():low;
                    tradeIdx++;
                } else if (!tradeDT.before(candleDTLater)){
                    tradeIdx++;
                }else{
                    boolean accurateFlag=true;
                    String tempErrProperty="";
                    //Report Generation for ease of reading
                    if(currentCdlObj.getO().compareTo(open)!=0){
                        tempErrProperty+=String.format("Open[%f ---> %f], ",currentCdlObj.getO(),open);
                        accurateFlag=false;
                    }
                    if(currentCdlObj.getH().compareTo(high)!=0){
                        tempErrProperty+=String.format("High[%f ---> %f], ",currentCdlObj.getH(),high);
                        accurateFlag=false;
                    }
                    if(currentCdlObj.getL().compareTo(low)!=0){
                        tempErrProperty+=String.format("Low[%f ---> %f], ",currentCdlObj.getL(),low);
                        accurateFlag=false;
                    }
                    if(currentCdlObj.getC().compareTo(close)!=0){
                        tempErrProperty+=String.format("Close[%f ---> %f], ",currentCdlObj.getC(),close);
                        accurateFlag=false;
                    }

                    if(accurateFlag){
                        accurateCandles+=1;
                    } else {
                        tempErrProperty = !tempErrProperty.isEmpty()?tempErrProperty.substring(0,tempErrProperty.length()-2):"";
                        String dtformat = String.format("Error Candle:\t%tT %1$tA, %1$tB %1$tY %n",candleDTCurrent);
                        String cdlStringData = String.format("Open[%f]\tHigh[%f]\tLow[%f]\tClose[%f]%n",currentCdlObj.getO(),currentCdlObj.getH(),currentCdlObj.getL(),currentCdlObj.getC());
                        resultString = resultString+
                                dtformat+
                                "Candle Values:\t"+ cdlStringData +
                                "Error Portion:\t"+tempErrProperty+"\n\n";
                    }
                    totalActuallyChecked++;
                    cdlIdx++;
                    break;
                }
            }
        }
        System.out.printf("Finished. %d/%d accurate Candlesticks.%n",accurateCandles, totalActuallyChecked);
        printResultString();
    }
    public void printResultString(){
        System.out.printf("Report:%n%s", Objects.equals(resultString, "") ?"No Inconsistent Candle Detected!\n":resultString);
    }

}
