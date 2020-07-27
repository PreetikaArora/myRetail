/**
 *
 */
package com.target.myRetail.service;


import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.repository.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Inbuilt Data Store for Products
 * @author Preetika
 *
 */
@Service
public class DataLoaderService {

    @Autowired
    private ProductRepository productRepository;

    //constructor
    public DataLoaderService() {
    }

	@PostConstruct
    public void init() /*throws MyRetailException*/ {
        loadProductPriceInDB();
    }

    //Load the products in DB
    private void loadProductPriceInDB() {


        if (productRepository != null) {

            List<Product> productList = new ArrayList<Product>();
            Product product = new Product("13860428", "USD", new BigDecimal(13.49));
            productList.add(product);

            Product product2 = new Product("16752456", "USD", new BigDecimal(26.49));
            productList.add(product2);

            Product product3 = new Product("16752457", "USD", new BigDecimal(29.99));
            productList.add(product3);

            //Deleting any data before load
            productRepository.deleteAll();

            productRepository.save(productList);
        }
    }

}