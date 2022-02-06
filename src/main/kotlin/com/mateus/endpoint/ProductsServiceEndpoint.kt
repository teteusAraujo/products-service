package com.mateus.endpoint

import com.mateus.ProductServiceRequest
import com.mateus.ProductServiceResponse
import com.mateus.ProductsServiceGrpc
import com.mateus.dto.ProductRequest
import com.mateus.service.ProductService
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductsServiceEndpoint(private val productService: ProductService): ProductsServiceGrpc.ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        val productReq = ProductRequest(
            name = request!!.name,
            price = request.price,
            stockQuantity = request.stockQuantity
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
}