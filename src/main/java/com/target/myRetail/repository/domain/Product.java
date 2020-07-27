package com.target.myRetail.repository.domain;


//import com.datastax.driver.mapping.annotations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * This is a Data Object that contains the productId price and currency of a product.
 *
 * @author Preetika
 */

@Document(collection = "productPrice")
public class Product {

    @Id
    private String productId;

    private String currency;
    private BigDecimal price;

    public Product() {
    }

    public Product(String productId, String currency, BigDecimal price) {
        this.productId = productId;
        this.currency = currency;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
