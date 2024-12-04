package com.cbi.taskManager.dto;

public record LoginResponse(Long id, String email,String name,String lastName,  String generatedToken, Long expiresIn) {
}
