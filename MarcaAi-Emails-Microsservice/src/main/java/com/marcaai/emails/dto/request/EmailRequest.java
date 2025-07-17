package com.marcaai.emails.dto.request;

public record EmailRequest(String to, String subject, String bodyText) {

}
