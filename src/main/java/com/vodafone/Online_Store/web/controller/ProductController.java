package com.vodafone.Online_Store.web.controller;

import com.vodafone.Online_Store.core.domain.Product;
import com.vodafone.Online_Store.core.domain.UserInfo;

import com.vodafone.Online_Store.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.equals("ADMIN"))) {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.ok(createdProduct);
        }
//        throw new HttpResponseException();
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.equals("ADMIN"))) {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.equals("ADMIN"))) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String category) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts(@RequestParam String category) {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/category")
//    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
//        List<Product> products = productService.getProductsByCategory(category);
//        return ResponseEntity.ok(products);
//    }
}
