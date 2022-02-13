package com.wintweatlh.assignment.service.implementation;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.ProductHistory;
import com.wintweatlh.assignment.repository.ProductHistoryRepository;
import com.wintweatlh.assignment.request.ProductHistoryRequest;
import com.wintweatlh.assignment.request.ProductHistoryRequestModel;
import com.wintweatlh.assignment.response.Response;
import com.wintweatlh.assignment.service.ExcelService;
import com.wintweatlh.assignment.service.ProductHistoryService;
import com.wintweatlh.assignment.utility.StringToDateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {
    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Autowired
    private ExcelService excelService;

    @Override
    public Response add(ProductHistoryRequest request) throws BaseException {
        List<ProductHistory> productHistories = new ArrayList<>();

        for(ProductHistoryRequestModel productHistoryRequestModel : request.getProductHistory()){
            LocalDate date = StringToDateConversion.convert(productHistoryRequestModel.getDate(), "d-MMM-yyyy", "date");
            ProductHistory productHistory = ProductHistory.builder()
                    .productId(productHistoryRequestModel.getProductId())
                    .price(productHistoryRequestModel.getPrice())
                    .date(date).build();

            productHistories.add(productHistory);
        }

        productHistoryRepository.add(productHistories);
        return Response.builder().message("Successfully added product history").build();
    }

    @Override
    public Response add(MultipartFile file) throws BaseException {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        }catch (IOException ioException){
           throw BaseException.builder().errorInfo(ioException.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        }
        List<List<String>> objects = excelService.readFromExcel(inputStream, 0);
        List<ProductHistoryRequestModel> productHistoryRequestModels = new ArrayList<>();
        for(int i = 1; i < objects.size(); i++){
            List<String> objectList = objects.get(i);
            ProductHistoryRequestModel model = ProductHistoryRequestModel.builder()
                    .productId(Long.parseLong(objectList.get(2)))
                    .date((String)objectList.get(0))
                    .price(Double.parseDouble(objectList.get(1))).build();

            productHistoryRequestModels.add(model);
        }

        return add(ProductHistoryRequest.builder().productHistory(productHistoryRequestModels).build());
    }

    @Override
    public Response get(Long productId, Integer limit) throws BaseException {
        if(Objects.isNull(productId))
            throw BaseException.builder().errorInfo("product id cannot be null.").status(HttpStatus.BAD_REQUEST).build();

        if(Objects.isNull(limit))
            throw BaseException.builder().errorInfo("limit cannot be null").status(HttpStatus.BAD_REQUEST).build();

        List<ProductHistory> histories = productHistoryRepository.get(LocalDate.now(), productId, limit);
        return Response.builder().response(histories).build();
    }
}