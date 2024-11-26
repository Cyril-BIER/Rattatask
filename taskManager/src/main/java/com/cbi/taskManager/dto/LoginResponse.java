package com.cbi.taskManager.dto;

public record LoginResponse(Long id, String username, String generatedToken, Long expiresIn) {
}
