package com.wintweatlh.assignment.request;

import com.wintweatlh.assignment.model.ProductHistory;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NotNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistoryRequest {
    @NotNull
    @NotEmpty
    private List<@Valid ProductHistoryRequestModel> productHistory;
}