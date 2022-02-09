package com.mateus.dto

data class ProductUpdateRequest(
    val id : Long,
    val name : String ,
    val price : Double,
    val stockQuantity : Int
)
