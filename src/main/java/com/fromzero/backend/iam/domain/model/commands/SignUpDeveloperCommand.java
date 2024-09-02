package com.fromzero.backend.iam.domain.model.commands;



public record SignUpDeveloperCommand(String username , String password , String firstName, String lastName) {
}