package com.trspo.pool.service

import com.google.protobuf.Empty
import com.trspo.grpc.transaction.TransactionBatchRequest
import com.trspo.grpc.transaction.TransactionBatchResponse
import com.trspo.grpc.transaction.TransactionServiceGrpc
import com.trspo.pool.entity.Transaction
import com.trspo.pool.repository.TransactionRepository
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired

@GrpcService
class TransactionHandlerService : TransactionServiceGrpc.TransactionServiceImplBase() {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    override fun getTransactions(request: Empty, responseObserver: StreamObserver<TransactionBatchResponse>) {
        val transactions = transactionRepository.getTransactionsBatch(5)
        val batchResponse = Transaction.toTransactionBatchResponse(transactions)

        responseObserver.onNext(batchResponse)
        responseObserver.onCompleted()
    }

    override fun returnTransactionsResponse(request: TransactionBatchRequest, responseObserver: StreamObserver<Empty>) {
        val transactionsToAdd: List<Transaction> = Transaction.fromTransactionBatch(request)

        for (transaction in transactionsToAdd) transactionRepository.save(transaction)

        responseObserver.onNext(Empty.newBuilder().build())
        responseObserver.onCompleted()
    }
}