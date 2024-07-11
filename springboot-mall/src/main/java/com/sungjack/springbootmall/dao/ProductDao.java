package com.sungjack.springbootmall.dao;

import com.sungjack.springbootmall.constant.ProductCategory;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {


     Integer countProduct(ProductQueryParams productQueryParams) ;



    List<Product> getProducts(ProductQueryParams productQueryParams);


    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProductById(Integer productId);
}
