package com.wintweatlh.assignment.controller;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.request.ProductHistoryRequest;
import com.wintweatlh.assignment.service.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product-history")
public class ProductHistoryController {
    @Autowired
    private ProductHistoryService productHistoryService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ProductHistoryRequest productHistoryRequest) throws BaseException {
        return new ResponseEntity(productHistoryService.add(productHistoryRequest), HttpStatus.OK);
    }
}
