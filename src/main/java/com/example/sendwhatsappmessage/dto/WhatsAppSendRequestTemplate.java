package com.example.sendwhatsappmessage.dto;

import javax.annotation.Generated;

public class WhatsAppSendRequestTemplate {
    private String to;
    private String bodyVariable1;
    private String bodyVariable2;

    @Generated("SparkTools")
    private WhatsAppSendRequestTemplate(Builder builder) {
        this.to = builder.to;
        this.bodyVariable1 = builder.bodyVariable1;
        this.bodyVariable2 = builder.bodyVariable2;
    }

    public String getTo() {
        return to;
    }

    public String getBodyVariable1() {
        return bodyVariable1;
    }

    public String getBodyVariable2() {
        return bodyVariable2;
    }

    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder {
        private String to;
        private String bodyVariable1;
        private String bodyVariable2;

        private Builder() {
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder bodyVariable1(String bodyVariable1) {
            this.bodyVariable1 = bodyVariable1;
            return this;
        }

        public Builder bodyVariable2(String bodyVariable2) {
            this.bodyVariable2 = bodyVariable2;
            return this;
        }

        public WhatsAppSendRequestTemplate build() {
            return new WhatsAppSendRequestTemplate(this);
        }
    }
}
