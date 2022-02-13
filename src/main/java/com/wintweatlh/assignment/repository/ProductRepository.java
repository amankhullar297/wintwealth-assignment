package com.wintweatlh.assignment.repository;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.Product;
import com.wintweatlh.assignment.model.ProductDetails;
import com.wintweatlh.assignment.response.Response;

import java.time.LocalDate;

public interface ProductRepository  {
    public Product add(Product product) throws BaseException;
    public ProductDetails get(Long productId, LocalDate date) throws BaseException;
}
