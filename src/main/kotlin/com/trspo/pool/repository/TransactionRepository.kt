package com.trspo.pool.repository

import com.trspo.pool.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TransactionRepository: JpaRepository<Transaction,UUID> {

    @Query("SELECT * FROM transaction GROUP BY id,RANDOM() LIMIT :amount",nativeQuery = true)
    fun getTransactionsBatch(amount:Int):List<Transaction>

}