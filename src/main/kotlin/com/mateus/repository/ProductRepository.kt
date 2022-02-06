package com.mateus.repository

import com.mateus.domain.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
}