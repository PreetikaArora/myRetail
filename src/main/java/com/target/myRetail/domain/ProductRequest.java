package com.target.myRetail.domain;

import javax.ws.rs.QueryParam;
/**
 * ProductRequest class to fetch Product Name.
 * @author Preetika
 */
public class ProductRequest {

    private String productId;
    @QueryParam("fields")
    private String fields;
    @QueryParam("id_type")
    private String idType;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productId='" + productId + '\'' +
                ", fields='" + fields + '\'' +
                ", idType='" + idType + '\'' +
                '}';
    }
}
