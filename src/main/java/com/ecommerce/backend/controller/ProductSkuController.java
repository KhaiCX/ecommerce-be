package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.ProductSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api")
public class ProductSkuController {
    private final ProductSkuService productSkuService;

//    @GetMapping("/product-skus")
//    public ResponseEntity<Void> addProductSkus(@RequestBody ProductSkuRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(productSkuService.addProductSku(request));
//    }
}
