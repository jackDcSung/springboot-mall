package com.sungjack.springbootmall.service;


import com.sungjack.springbootmall.dao.ProductQueryParams;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    //    List<Product> getproducts(ProductCategory category,String search);


    Integer countProduct(ProductQueryParams productQueryParams );

    List<Product> getproducts(ProductQueryParams productQueryParams);


    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);

    //參數有兩個
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
