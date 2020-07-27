package com.target.myRetail.service;

import com.target.myRetail.domain.Price;
import com.target.myRetail.domain.ProductEntity;
import com.target.myRetail.exception.MyRetailException;
import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.repository.domain.Product;
import com.target.myRetail.serviceagent.ProductServiceAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
/**
 * Product Service layer
 * @author Preetika
 */
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Inject
    ProductServiceAgent productServiceAgent;
    @Inject
    ProductRepository productRepository;
/*
* This service fetches product name and price for product id
* */
    @Override
    public Response getProducts(String productId) throws Exception {
        log.info("Inside getProducts");
        ProductEntity productEntity = new ProductEntity();
        Response response = null;
        log.info("Fetching Product Price:");
        Product product = productRepository.findProductByproductId(productId);
        log.info("product:" + product);
        log.info("Fetching Product Name:");
        String productName = productServiceAgent.getProductName(productId);
        if(product!=null) {
            productEntity = buildResponse(product, productName);
        }
        response = Response.status(Response.Status.OK).entity(productEntity).build();
        log.info("Returning from getProducts service:" + response);
        return response;
    }

    private ProductEntity buildResponse(Product product, String productName) {
        Price price = new Price(product.getPrice().setScale(2, BigDecimal.ROUND_FLOOR), product.getCurrency());
        return new ProductEntity(product.getProductId(), productName, price);
    }
    /*
     * This service updates the product price for a given product id
     * */
    @Override
    public Response updateProduct(ProductEntity productEntity) throws Exception {
        log.info("Inside updateProduct");
        String responseMsg = null;
        try {
            Product product= productRepository.findProductByproductId(productEntity.getId());
            if(product==null){
                throw new MyRetailException(HttpStatus.BAD_REQUEST.value(),"Product Id not found");

            }
            product = getProductDao(productEntity);
            productRepository.save(product);
            responseMsg="Price Record Updated";
        }
        catch(Exception e){
            log.error("Error while saving to DB"+e);
        }
        Response response = Response.status(Response.Status.OK).entity(responseMsg).build();
        return response;
    }

    private Product getProductDao(ProductEntity productEntity) {
        Product product= new Product();
        product.setProductId(productEntity.getId());
        if(productEntity.getPrice()!=null) {
            product.setPrice(productEntity.getPrice().getValue());
            product.setCurrency(productEntity.getPrice().getCurrency());
        }
        return product;
    }

}
