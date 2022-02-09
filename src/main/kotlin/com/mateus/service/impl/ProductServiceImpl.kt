package com.mateus.service.impl

import com.mateus.domain.Product
import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductResponse
import com.mateus.dto.ProductUpdateRequest
import com.mateus.repository.ProductRepository
import com.mateus.service.ProductService
import com.mateus.utils.toProduct
import com.mateus.utils.toProductRes
import com.mateus.utils.toProductUpdate
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl ( private val productRepository: ProductRepository) : ProductService {

    override fun create(request: ProductRequest): ProductResponse {
        val productSaved = productRepository.save(request.toProduct())
       return productSaved.toProductRes()
    }

    override fun findById(id: Long): ProductResponse {
        return productRepository.findById(id).get().toProductRes()
    }

    override fun delete(id: Long) {
        productRepository.deleteById(id)
    }


}