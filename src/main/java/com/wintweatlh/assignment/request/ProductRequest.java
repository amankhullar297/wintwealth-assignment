package com.wintweatlh.assignment.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class ProductRequest {
   @NotNull
   @NotBlank
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    private Float interest;

    @NotNull
    @NotBlank
    private String startDate;

    @NotNull
    @NotBlank
    private String maturityDate;
}
