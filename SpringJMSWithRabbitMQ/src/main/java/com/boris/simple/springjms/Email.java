package com.boris.simple.springjms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Email implements Serializable {
    private String from;
    private String to;
    private String topic;
    private String message;
}
