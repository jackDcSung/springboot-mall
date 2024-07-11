package com.sungjack.springbootmall.service;

import com.sungjack.springbootmall.constant.ProductCategory;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getproducts(ProductCategory category,String search);

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);

    //參數有兩個
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);



}
