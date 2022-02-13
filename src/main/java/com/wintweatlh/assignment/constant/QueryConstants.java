package com.wintweatlh.assignment.constant;

public class QueryConstants {
    public static class Product{
        public static final String INSERT_QUERY = "insert into product(name, start_date, maturity_date, interest) values(?, ?, ?, ?)";
        public static final String GET_BY_PRODUCT_ID_AND_DATE = "SELECT product.name, product_history.price, product.interest FROM product join product_history on (product.id = product_history.product_id) where product.id = ? AND product_history.date = ?";
    }

    public static class ProductHistory{
        public static final String INSERT_QUERY = "insert into product_history(product_id, date, price) values(?, ?, ?)";
        public static final String GET_NEXT_THREE_DAYS_HISTORY_BY_PRODUCT_ID = "select date, price FROM product_history where product_id = ? AND date > ? Limit ?";
    }
}
