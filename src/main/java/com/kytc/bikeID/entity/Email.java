package com.kytc.bikeID.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Email {
    private Map<String, Object> Properties;
    private String from;
    private String to;
    private String subject;
    private String template;
}
