package com.target.myRetail.repository;


import com.target.myRetail.repository.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * This is the DAO layer to interact with the database and fetch the price and currency for the given product.
 *
 * @author Preetika
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    /**
     * This method queries the database and gets the price details for the given productId.
     *
     * @param productId Product id to be
     * @return Product
     */

    Product findProductByproductId(String productId);

}
