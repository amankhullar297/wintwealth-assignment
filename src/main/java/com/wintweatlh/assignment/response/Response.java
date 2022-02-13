package com.wintweatlh.assignment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Response{
    Object response;
    String message;
}
