package com.noder.cargadorws.Types;

import java.util.Date;

public class Transaction {
    
    private long transactionId;
    private int connectorId;
    private Date startDate;
    private Date endDate;
    private Integer meterStart;
    private Integer meterStop;
    private TransactionStatus status;

    public Transaction(int connectorId, Integer meterStart, Date startDate){
        this.connectorId = connectorId;
        this.startDate = startDate;
        this.meterStart = meterStart;
        this.meterStop = null;
        this.endDate = null;
        this.status = TransactionStatus.Active;
    }

    public void endTransaction(Integer meterStop){
        this.meterStop = meterStop;
        this.endDate = new Date();
        this.status = TransactionStatus.Ended;
    }

    public enum TransactionStatus{
        Active,
        Ended
    };

    
}