package com.wintweatlh.assignment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NotNull
public class ProductHistoryRequestModel {
    @NotNull
    private Long productId;

    @NotNull
    @NotEmpty
    private String date;

    @NotNull
    private Double price;
}