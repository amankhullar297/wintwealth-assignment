package com.wintweatlh.assignment.repository.implementation;

import com.wintweatlh.assignment.constant.QueryConstants;
import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.model.ProductHistory;
import com.wintweatlh.assignment.repository.ProductHistoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.wintweatlh.assignment.constant.QueryConstants.ProductHistory.GET_NEXT_THREE_DAYS_HISTORY_BY_PRODUCT_ID;

@Component
public class ProductHistoryRepositoryImpl implements ProductHistoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(List<ProductHistory> productHistories) throws BaseException {
        try {
             jdbcTemplate.batchUpdate(QueryConstants.ProductHistory.INSERT_QUERY,
                    new BatchPreparedStatementSetter() {
                        @Override
                        @SneakyThrows
                        public void setValues(PreparedStatement ps, int i){
                            try {
                                ProductHistory productHistory = productHistories.get(i);
                                ps.setDate(2, Date.valueOf(productHistory.getDate()));
                                ps.setLong(1, productHistory.getProductId());
                                ps.setDouble(3, productHistory.getPrice());
                            }catch (SQLException exception){
                                throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
                            }
                        }

                        @Override
                        public int getBatchSize() {
                            return productHistories.size();
                        }
                    });
        }
        catch (DataAccessException exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }
        catch (Exception exception){
            throw BaseException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorInfo(exception.getMessage()).build();
        }
    }

    @Override
    public List<ProductHistory> get(LocalDate date, Long productId, int limit) throws BaseException {
        try {
            return jdbcTemplate.query(GET_NEXT_THREE_DAYS_HISTORY_BY_PRODUCT_ID, new Object[]{productId, date, limit}, (rs, rowNum) -> ProductHistory.builder().date(rs.getDate("date").toLocalDate()).price(rs.getDouble("price")).build());
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
