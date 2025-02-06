package com.example.sendwhatsappmessage.dto;

import javax.annotation.Generated;

public class WhatsAppSendRequestText {
    private String to;
    private String messageContent;

    @Generated("SparkTools")
    private WhatsAppSendRequestText(Builder builder) {
        this.to = builder.to;
        this.messageContent = builder.messageContent;
    }

    public String getTo() {
        return to;
    }

    public String getMessageContent() {
        return messageContent;
    }

    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder {
        private String to;
        private String messageContent;

        private Builder() {
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder messageContent(String messageContent) {
            this.messageContent = messageContent;
            return this;
        }

        public WhatsAppSendRequestText build() {
            return new WhatsAppSendRequestText(this);
        }
    }
}