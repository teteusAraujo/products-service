package com.mateus.utils

import com.mateus.domain.Product
import com.mateus.dto.ProductRequest
import com.mateus.dto.ProductResponse

fun Product.toProductRes() : ProductResponse{
   return  ProductResponse(
        id = id!! ,
        name = name ,
        price = price ,
        stockQuantity = stockQuantity
    )
}

fun ProductRequest.toProduct(): Product{
    return Product(
        id = null,
        name = name,
        price= price,
        stockQuantity = stockQuantity
    )
}