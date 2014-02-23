package edu.brown.benchmark.tpceb.generators;

import java.util.Date;

import edu.brown.benchmark.tpceb.generators.TradeGenerator.TradeType;
import edu.brown.benchmark.tpceb.util.EGenDate;
import edu.brown.benchmark.tpceb.util.EGenMoney;

public class MEEPriceBoard {
    public MEEPriceBoard( int tradingTimeSoFar, Date baseTime, Date currentTime, SecurityHandler securityFile, int configuredCustomerCount){
        fMeanInTheMoneySubmissionDelay = 1.0 ;
        security = new MEESecurity();
        this.securityFile = securityFile;
        numberOfSecurities = 0;
        System.out.println("meepriceboard current time" + currentTime);
        numberOfSecurities = SecurityHandler.getSecurityNum(configuredCustomerCount);
        security.init( tradingTimeSoFar, baseTime, currentTime, fMeanInTheMoneySubmissionDelay );
        securityFile.loadSymbolToIdMap();
        
    }
    
    public    void getSymbol(long securityIndex,String szOutput, int outputLen){
        System.out.println("Getting symbol" +  szOutput);
        System.out.println("Getting symbol length" + outputLen);
        szOutput = securityFile.createSymbol( securityIndex, outputLen );
        System.out.println("Got symbol");
    }
    
    public    EGenMoney getMinPrice(){
        return( security.getMinPrice( ));
    }
    
    public    EGenMoney   getMaxPrice(){
        return( security.getMaxPrice( ));
    }
    
    public EGenMoney   getCurrentPrMEESecurityice( long securityIndex ){
        return( security.getCurrentPrice( securityIndex ));
    }
    
    public EGenMoney  getCurrentPrice( long securityIndex ){
        return( security.getCurrentPrice( securityIndex ));
    }

    public EGenMoney   getCurrentPrice( String  securitySymbol ){
        return( security.getCurrentPrice( securityFile.getIndex( securitySymbol )));
    }
    
    public EGenMoney   CalculatePrice(String securitySymbol, double fTime ){
        return( security.calculatePrice( securityFile.getIndex( securitySymbol ), fTime ));
    }
    
    public double   getSubmissionTime(String securitySymbol, double fPendingTime, EGenMoney fLimitPrice, TradeType eTradeTypeID){
        return( security.getSubmissionTime( securityFile.getIndex( securitySymbol ), fPendingTime, fLimitPrice, eTradeTypeID ));
    }
    
    public double   getSubmissionTime(long securityIndex, double fPendingTime, EGenMoney fLimitPrice, TradeType eTradeTypeID){
        return( security.getSubmissionTime( securityIndex, fPendingTime, fLimitPrice, eTradeTypeID ));
    }
    
    public double   getCompletionTime(long securityIndex,double fSubmissionTime, EGenMoney pCompletionPrice ){
        Object obj = security.getCompletionTimeAndPrice( securityIndex, fSubmissionTime)[0];
        String str = obj.toString();
        return Double.valueOf(str).doubleValue();
    }
    
    public long getNumOfSecurities(){
        return numberOfSecurities;
    }
    private long                 numberOfSecurities;
    private double              fMeanInTheMoneySubmissionDelay;
    private MEESecurity         security;
    private SecurityHandler     securityFile;
}
