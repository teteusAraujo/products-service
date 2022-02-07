package com.mateus.service

import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductResponse
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
interface ProductService {
    fun create(request: ProductRequest) : ProductResponse
    fun findById(id: Long) : ProductResponse
}