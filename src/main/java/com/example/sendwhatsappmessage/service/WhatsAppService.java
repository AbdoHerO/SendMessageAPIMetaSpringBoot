package com.example.sendwhatsappmessage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.access.token}")
    private String accessToken;

    @Value("${whatsapp.templatename}")
    private String templateName;

    private final RestTemplate restTemplate;

    public WhatsAppService() {
        this.restTemplate = new RestTemplate(); // Initialize RestTemplate
    }

    public String sendWhatsAppTemplateMessage(String recipientPhoneNumber, String[] templateVariables) {
        try {
            // Construct the JSON payload with template variables for the title (header) and body
            String jsonPayload = String.format(
                    "{" +
                            "\"messaging_product\": \"whatsapp\"," +
                            "\"to\": \"%s\"," +
                            "\"type\": \"template\"," +
                            "\"template\": {" +
                            "\"name\": \"%s\"," +
                            "\"language\": {\"code\": \"fr\"}," +  // Set the language as "fr" for French
                            "\"components\": [" +
                            "{" +
                            "\"type\": \"header\"," +
                            "\"parameters\": [" +
                            "{\"type\": \"text\", \"text\": \"%s\"}" + // First variable for the title (header)
                            "]" +
                            "}," +
                            "{" +
                            "\"type\": \"body\"," +
                            "\"parameters\": [" +
                            "{\"type\": \"text\", \"text\": \"%s\"}," + // First variable for the body
                            "{\"type\": \"text\", \"text\": \"%s\"}" +  // Second variable for the body
                            "]" +
                            "}" +
                            "]" +
                            "}" +
                            "}",
                    recipientPhoneNumber, templateName, templateVariables[0], templateVariables[1], templateVariables[2]); // Adjust the number of variables accordingly

            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);

            // Create HTTP entity with payload and headers
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

            // Send POST request
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            // Log the response for debugging
            System.out.println("Response status code: " + response.getStatusCodeValue());
            System.out.println("Response body: " + response.getBody());

            // Handle the response
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully. Response: " + response.getBody();
            } else {
                return "Failed to send message. Response: " + response.getBody();
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
