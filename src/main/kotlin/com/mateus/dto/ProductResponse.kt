package com.mateus.dto

data class ProductResponse(
    val id : Long,
    val name : String ,
    val price : Double,
    val stockQuantity : Int
)
