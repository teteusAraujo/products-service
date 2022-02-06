package com.mateus.service.impl

import com.mateus.domain.Product
import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductResponse
import com.mateus.repository.ProductRepository
import com.mateus.service.ProductService

class ProductServiceImpl ( private val productRepository: ProductRepository) : ProductService {

    override fun create(request: ProductRequest): ProductResponse {
        val productReq = Product(
            id = null,
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity
        )
        val productSaved = productRepository.save(productReq)

       return ProductResponse(
            id = productSaved.id!!,
            name = productSaved.name,
            price = productSaved.price,
            stockQuantity = productSaved.stockQuantity
        )

    }
}