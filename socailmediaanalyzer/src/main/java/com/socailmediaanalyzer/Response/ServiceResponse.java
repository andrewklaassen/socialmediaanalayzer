package com.socailmediaanalyzer.Response;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T>{ 

    private final T data;
    private final boolean success;
    private final Integer total;
    private final Integer unfilteredTotal;
    private final List<ServiceError> errors;
    private final String id;
    
    public ServiceResponse() {
        this(null, false,0, 0, new ArrayList<>(), null);
    }

    public ServiceResponse(T data, boolean success, int total) {
        this(data, success, total,new ArrayList<>(), null);
    }

    public ServiceResponse(T data, boolean success, int total, int unfilteredTotal) {
        this(data, success, total, unfilteredTotal, new ArrayList<>(), null);
    }

    public ServiceResponse(T data, boolean success, int total, String id) {
        this(data, success, total, new ArrayList<>(), id);
    }
    
    public ServiceResponse(T data, boolean success, int total, List<ServiceError> errors) {
        this(data, success, total, errors, null);
    }

    public ServiceResponse(T data, boolean success, int total, List<ServiceError> errors, String id){
        this.data = data;
        this.success = success;
        this.total = total;
        this.unfilteredTotal = total;
        this.errors = errors;
        this.id = id;
    }

    public ServiceResponse(T data, boolean success, int total, int unfilteredTotal, List<ServiceError> errors, String id){
        this.data = data;
        this.success = success;
        this.total = total;
        this.unfilteredTotal = unfilteredTotal;
        this.errors = errors;
        this.id = id;
    }
    
    public T getData() { 
            return data; 
    } 

    public boolean isSuccess() {
            return success; 
    } 

    public Integer getTotal() { 
            return total; 
    }

    public Integer getUnfilteredTotal() {
        return unfilteredTotal;
    }

    public List<ServiceError> getErrors() {
            return errors;
    } 

    public String getId() {
            return id;
    }
}
