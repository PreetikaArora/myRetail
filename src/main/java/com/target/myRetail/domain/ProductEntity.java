package com.target.myRetail.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * ProductEntity domain class.
 * @author Preetika
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEntity {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("current_Price")
    private Price price;

    public ProductEntity() {
    }

    public ProductEntity(String id, String productName, Price price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
