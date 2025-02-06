package com.example.sendwhatsappmessage.controller;

import com.example.sendwhatsappmessage.dto.*;
//import com.example.sendwhatsappmessage.model.Notification;
import com.example.sendwhatsappmessage.service.ServiceWhatsApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api")
public class WhatsAppController {

    @Autowired
    private ServiceWhatsApp serviceWhatsApp;


    @PostMapping("/sendTextMessage")
    public String sendWhatsAppTextMessage(
            @RequestParam String to,
            @RequestParam String messageContent) {
//        return serviceWhatsApp.sendWhatsAppTextMessage(to, messageContent); // Manually JSON pyload


        // Build the request
        WhatsAppSendRequestText request = WhatsAppSendRequestText.builder()
                .to(to)
                .messageContent(messageContent)
                .build();

        // Send the message
        return serviceWhatsApp.sendWhatsAppTextMessage(request);
    }


    @PostMapping("/sendTemplateMessage")
    public String sendWhatsAppTemplateMessage(
            @RequestParam String to,
            @RequestParam String bodyVariable1,
            @RequestParam String bodyVariable2) {
        // Build the WhatsApp request
        WhatsAppSendRequestTemplate request = WhatsAppSendRequestTemplate.builder()
                .to(to)
                .bodyVariable1(bodyVariable1)
                .bodyVariable2(bodyVariable2)
                .build();

        // Send the message
        return serviceWhatsApp.sendWhatsAppTemplateMessage(request);
    }

    @PostMapping("/sendTemplateMessageConfiramtion")
    public String sendTemplateMessageConfiramtion(
            @RequestParam String to) {
        // Build the WhatsApp request
        WhatsAppSendRequestTemplateConfirmation request = WhatsAppSendRequestTemplateConfirmation.builder()
                .to(to)
                .build();

        // Send the message
        return serviceWhatsApp.sendWhatsAppTemplateMessageConfirmation(request);
    }

    @PostMapping("/sendDynamicTemplateMessage")
    public String sendDynamicTemplateMessage(@RequestBody WhatsAppDynamicTemplateRequest request) {
        return serviceWhatsApp.sendDynamicTemplateMessage(request);
    }

    @PostMapping("/sendBatchDynamicTemplateMessages")
    public List<MessageResult> sendBatchDynamicTemplateMessages(@RequestBody WhatsAppBatchRequest batchRequest) {
        return serviceWhatsApp.sendBatchDynamicTemplateMessages(batchRequest.getMessages());
    }

//    @PostMapping("/sendNotification")
//    public Future<Boolean> sendNotificationWhatsApp(@RequestBody Notification notification) {
//        // Send a notification using the provided Notification object
//        return serviceWhatsApp.sendNotificationWhatsApp(notification);
//    }

    @GetMapping("/webhook")
    public String verifyWebhook(@RequestParam Map<String, String> params) {
        String mode = params.get("hub.mode");
        String token = params.get("hub.verify_token");
        String challenge = params.get("hub.challenge");

        // Verification logic (assuming VERIFY_TOKEN is defined elsewhere)
        if ("subscribe".equals(mode) && "mySecretVerifyToken".equals(token)) {
            return challenge; // Respond with the challenge token from Meta to verify your endpoint
        }

        System.out.println("Received webhook event: ");

        return "Verification failed";
    }

    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, Object> payload) {
        // Handle incoming webhook events
        System.out.println("Received webhook event: " + payload);
        // Add logic to process different event types if necessary
    }
}


