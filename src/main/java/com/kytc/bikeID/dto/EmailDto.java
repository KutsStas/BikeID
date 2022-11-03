package com.kytc.bikeID.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EmailDto {
    private Map<String, Object> Properties;
    private String from;
    private String to;
    private String subject;
    private String template;
}
