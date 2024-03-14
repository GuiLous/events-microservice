package com.guilou.eventsmicroservice.dtos;

public record EmailRequestDTO(String to, String subject, String body) {
}