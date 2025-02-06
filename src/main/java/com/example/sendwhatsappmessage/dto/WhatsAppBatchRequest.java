package com.example.sendwhatsappmessage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WhatsAppBatchRequest {
    private List<WhatsAppDynamicTemplateRequest> messages;
}