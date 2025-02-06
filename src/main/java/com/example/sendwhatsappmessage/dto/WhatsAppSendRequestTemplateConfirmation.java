package com.example.sendwhatsappmessage.dto;

import javax.annotation.Generated;

public class WhatsAppSendRequestTemplateConfirmation {
    private String to;
    private String bodyVariable1;
    private String bodyVariable2;

    @Generated("SparkTools")
    private WhatsAppSendRequestTemplateConfirmation(WhatsAppSendRequestTemplateConfirmation.Builder builder) {
        this.to = builder.to;
    }

    public String getTo() {
        return to;
    }

    @Generated("SparkTools")
    public static WhatsAppSendRequestTemplateConfirmation.Builder builder() {
        return new WhatsAppSendRequestTemplateConfirmation.Builder();
    }

    @Generated("SparkTools")
    public static final class Builder {
        private String to;

        private Builder() {
        }

        public WhatsAppSendRequestTemplateConfirmation.Builder to(String to) {
            this.to = to;
            return this;
        }

        public WhatsAppSendRequestTemplateConfirmation build() {
            return new WhatsAppSendRequestTemplateConfirmation(this);
        }
    }
}
