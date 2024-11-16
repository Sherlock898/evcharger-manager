package com.noder.cargadorws.ocpp.messages;

import com.noder.cargadorws.ocpp.messages.types.IdTagInfo;

public record StartTransactionConf(IdTagInfo idTagInfo, Integer transactionId) {
    
}
