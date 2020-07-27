package com.target.myRetail.api.resource;

import com.target.myRetail.api.Validator;
import com.target.myRetail.domain.ProductEntity;
import com.target.myRetail.exception.MyRetailException;
import com.target.myRetail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
/**
 * This is the Resource layer
 *
 * @author Preetika
 */
@Resource
@Path("products")
public class ProductResource {
    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Inject
    Validator validator;
    @Context
    private UriInfo uriInfo;
    @Inject
    private ProductService productService;

    /**
     * Get Product Details: Product Name and Current Price from Product Id
     *
     * @return Product Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{productId}")
    public Response getProducts(@PathParam("productId") String productId) {
        HashMap<String, String> hashMap = new HashMap<>();
        log.info("Inside getProducts");
        log.info("URI:" + uriInfo.getRequestUri().toString());
        Response response = null;
        try {
            validator.validateMandatoryParams("Product Id", productId);
            response = productService.getProducts(productId);
        }
        catch(MyRetailException e){
            log.debug("Inside Exception  " + e);
            log.debug(e.getErrorMessage(),e);
            Response.status(e.getErrorCode()).entity(e.getErrorMessage()).build();
        }
        catch (Exception e) {
            log.error("Error while executing getProducts service"+e);
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while executing getProducts service").build();
        }
        return response;
    }

    /**
     * Update Product Price
     *
     * @return Product Response
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{productId}")
    public Response updateProductPrice(@PathParam("productId") String productId, ProductEntity productEntity) {
        HashMap<String, String> hashMap = new HashMap<>();
        log.info("Inside getProducts");
        log.info("URI:" + uriInfo.getRequestUri().toString());
        Response response = null;
        try {
            validator.validateMandatoryParams(productEntity);
            response = productService.updateProduct(productEntity);
        } catch (Exception e) {
            log.error("Error while executing getProducts service");
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while executing updateProductPrice service").build();
        }
        return response;
    }

}
