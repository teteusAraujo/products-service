package com.mateus.exceptions

import io.grpc.Status

class ProductNotFoundException(private val productId : Long) : BaseBusinessExceptions() {
    override fun erroMessage(): String {
        return "O produto com o ID $productId não existe ou não foi encontrado no nosso sistema"
    }

    override fun statusCode(): Status.Code {
        return Status.Code.NOT_FOUND
    }
}