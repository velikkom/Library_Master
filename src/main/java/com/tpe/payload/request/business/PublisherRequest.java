package com.tpe.payload.request.business;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class PublisherRequest
{
    @NotEmpty(message = "Name is required")
    private String name;
}
