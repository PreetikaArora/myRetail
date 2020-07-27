package com.target.myRetail.api.service;

import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.ProductEntity;
import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.repository.domain.Product;
import com.target.myRetail.service.ProductService;
import com.target.myRetail.service.ProductServiceImpl;
import com.target.myRetail.serviceagent.ProductServiceAgent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductServiceTest {
    @Mock
    ProductServiceAgent productServiceAgent;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductsTest() throws Exception{
        String productId="13860428";
        //Objects created for the actual Mock
        Product productMock = new Product(productId,"EUR",new BigDecimal(12.34));
        Mockito.when(productRepository.findProductByproductId(Mockito.anyString())).thenReturn(productMock);
        Mockito.when(productServiceAgent.getProductName(productId)).thenReturn("Blue Ray");
        Response response= productService.getProducts(productId);
        assertNotNull(response);
        }

    @Test
    public void updateProduct() throws Exception{
        String productId="13860428";
        Price price= new Price(new BigDecimal(12.45),"EUR");
        ProductEntity productEntity= new ProductEntity(productId,"The Big Lebowski (Blu-ray)",price);
        Response response= productService.updateProduct(productEntity);
        assertNotNull(response);
    }
}
