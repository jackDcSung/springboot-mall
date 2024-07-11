package com.sungjack.springbootmall.service.impl;


import com.sungjack.springbootmall.constant.ProductCategory;
import com.sungjack.springbootmall.dao.ProductDao;
import com.sungjack.springbootmall.dao.impl.ProductDaoImpl;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
private ProductDao productDao;


    @Override
    public List<Product> getproducts(ProductCategory category,String search) {
        return  productDao.getProducts(category,search);
    }

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {


        return productDao.createProduct(productRequest);

    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        productDao.updateProduct(productId,productRequest);

    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);

    }



}
