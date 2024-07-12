package com.sungjack.springbootmall.dao.impl;


import com.sungjack.springbootmall.dao.ProductDao;
import com.sungjack.springbootmall.dto.ProductQueryParams;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {

        String sql = "SELECT count(*) FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();


        //查詢條件

        sql = addFilterSql(sql, map, productQueryParams);


//在這邊第三個參數略有不從
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);


        return total;

    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
//原本的ProductCategory category,String search 改成 ProductQueryParams productQueryParams

        String sql = "select  product_id,product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date " +
                "from product where 1=1";

        Map<String, Object> map = new HashMap<>();


        //查詢條件

        sql= addFilterSql(sql, map, productQueryParams);




        //排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();


        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());


        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        return productList;


    }

    @Override
    public Product getProductById(Integer productid) {
        String sql = "select  product_id,product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date " +
                "from product where product_id=:productId\n";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productid);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());


        if (productList.size() > 0) {

            return productList.get(0);


        }
        return null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
//也會更新last_modified_date
        //要注意!!
        String sql = "update  product set product_name=:productName, category=:category, image_url=:imageUrl," +
                " price=:price, stock=:stock,description=:description, last_modified_date=:lastModifiedDate " +
                "where product_id=:productId";

        Map<String, Object> map = new HashMap<>();

        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());


        namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public void deleteProductById(Integer productId) {

        String sql = "delete from product where product_id=:productId";

        Map<String, Object> map = new HashMap();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }


    //如果只有在這個class才會用到的話，才會設成pravate，因為我們希望能夠更好的去管理程式的使用範圍
    private String addFilterSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams) {


        if (productQueryParams.getCategory() != null) {

            sql = sql + " AND category=:category";//這個空白鍵是非常重要的（這個只適用於　spring jdbc)
            map.put("category", productQueryParams.getCategory().name());

        }

        if (productQueryParams.getSearch() != null) {
            sql = sql + " AND product_name LIKE :search";//這個空白鍵是非常重要的（這個只適用於　spring jdbc)

            map.put("search", "%" + productQueryParams.getSearch() + "%");

        }
        return sql;

    }


}



