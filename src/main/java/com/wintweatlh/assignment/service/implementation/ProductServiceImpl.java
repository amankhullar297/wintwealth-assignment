package com.wintweatlh.assignment.service.implementation;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.Product;
import com.wintweatlh.assignment.model.ProductDetails;
import com.wintweatlh.assignment.repository.ProductRepository;
import com.wintweatlh.assignment.request.ProductRequest;
import com.wintweatlh.assignment.response.Response;
import com.wintweatlh.assignment.service.ProductService;
import com.wintweatlh.assignment.utility.StringToDateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Response add(ProductRequest productRequest) throws BaseException {
        LocalDate startDate = StringToDateConversion.convert(productRequest.getStartDate(), "d-MMM-yyyy", "startDate");
        LocalDate maturityDate = StringToDateConversion.convert(productRequest.getMaturityDate(), "d-MMM-yyyy", "maturityDate");

        Product product = Product.builder()
                .interest(productRequest.getInterest())
                .startDate(startDate)
                .maturityDate(maturityDate)
                .name(productRequest.getName())
                .build();

        Product productResponse = productRepository.add(product);
        Response response = Response.builder().response(productResponse).message("Successfully added product").build();
        return response;
    }

    @Override
    public Response get(Long productId, String date) throws BaseException {
        if(Objects.isNull(productId))
            throw BaseException.builder().errorInfo("product id cannot be null.").status(HttpStatus.BAD_REQUEST).build();

        if(Objects.isNull(date) || date.isEmpty())
            throw BaseException.builder().errorInfo("date cannot be null or empty.").status(HttpStatus.BAD_REQUEST).build();

        LocalDate localDate = StringToDateConversion.convert(date, "d-MMM-yyyy", "date");

        ProductDetails details = productRepository.get(productId, localDate);
        return Response.builder().response(details).build();
    }
}
