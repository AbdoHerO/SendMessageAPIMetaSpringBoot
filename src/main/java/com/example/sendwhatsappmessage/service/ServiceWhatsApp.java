package com.example.sendwhatsappmessage.service;

import com.example.sendwhatsappmessage.dto.*;
import com.example.sendwhatsappmessage.dto.WhatsappSendHttpRequestTemplateBodyModel.Parameter;
//import com.example.sendwhatsappmessage.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceWhatsApp {

    private final Logger logger = LoggerFactory.getLogger(ServiceWhatsApp.class);

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.access.token}")
    private String accessToken;

    @Value("${whatsapp.templatename}")
    private String templateName;


    @Value("${whatsapp.templatename.confirmation}")
    private String templateConfirmationName;

    private final RestTemplate restTemplate;

    public ServiceWhatsApp() {
        this.restTemplate = new RestTemplate(); // Initialize RestTemplate
    }


//    public String sendWhatsAppTextMessage(String recipientPhoneNumber, String messageContent) {
//        try {
//            // Construct the JSON payload for a text message
//            String jsonPayload = String.format(
//                    "{" +
//                            "\"messaging_product\": \"whatsapp\"," +
//                            "\"to\": \"%s\"," +
//                            "\"type\": \"text\"," +
//                            "\"text\": {\"preview_url\": true, \"body\": \"%s\"}" +
//                            "}",
//                    recipientPhoneNumber, messageContent);
//
//            // Create headers
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + accessToken);
//
//            // Create HTTP entity with payload and headers
//            HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
//
//            // Send POST request
//            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
//
//            // Log the response for debugging
//            logger.info("Response status code: {}", response.getStatusCodeValue());
//            logger.info("Response body: {}", response.getBody());
//
//            // Handle response
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return "Message sent successfully. Response: " + response.getBody();
//            } else {
//                logger.warn("API response not OK. Status code: {}", response.getStatusCode());
//                return "Failed to send message. Response: " + response.getBody();
//            }
//        } catch (Exception e) {
//            logger.error("Error sending WhatsApp message", e);
//            return "An error occurred: " + e.getMessage();
//        }
//    }




    public String sendWhatsAppTextMessage(WhatsAppSendRequestText request) {
        try {
            // Create request body model
            WhatsappSendHttpRequestTextBodyModel requestBody = new WhatsappSendHttpRequestTextBodyModel(
                    request.getTo(), request.getMessageContent());


            // Log the request body as JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(requestBody);
            logger.info("Sending WhatsApp message with payload: {}", jsonPayload);


            // Create headers
            HttpHeaders headers = buildHeaders();
            HttpEntity<WhatsappSendHttpRequestTextBodyModel> requestEntity = new HttpEntity<>(requestBody, headers);

            // Send POST request
            ResponseEntity<WhatsappSendHttpResponseTextBodyModel> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, requestEntity, WhatsappSendHttpResponseTextBodyModel.class);

            // Handle response
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully. Response: " + response.getBody();
            } else {
                logger.warn("API response not OK. Status code: {}", response.getStatusCode());
                return "Failed to send message. Response: " + response.getBody();
            }
        } catch (Exception e) {
            logger.error("Error sending WhatsApp message", e);
            return "An error occurred: " + e.getMessage();
        }
    }



    public String sendWhatsAppTemplateMessage(WhatsAppSendRequestTemplate request) {
        try {
            // Create request body model
            WhatsappSendHttpRequestTemplateBodyModel requestBody = new WhatsappSendHttpRequestTemplateBodyModel(
                    request.getTo(),
                    templateName,
                    Arrays.asList(
                            new Parameter(request.getBodyVariable1()),
                            new Parameter(request.getBodyVariable2())
                    ) // Only body parameters
            );

            // Create headers
            HttpHeaders headers = buildHeaders();
            HttpEntity<WhatsappSendHttpRequestTemplateBodyModel> requestEntity = new HttpEntity<>(requestBody, headers);

            // Send POST request
            ResponseEntity<WhatsappSendHttpResponseTemplateBodyModel> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, requestEntity, WhatsappSendHttpResponseTemplateBodyModel.class
            );

            // Log the response for debugging
            logProviderResponse(response.getBody());

            // Handle response
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully. Response: " + response.getBody();
            } else {
                logger.warn("API response not OK. Status code: {}", response.getStatusCode());
                return "Failed to send message. Response: " + response.getBody();
            }
        } catch (ResourceAccessException e) {
            logger.error("HTTP Resource Access Error: {}", e.getMessage());
            return "An error occurred: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Error sending WhatsApp message", e);
            throw new RuntimeException(e);
        }
    }


    public String sendWhatsAppTemplateMessageConfirmation(WhatsAppSendRequestTemplateConfirmation request) {
        try {
            // Create request body model
            WhatsappSendHttpRequestTemplateConfirmationBodyModel requestBody = new WhatsappSendHttpRequestTemplateConfirmationBodyModel(
                    request.getTo(),
                    templateConfirmationName
            );

            // Create headers
            HttpHeaders headers = buildHeaders();
            HttpEntity<WhatsappSendHttpRequestTemplateConfirmationBodyModel> requestEntity = new HttpEntity<>(requestBody, headers);

            // Send POST request
            ResponseEntity<WhatsappSendHttpResponseTemplateBodyModel> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, requestEntity, WhatsappSendHttpResponseTemplateBodyModel.class
            );

            // Log the response for debugging
            logProviderResponse(response.getBody());

            // Handle response
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully. Response: " + response.getBody();
            } else {
                logger.warn("API response not OK. Status code: {}", response.getStatusCode());
                return "Failed to send message. Response: " + response.getBody();
            }
        } catch (ResourceAccessException e) {
            logger.error("HTTP Resource Access Error: {}", e.getMessage());
            return "An error occurred: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Error sending WhatsApp message", e);
            throw new RuntimeException(e);
        }
    }



    private void logProviderResponse(WhatsappSendHttpResponseTemplateBodyModel response) {
        if (response == null) {
            logger.warn("WhatsappSendHttpResponseTemplateBodyModel is null");
            return;
        }
        logger.info("-------------------------------------");
        logger.info("Status: {}", response.getStatus());
        logger.info("Code: {}", response.getCode());
        logger.info("Id: {}", response.getId());
        logger.info("-------------------------------------");
    }


    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }

    public String sendDynamicTemplateMessage(WhatsAppDynamicTemplateRequest request) {
        try {
            List<Parameter> parameters = request.getParameters().stream()
                    .map(Parameter::new)
                    .collect(Collectors.toList());

            WhatsappSendHttpRequestTemplateBodyModel requestBody = new WhatsappSendHttpRequestTemplateBodyModel(
                    request.getTo(),
                    request.getTemplateName(),
                    parameters
            );

            HttpHeaders headers = buildHeaders();
            HttpEntity<WhatsappSendHttpRequestTemplateBodyModel> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<WhatsappSendHttpResponseTemplateBodyModel> response = restTemplate.exchange(
                    apiUrl, HttpMethod.POST, requestEntity, WhatsappSendHttpResponseTemplateBodyModel.class
            );

            logProviderResponse(response.getBody());

            if (response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully. Response: " + response.getBody();
            } else {
                logger.warn("API response not OK. Status code: {}", response.getStatusCode());
                return "Failed to send message. Response: " + response.getBody();
            }
        } catch (ResourceAccessException e) {
            logger.error("HTTP Resource Access Error: {}", e.getMessage());
            return "An error occurred: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Error sending WhatsApp message", e);
            throw new RuntimeException(e);
        }
    }

//    @Async
//    public Future<Boolean> sendNotificationWhatsApp(Notification notification) {
//        try {
//            // Create the request data for WhatsApp message based on Notification object
//            WhatsAppSendRequest whatsAppSendRequest = WhatsAppSendRequest.builder()
//                    .to(notification.getRecipientAddress())
//                    .headerVariable(notification.getSubject()) // Assuming headerVariable maps to subject
//                    .bodyVariable1(notification.getMessage()) // Map appropriately
//                    .bodyVariable2("Additional Body Variable") // Replace or remove as necessary
//                    .build();
//
//            // Send the WhatsApp message
//            String response = sendWhatsAppTemplateMessage(whatsAppSendRequest);
//            logger.info("WhatsApp notification sent with response: {}", response);
//
//            // Assume response indicates success if no exception occurred
//            return new AsyncResult<>(true);
//        } catch (Exception e) {
//            logger.error("Failed to send WhatsApp notification", e);
//            return new AsyncResult<>(false);
//        }
//    }
}
