package com.noder.cargadorws.Types;

import java.time.Instant;

public class Transaction {

    private static Integer transactionCounter = 0;

    private Integer transactionId;
    private int connectorId;
    private Instant startDate;
    private Instant endDate;
    private Integer meterStart;
    private Integer meterStop;
    private TransactionStatus status;

    public Transaction(int connectorId, Integer meterStart, Instant startDate){
        this.transactionId = transactionCounter++;
        this.connectorId = connectorId;
        this.startDate = startDate;
        this.meterStart = meterStart;
        this.meterStop = null;
        this.endDate = null;
        this.status = TransactionStatus.Active;
    }

    public void endTransaction(Integer meterStop){
        this.meterStop = meterStop;
        this.endDate = Instant.now();
        this.status = TransactionStatus.Ended;
    }

    public enum TransactionStatus{
        Active,
        Ended
    };

    public Integer getTransactionId() {
        return transactionId;
    }

    public TransactionStatus getTransactionStatus() {
        return status;
    }
}