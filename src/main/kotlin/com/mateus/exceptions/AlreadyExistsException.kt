package com.mateus.exceptions

import io.grpc.Status

class AlreadyExistsException(private val productName : String) : BaseBusinessExceptions() {
    override fun erroMessage(): String {
        return "O produto $productName já está cadastradao no sistema"
    }

    override fun statusCode(): Status.Code {
        return Status.Code.ALREADY_EXISTS
    }
}