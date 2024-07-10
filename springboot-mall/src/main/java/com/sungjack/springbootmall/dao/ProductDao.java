package com.sungjack.springbootmall.dao;

import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);
}
