package com.target.myRetail.api.resource;

import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the product resource class.
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResourceTest{
    @LocalServerPort
    protected int port;

    protected URI baseUri;

    protected Client client;

    @Before
    public void setUp() throws Exception {
        this.baseUri = new URI("http://localhost:" + port + "/api");
        this.client = ClientBuilder.newClient();
    }
    @Test
    public void getProductsNoData() {
        String productId="12345";
        Response response = client.target(baseUri).path("products").path(productId).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        ProductEntity productEntity= response.readEntity(ProductEntity.class);
        assertEquals(productEntity.getId(),null);
    }

    @Test
    public void getProductsSuccess() {
        String productId="13860428";
        Response response = client.target(baseUri).path("products").path(productId).request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        ProductEntity productEntity= response.readEntity(ProductEntity.class);
        assertEquals(productEntity.getId(),productId);
        assertEquals(productEntity.getProductName(),null);
        assertEquals(productEntity.getPrice().getValue(),new BigDecimal(13.49).setScale(2, BigDecimal.ROUND_FLOOR));
        assertEquals(productEntity.getPrice().getCurrency(),"USD");
    }

    @Test
    public void updateProduct() {
        String productId="13860428";
        Price price= new Price(new BigDecimal(12.45),"EUR");
        ProductEntity productEntity= new ProductEntity(productId,"The Big Lebowski (Blu-ray)",price);
        Response response = client.target(baseUri).path("products").path(productId).request().put(Entity.entity(productEntity, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String responseStr= response.readEntity(String.class);
        assertEquals(responseStr,"Price Record Updated");
    }
}
