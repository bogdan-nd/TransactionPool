package com.trspo.pool.entity

import java.util.*
import com.google.common.hash.Hashing.sha256
import java.nio.charset.StandardCharsets
import com.trspo.grpc.transaction.*
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Transaction(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
                name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator"
        )
        val id:UUID = UUID.randomUUID(),
        val data: String) {

    @Transient
    val transactionHash:String = hashData()

    private fun hashData(): String {
        val totalData: String = data + id

        return sha256()
                .hashString(totalData, StandardCharsets.UTF_8)
                .toString()
    }

    override fun toString(): String {
        return String.format("Transaction:\nid -> %s\ndata -> %s",id,data);
    }

    public fun fromTransactionMessage(response:TransactionMessage):Transaction{
        val transactionId:UUID = UUID.fromString(response.id)
        return Transaction(transactionId, response.data)
    }

    public fun toTransactionMessage():TransactionMessage{
        return TransactionMessage.newBuilder()
                .setId(id.toString())
                .setData(data)
                .build()
    }
}