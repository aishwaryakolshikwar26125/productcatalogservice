package org.example.productcatalogservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import javax.swing.text.AbstractDocument;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetByProductId_whereProductIsNotFound_ReturnsNotFoundResponse() throws Exception {
    mockMvc.perform(get("/products/1")).andExpect(status().isNotFound());
    }

    @Test
    public void Test_GetByProductId_WithValidateId_ReturnsProperResponse() throws Exception {


        //Arrange
        Long productId = 2L;
        Product product = new Product();
        product.setName("Iphone 16PRO");
        product.setId(productId);

        ProductDto productDto=new ProductDto();
        productDto.setName("Iphone 16PRO");
        productDto.setId(productId);

        when(productService.getProductById(productId)).thenReturn(product);

        String expecptedString=objectMapper.writeValueAsString(productDto);
        mockMvc.perform(get("/products/2"))//act

                .andExpect(status().isOk())//assert
                .andExpect(content().string(expecptedString));

    }

    @Test
    public void Test_CreateProduct_returnsProperResponse() throws Exception {
        Long productId = 2L;
        ProductDto productDto=new ProductDto();
        productDto.setName("Iphone 16PRO");
        productDto.setId(productId);

        Product product = new Product();
        product.setName("Iphone 16PRO");
        product.setId(productId);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        String expecptedString=objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(expecptedString))
                .andExpect(status().isCreated())
                .andExpect(content().string(expecptedString));




    }

}