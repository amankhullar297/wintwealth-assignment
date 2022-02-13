package com.wintweatlh.assignment.repository;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.ProductHistory;

import java.time.LocalDate;
import java.util.List;

public interface ProductHistoryRepository {
    public void add(List<ProductHistory> productHistories) throws BaseException;
    public List<ProductHistory> get(LocalDate date, Long productId, int limit) throws BaseException;
}
