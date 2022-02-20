package com.mateus.exceptions

import io.grpc.Status

abstract class BaseBusinessExceptions : RuntimeException() {
    abstract fun erroMessage():String
    abstract fun statusCode(): Status.Code

}