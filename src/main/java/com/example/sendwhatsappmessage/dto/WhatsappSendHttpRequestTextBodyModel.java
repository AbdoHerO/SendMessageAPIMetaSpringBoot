package com.example.sendwhatsappmessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WhatsappSendHttpRequestTextBodyModel {

    @JsonProperty("messaging_product")
    private String messagingProduct = "whatsapp";

    @JsonProperty("to")
    private String to;

    @JsonProperty("type")
    private String type = "text";

    @JsonProperty("text")
    private Text text;

    // Constructor with previewUrl defaulting to true
    public WhatsappSendHttpRequestTextBodyModel(String to, String messageContent) {
        this(to, messageContent, true);  // Call the other constructor with default value for previewUrl
    }

    // Constructor with explicit previewUrl
    public WhatsappSendHttpRequestTextBodyModel(String to, String messageContent, boolean previewUrl) {
        this.to = to;
        this.text = new Text(previewUrl, messageContent);
    }

    public static class Text {
        @JsonProperty("preview_url")
        private boolean previewUrl;

        @JsonProperty("body")
        private String body;

        public Text(boolean previewUrl, String body) {
            this.previewUrl = previewUrl;
            this.body = body;
        }
    }
}