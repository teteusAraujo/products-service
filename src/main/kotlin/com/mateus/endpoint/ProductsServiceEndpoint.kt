package com.mateus.endpoint

import com.mateus.*
import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductUpdateRequest
import com.mateus.exceptions.BaseBusinessExceptions
import com.mateus.service.ProductService
import com.mateus.utils.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductsServiceEndpoint(private val productService: ProductService): ProductsServiceGrpc.ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
       try {
           val payload = ValidationUtil.validatePayload(request)
           val productReq = ProductRequest(
               name = payload.name,
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
           responseObserver?.onCompleted()
       } catch (ex: BaseBusinessExceptions){
           responseObserver?.onError(ex.statusCode().toStatus()
               .withDescription(ex.erroMessage()).asRuntimeException())
       }
    }

    override fun findById(request: ProductRequestById?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
            val response = productService.findById(request!!.id)
            val productRes = ProductServiceResponse.newBuilder()
                .setId(response.id)
                .setName(response.name)
                .setPrice(response.price)
                .setStockQuantity(response.stockQuantity)
                .build()

            responseObserver!!.onNext(productRes)
            responseObserver.onCompleted()
        } catch (ex: BaseBusinessExceptions){
            responseObserver?.onError(ex.statusCode().toStatus()
                .withDescription(ex.erroMessage()).asRuntimeException())
        }
    }

    override fun update(request: ProductServiceUpdateRequest?,
        responseObserver: StreamObserver<ProductServiceResponse>?) {
        val productReq = ProductUpdateRequest(
            id = request!!.id,
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity
        )
        val response = productService.update(productReq)
        val productRes = ProductServiceResponse.newBuilder()
            .setId(response.id)
            .setName(response.name)
            .setPrice(response.price)
            .setStockQuantity(response.stockQuantity)
            .build()
        responseObserver!!.onNext(productRes)
        responseObserver.onCompleted()


    }

    override fun delete(request: ProductRequestById?, responseObserver: StreamObserver<Empty>?) {
        productService.delete(request!!.id)
        responseObserver?.onNext(Empty.newBuilder().build())
        responseObserver!!.onCompleted()
    }
}