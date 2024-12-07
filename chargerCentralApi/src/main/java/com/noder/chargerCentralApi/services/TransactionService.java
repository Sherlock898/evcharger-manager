package com.noder.chargerCentralApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.models.Transaction;
import com.noder.chargerCentralApi.models.Transaction.TransactionStatus;
import com.noder.chargerCentralApi.repositories.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> updateTransaction(Long id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(existingTransaction -> {
            existingTransaction.setStart_time(transactionDetails.getStart_time());
            existingTransaction.setEnd_time(transactionDetails.getEnd_time());
            existingTransaction.setStart_meter_value(transactionDetails.getStart_meter_value());
            existingTransaction.setEnd_meter_value(transactionDetails.getEnd_meter_value());
            existingTransaction.setStatus(transactionDetails.getStatus());
            existingTransaction.setConnector(transactionDetails.getConnector());
            return transactionRepository.save(existingTransaction);
        });
    }

    public boolean deleteTransaction(Long id) {
        if(transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Transaction> updateTransactionStatus(Long id, TransactionStatus status) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setStatus(status);
            return transactionRepository.save(transaction);
        });
    }
}
