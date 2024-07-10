package com.sungjack.springbootmall.dao.impl;


import com.sungjack.springbootmall.dao.ProductDao;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Product getProductById(Integer productid) {
        String sql = "select  product_id,product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date " +
                "from product where product_id=:productId\n";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productid);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());




        if(productList.size()>0){

            return productList.get(0);


        }
        return null;
    }


}
