package com.wintweatlh.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ProductHistory {
    private Long id;
    private Long productId;
    private LocalDate date;
    private Double price;
}
