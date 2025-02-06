package com.example.sendwhatsappmessage.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import javax.annotation.Generated;

@Getter
public class WhatsAppDynamicTemplateRequest {
    private String to;
    private String templateName;
    private List<String> parameters;


    // @JsonCreator for constructor-based deserialization
    @JsonCreator
    public WhatsAppDynamicTemplateRequest(
            @JsonProperty("to") String to,
            @JsonProperty("templateName") String templateName,
            @JsonProperty("parameters") List<String> parameters) {
        this.to = to;
        this.templateName = templateName;
        this.parameters = parameters;
    }

    @Generated("SparkTools")
    private WhatsAppDynamicTemplateRequest(Builder builder) {
        this.to = builder.to;
        this.templateName = builder.templateName;
        this.parameters = builder.parameters;
    }

    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Generated("SparkTools")
    public static final class Builder {
        private String to;
        private String templateName;
        private List<String> parameters;

        private Builder() {
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder templateName(String templateName) {
            this.templateName = templateName;
            return this;
        }

        public Builder parameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public WhatsAppDynamicTemplateRequest build() {
            return new WhatsAppDynamicTemplateRequest(this);
        }
    }
}