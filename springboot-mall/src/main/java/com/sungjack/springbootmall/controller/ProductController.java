package com.sungjack.springbootmall.controller;


import com.sungjack.springbootmall.constant.ProductCategory;
import com.sungjack.springbootmall.dao.ProductQueryParams;
import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(

            //查詢條件 Filting
            @RequestParam(required = false) ProductCategory category,            //重點!代表category參數是可選的參數
            @RequestParam(required = false) String search,


            //排序 sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,


            /*
            分頁 Pagination(對應到MYSQL limit 和 offsset 的語法)
            想讓他們是可選的話，兩種方法
            required=false
            第二種是加上defaultvalue
            這邊用第二種作法
            */
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,//不可以比0小
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //defaultValue = "0",表示預設不跳過任何一筆數據 從第 一筆開始取


    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);





//        List<Product> productList = productService.getproducts(category, search);


        List<Product> productList = productService.getproducts(productQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        if (product != null) {

            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        }

    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {


        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
//之所以可以沿用，是因為裡面變數是永許前端去修改的

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
//檢查判斷商品是否存在的迴圈
        Product product = productService.getProductById(productId);

        if (product == null) {


            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//如果商品存在，更新商品，修改商品數據
//因為這個方法不會反而什麼值，所以直接這樣寫就可以了
        productService.updateProduct(productId, productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);


    }

    //為什麼不用檢查是否存在呢?
    //刪除商品的意義，對前端來說，是否不存在就好，存不存在對我沒有意義，他只要我們告訴她商品不在就好
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {


        productService.deleteProductById(productId);


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


}
