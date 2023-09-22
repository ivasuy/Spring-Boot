package com.vasu.ProductService.service;

import com.vasu.ProductService.model.ProductRequest;
import com.vasu.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
