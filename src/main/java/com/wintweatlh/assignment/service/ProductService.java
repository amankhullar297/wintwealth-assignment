package com.wintweatlh.assignment.service;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.Product;
import com.wintweatlh.assignment.request.ProductRequest;
import com.wintweatlh.assignment.response.Response;

public interface ProductService {
    public Response add(ProductRequest productRequest) throws BaseException;
    public Response get(Long productId, String date) throws BaseException;
}
