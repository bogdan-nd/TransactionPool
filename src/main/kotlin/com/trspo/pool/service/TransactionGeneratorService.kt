package com.trspo.pool.service

import com.trspo.pool.entity.Transaction
import com.trspo.pool.repository.TransactionRepository
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class TransactionGeneratorService {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Scheduled(fixedRate = 3000)
    public fun generateTransactions(){
        val transactionData = FillData.fillTransactionData()
        val newTransaction:Transaction = Transaction(data=transactionData)
        transactionRepository.save(newTransaction)
    }
}