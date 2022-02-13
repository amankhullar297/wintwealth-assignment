package com.wintweatlh.assignment.service;

import com.wintweatlh.assignment.exception.BaseException;

import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    public List<List<String>> readFromExcel(InputStream inputStream, int sheetNumber) throws BaseException;
}
