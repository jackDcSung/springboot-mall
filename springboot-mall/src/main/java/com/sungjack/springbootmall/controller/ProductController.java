package com.sungjack.springbootmall.controller;


import com.sungjack.springbootmall.dto.ProductRequest;
import com.sungjack.springbootmall.model.Product;
import com.sungjack.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

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


}
