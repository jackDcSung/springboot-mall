package com.sungjack.springbootmall.service;

import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);



    Integer createProduct(ProductRequest productRequest);
}
