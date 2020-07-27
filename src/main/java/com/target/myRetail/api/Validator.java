package com.target.myRetail.api;

import com.target.myRetail.domain.ProductEntity;
import com.target.myRetail.exception.MyRetailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/**
 * This is the Validator to validate mandatory params
 *
 * @author Preetika
 */
@Component
public class Validator {
    public void validateMandatoryParams(String propertyName, String propertyValue) throws IllegalArgumentException, MyRetailException {
        if (StringUtils.isEmpty(propertyValue)) {
            throw new MyRetailException(HttpStatus.BAD_REQUEST.value(),propertyName + " is mandatory. Please try again");
        }
    }

    public void validateMandatoryParams(ProductEntity productEntity) throws IllegalArgumentException, MyRetailException {
        validateMandatoryParams("Product Id",productEntity.getId());
        if(productEntity.getPrice()!=null) {
            validateMandatoryParams("Product Price", productEntity.getPrice().getValue().toString());
            validateMandatoryParams("Product Price Currency", productEntity.getPrice().getCurrency());
        }
        else {
            throw new IllegalArgumentException("Product Price is mandatory. Please try again");
        }
    }
}
