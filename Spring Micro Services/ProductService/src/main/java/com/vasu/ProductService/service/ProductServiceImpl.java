package com.vasu.ProductService.service;

import com.vasu.ProductService.entity.Product;
import com.vasu.ProductService.expection.ProductException;
import com.vasu.ProductService.model.ProductRequest;
import com.vasu.ProductService.model.ProductResponse;
import com.vasu.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product..");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}", productId);

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductException("Product with given Id does not exist", "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product with given Id does not exist", "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity){
            throw new ProductException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }
}
