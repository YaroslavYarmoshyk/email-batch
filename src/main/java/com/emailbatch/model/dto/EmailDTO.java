package com.emailbatch.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class EmailDTO {
    private String email;
    private Boolean emailSent;
}
