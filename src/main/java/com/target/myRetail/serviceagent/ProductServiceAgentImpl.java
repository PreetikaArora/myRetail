package com.target.myRetail.serviceagent;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
/*
 * This service fetches the  product name for product id from External API
 * @author Preetika
 * */
@Service
public class ProductServiceAgentImpl implements ProductServiceAgent {

    private static final String MYRETAIL_PRODUCT_API = "https://apiv2.apifootball.com";
    @Value("${product-api-endpoint}")
    private String apiEndpointURL;
    @Value("${product-api-key}")
    private String apiKey;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceAgentImpl.class);
    /*
     * This method fetches the  product name for product id from an External API
     *
     * */
    @Override
    public String getProductName(String productId) throws IOException {
        log.info("Inside getProductName");
        String productName = null;
        try {
            Client client = ClientBuilder.newClient(new ClientConfig().register(JacksonFeature.class));
            WebTarget webTarget = client.target(apiEndpointURL)
                    .path(productId)
                    .queryParam("fields", "descriptions")
                    .queryParam("id_type", "TCIN")
                    .queryParam("key", apiKey);

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            log.info("webtarget:"+webTarget.getUri());
            Response response = invocationBuilder.get();
            if(response.getStatusInfo().getStatusCode()==HttpStatus.OK.value()) {
                productName = response.readEntity(String.class);
            }
            else {
                log.info("Error while connecting to Product Name API"+response.getStatusInfo().getStatusCode());
            }
        }
        catch(Exception e){
            log.error("Exception while connecting to product name API"+e);
        }
        log.info("Returning from getProductName:" + productName);
        return productName;
    }
}
