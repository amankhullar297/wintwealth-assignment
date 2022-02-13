package com.wintweatlh.assignment.service;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.request.ProductHistoryRequest;
import com.wintweatlh.assignment.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ProductHistoryService {
    public Response add(ProductHistoryRequest request) throws BaseException;
    public Response add(MultipartFile file) throws BaseException;
    public Response get(Long productId, Integer limit) throws BaseException;
}
