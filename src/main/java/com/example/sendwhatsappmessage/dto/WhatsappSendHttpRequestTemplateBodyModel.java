package com.example.sendwhatsappmessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Arrays;

public class WhatsappSendHttpRequestTemplateBodyModel {

    @JsonProperty("messaging_product")
    private String messagingProduct = "whatsapp";

    @JsonProperty("to")
    private String to;

    @JsonProperty("type")
    private String type = "template";

    @JsonProperty("template")
    private Template template;

    public WhatsappSendHttpRequestTemplateBodyModel(String to, String templateName, List<Parameter> bodyParams) {
        this.to = to;
        this.template = new Template(templateName, bodyParams);
    }

    public static class Template {
        @JsonProperty("name")
        private String name;

        @JsonProperty("language")
        private Language language = new Language("fr");

        @JsonProperty("components")
        private List<Component> components;

        public Template(String name, List<Parameter> bodyParams) {
            this.name = name;
            this.components = Arrays.asList(
                    new Component("body", bodyParams)  // Only body parameters
            );
        }
    }





    public static class Language {
        @JsonProperty("code")
        private String code;

        public Language(String code) {
            this.code = code;
        }
    }

    public static class Component {
        @JsonProperty("type")
        private String type;

        @JsonProperty("parameters")
        private List<Parameter> parameters;

        public Component(String type, List<Parameter> parameters) {
            this.type = type;
            this.parameters = parameters;
        }
    }

    public static class Parameter {
        @JsonProperty("type")
        private String type = "text";

        @JsonProperty("text")
        private String text;

        public Parameter(String text) {
            this.text = text;
        }
    }
}
