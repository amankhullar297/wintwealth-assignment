package com.wintweatlh.assignment.controller;


import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.Product;
import com.wintweatlh.assignment.request.ProductHistoryRequest;
import com.wintweatlh.assignment.request.ProductRequest;
import com.wintweatlh.assignment.service.ProductHistoryService;
import com.wintweatlh.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductHistoryService productHistoryService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid  ProductRequest productRequest) throws BaseException {
        return new ResponseEntity(productService.add(productRequest), HttpStatus.OK);
    }

    @PostMapping("/history")
    public ResponseEntity<?> add(@RequestBody @Valid ProductHistoryRequest productHistoryRequest) throws BaseException {
        return new ResponseEntity(productHistoryService.add(productHistoryRequest), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> get(@RequestParam Long productId, @RequestParam String date) throws BaseException {
        return new ResponseEntity(productService.get(productId, date), HttpStatus.OK);
    }

    @GetMapping("/history/next-three-days")
    public ResponseEntity<?> add(@RequestParam Long productId) throws BaseException {
        return new ResponseEntity(productHistoryService.get(productId, 3), HttpStatus.OK);
    }

    @PostMapping("/history/upload")
    public ResponseEntity<?> add(@RequestParam("file") MultipartFile file) throws BaseException {
        return new ResponseEntity(productHistoryService.add(file), HttpStatus.OK);
    }
}
