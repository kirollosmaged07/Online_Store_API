//package com.vodafone.Online_Store.web.controller;
//import com.vodafone.Online_Store.core.domain.Product;
//import com.vodafone.Online_Store.core.service.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@WebMvcTest(ProductController.class)
//public class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ProductService productService;
//
//    @InjectMocks
//    private ProductController productController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllProducts() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testGetProductById() throws Exception {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Test Product");
//
//        when(productService.getProductById(1L)).thenReturn(product);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Product"));
//    }
//
//    @Test
//    public void testAddProduct() throws Exception {
//        Product product = new Product();
//        product.setName("New Product");
//
//        when(productService.addProduct(any(Product.class))).thenReturn(product);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(product)))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testUpdateProduct() throws Exception {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Updated Product");
//
//        when(productService.updateProduct(any(Product.class))).thenReturn(product);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(product)))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product"));
//    }
//
//    @Test
//    public void testDeleteProduct() throws Exception {
//        doNothing().when(productService).deleteProduct(1L);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//}