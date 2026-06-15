package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.FakeStoreProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{


    //private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long id) {
        RestTemplate  restTemplate=restTemplateBuilder.build();
        //       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
//              restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
//                      FakeStoreProductDto.class,id);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null, FakeStoreProductDto.class,id);
        if(isValidateFakeStoreResponse(fakeStoreProductDtoResponseEntity)){
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}"
                ,from(product),FakeStoreProductDto.class,id);
        if(isValidateFakeStoreResponse(fakeStoreProductDtoResponseEntity)){
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;

    }
    public Product createProduct(Product product) {
        ResponseEntity<FakeStoreProductDto> response= requestForEntity(HttpMethod.POST,"https://fakestoreapi.com/products"
                ,from(product),FakeStoreProductDto.class);
        if(response.hasBody() && response.getStatusCode().equals(HttpStatus.CREATED)){
            return from(response.getBody());
        }
        return null;
    }

    @Override

    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity= requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products"
                ,null,FakeStoreProductDto[].class);
        if(fakeStoreProductDtoResponseEntity.hasBody() && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.OK) ){
            FakeStoreProductDto[] dtoArray=fakeStoreProductDtoResponseEntity.getBody();
            List<Product> p=new ArrayList<>();

            for(FakeStoreProductDto a:dtoArray){
                Product product=from(a);
                p.add(product);

            }
            return p;
        }
        return null;

    }

     //


    private Boolean isValidateFakeStoreResponse(ResponseEntity<FakeStoreProductDto>
                                                        fakeStoreProductDtoResponseEntity) {
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return true;
        }

        return false;
    }
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate  restTemplate=restTemplateBuilder.build();
        HttpHeaders heades=new HttpHeaders();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product p= new Product();
        Category c= new Category();
        c.setName(fakeStoreProductDto.getCategory());
        p.setCategory(c);
        p.setName(fakeStoreProductDto.getTitle());
        p.setDescription(fakeStoreProductDto.getDescription());
        p.setPrice(fakeStoreProductDto.getPrice());
        p.setImageUrl(fakeStoreProductDto.getImage());
        p.setId(fakeStoreProductDto.getId());
        return p;

    }
    private FakeStoreProductDto from(Product product){
        FakeStoreProductDto fd=new FakeStoreProductDto();
        fd.setTitle(product.getName());
        fd.setImage(product.getImageUrl());
        fd.setId(product.getId());
        fd.setPrice(product.getPrice());
        fd.setDescription(product.getDescription());
               if(product.getCategory()!=null){
            fd.setCategory(product.getCategory().getName());
        }


        return fd;
    }
}
