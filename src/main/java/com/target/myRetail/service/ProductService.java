package com.target.myRetail.service;

import com.target.myRetail.domain.ProductEntity;

import javax.ws.rs.core.Response;

public interface ProductService {
    Response getProducts(String productId) throws Exception;

    Response updateProduct(ProductEntity productEntity) throws Exception;
}
