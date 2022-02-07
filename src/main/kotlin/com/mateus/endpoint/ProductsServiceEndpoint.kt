package com.mateus.endpoint

import com.mateus.ProductRequestById
import com.mateus.ProductServiceRequest
import com.mateus.ProductServiceResponse
import com.mateus.ProductsServiceGrpc
import com.mateus.dto.ProductRequest
import com.mateus.service.ProductService
import com.mateus.utils.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductsServiceEndpoint(private val productService: ProductService): ProductsServiceGrpc.ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        val payload = ValidationUtil.validatePayload(request)
        val productReq = ProductRequest(
            name = payload!!.name,
            price = payload.price,
            stockQuantity = payload.stockQuantity
        )
        val productSaved = productService.create(productReq)

        val productRes = ProductServiceResponse.newBuilder()
            .setId(productSaved.id)
            .setName(productSaved.name)
            .setPrice(productSaved.price)
            .setStockQuantity(productSaved.stockQuantity)
            .build()

        responseObserver!!.onNext(productRes)
        responseObserver.onCompleted()
    }

    override fun findById(request: ProductRequestById?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        val response = productService.findById(request!!.id)
        val productRes = ProductServiceResponse.newBuilder()
            .setId(response.id)
            .setName(response.name)
            .setPrice(response.price)
            .setStockQuantity(response.stockQuantity)
            .build()

        responseObserver!!.onNext(productRes)
        responseObserver.onCompleted()
    }
}