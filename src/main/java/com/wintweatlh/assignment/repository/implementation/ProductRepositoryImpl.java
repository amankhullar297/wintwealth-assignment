package com.wintweatlh.assignment.repository.implementation;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.Product;
import com.wintweatlh.assignment.model.ProductDetails;
import com.wintweatlh.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.time.LocalDate;

import static com.wintweatlh.assignment.constant.QueryConstants.Product.GET_BY_PRODUCT_ID_AND_DATE;
import static com.wintweatlh.assignment.constant.QueryConstants.Product.INSERT_QUERY;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Product add(Product product) throws BaseException {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setDate(2, Date.valueOf(product.getStartDate()));
                    preparedStatement.setDate(3, Date.valueOf(product.getMaturityDate()));
                    preparedStatement.setFloat(4, product.getInterest());

                    return preparedStatement;
                }
            }, holder);
        }
        catch (DataAccessException exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }
        catch (Exception exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }

        product.setId(holder.getKey().longValue());
        return product;
    }

    @Override
    public ProductDetails get(Long productId, LocalDate date) throws BaseException {
        try {
            return jdbcTemplate.queryForObject(GET_BY_PRODUCT_ID_AND_DATE, new BeanPropertyRowMapper<ProductDetails>(ProductDetails.class), productId, Date.valueOf(date));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
        catch (DataAccessException exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }
        catch (Exception exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }
    }
}
