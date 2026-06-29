package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void Test_GetProductById_WithPositiveID_ReturnProductSuccessfully() {

        //Arrange
        Long productId = 2l;
        Product product = new Product();
        product.setName("Iphone 16PRO");
        product.setId(productId);

        when(productService.getProductById(productId)).thenReturn(product);
        //act

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(productId);

        //assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(productId,productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone 16PRO",productDtoResponseEntity.getBody().getName());
    }

    @Test
    public void Test_GetProductById_WithNegativeID_ResultsInIllegalArgumentException() {

        //Arrange
        Long productId = -2L;

        //act and assert

       Exception exception= assertThrows(IllegalArgumentException.class,
                ()->productController.getProductById(productId));

        assertEquals("please pass positive productID",exception.getMessage());


    }
    @Test
    public void Test_GetProductById_WhereProductServiceReturnsNull_ReturnNullProduct() {

        //Arrange
        Long productId = 2l;
        Product product = null;



        when(productService.getProductById(productId)).thenReturn(product);

        //act

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(productId);

        //assert
        assertNotNull(productDtoResponseEntity);
        assertNull(productDtoResponseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND,productDtoResponseEntity.getStatusCode());



    }



}