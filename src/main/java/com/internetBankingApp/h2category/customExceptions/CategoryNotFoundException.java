package com.internetBankingApp.h2category.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Category")
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
