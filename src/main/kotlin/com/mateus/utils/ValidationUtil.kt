package com.mateus.utils

import com.mateus.ProductServiceRequest

class ValidationUtil {

    companion object {
        fun validatePayload(payload: ProductServiceRequest?) : ProductServiceRequest{
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw IllegalArgumentException("O nome do produto não pode ser nulo ou vazio")
                if (it.price.isFinite())
                    throw IllegalArgumentException("Preço deve ser um valor valido")
                return it
            }
            return throw IllegalArgumentException()
        }
    }
}