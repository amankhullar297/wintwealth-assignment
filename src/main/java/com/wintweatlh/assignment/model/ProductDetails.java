package com.wintweatlh.assignment.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
    double price;
    float interest;
    String name;
}
