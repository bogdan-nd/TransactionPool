package com.trspo.pool.repository

import com.trspo.pool.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransactionRepository: JpaRepository<Transaction,UUID> {

}