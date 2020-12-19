package com.trspo.pool.service

import com.google.protobuf.Empty
import com.trspo.grpc.transaction.ReturnTransactionsRequest
import com.trspo.grpc.transaction.TransactionBatchResponse
import com.trspo.grpc.transaction.TransactionServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import lombok.AllArgsConstructor
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
@AllArgsConstructor
class TransactionHandlerService: TransactionServiceGrpc.TransactionServiceImplBase() {
    private val url: String = "localhost"
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress(url, 9090)
            .usePlaintext()
            .build()
    private val stub: TransactionServiceGrpc.TransactionServiceBlockingStub = TransactionServiceGrpc.newBlockingStub(channel)

    override fun getTransactions(request: Empty?, responseObserver: StreamObserver<TransactionBatchResponse>?) {

    }

    override fun returnTransactionsResponse(request: ReturnTransactionsRequest?, responseObserver: StreamObserver<Empty>?) {
        super.returnTransactionsResponse(request, responseObserver)
    }
}