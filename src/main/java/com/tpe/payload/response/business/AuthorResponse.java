package com.tpe.payload.response.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponse
{
    private Long id;
    private String name;
}
