package com.example.sendwhatsappmessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class WhatsappSendHttpRequestTemplateConfirmationBodyModel {
    @JsonProperty("messaging_product")
    private String messagingProduct = "whatsapp";

    @JsonProperty("to")
    private String to;

    @JsonProperty("type")
    private String type = "template";

    @JsonProperty("template")
    private WhatsappSendHttpRequestTemplateConfirmationBodyModel.Template template;

    public WhatsappSendHttpRequestTemplateConfirmationBodyModel(String to, String templateName) {
        this.to = to;
        this.template = new WhatsappSendHttpRequestTemplateConfirmationBodyModel.Template(templateName);
    }

    public static class Template {
        @JsonProperty("name")
        private String name;

        @JsonProperty("language")
        private WhatsappSendHttpRequestTemplateConfirmationBodyModel.Language language = new WhatsappSendHttpRequestTemplateConfirmationBodyModel.Language("fr");


        public Template(String name) {
            this.name = name;
        }
    }

    public static class Language {
        @JsonProperty("code")
        private String code;

        public Language(String code) {
            this.code = code;
        }
    }

}
