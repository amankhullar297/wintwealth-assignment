package com.wintweatlh.assignment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Product {
    private long id;
    private String name;
    private float interest;
    private LocalDate startDate;
    private LocalDate maturityDate;
}
