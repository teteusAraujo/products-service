package com.mateus.service.impl

import com.mateus.domain.Product
import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductResponse
import com.mateus.dto.ProductUpdateRequest
import com.mateus.exceptions.AlreadyExistsException
import com.mateus.exceptions.ProductNotFoundException
import com.mateus.repository.ProductRepository
import com.mateus.service.ProductService
import com.mateus.utils.toProduct
import com.mateus.utils.toProductRes
import com.mateus.utils.toProductUpdate
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl ( private val productRepository: ProductRepository) : ProductService {

    override fun create(request: ProductRequest): ProductResponse {
        verifyName(request.name)
        val productSaved = productRepository.save(request.toProduct())
       return productSaved.toProductRes()
    }

    private fun verifyName(name : String){
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }

    override fun findById(id: Long): ProductResponse {
       val findById = productRepository.findById(id)
        findById.orElseThrow{
            ProductNotFoundException(id)
        }
        return findById.get().toProductRes()
    }

    override fun update(request: ProductUpdateRequest): ProductResponse {
        verifyName(request.name)
        val product = productRepository.findById(request.id).orElseThrow {
            ProductNotFoundException(request.id)
        }
        val copyProduct = product.copy(
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity
        )
        return productRepository.update(copyProduct).toProductRes()


    }

    override fun delete(id: Long) {
        productRepository.deleteById(id)
    }


}