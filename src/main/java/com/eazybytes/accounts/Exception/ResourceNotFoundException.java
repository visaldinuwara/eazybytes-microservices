package com.eazybytes.accounts.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldVolume){
        super(String.format("%s not found with the given input data %s:'%s'",resourceName,fieldName,fieldVolume));
    }
}
