package com.example.sendwhatsappmessage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResult {
    private String to;
    private String status; // "SUCCESS" or "FAILED"
    private String details; // API response or error
}