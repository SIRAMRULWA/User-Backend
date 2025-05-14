package com.sihlemrulwa.User_Backend.security;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        // Generate a cryptographically strong 512-bit (64-byte) secret key
        byte[] secretBytes = new byte[64]; // 512 bits = 64 bytes
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretBytes);

        // Base64 encode the secret
        String base64Secret = Base64.getEncoder().encodeToString(secretBytes);

        // Print out generation details
        System.out.println("JWT Secret Key Generator");
        System.out.println("========================");
        System.out.println("Key Length: 512 bits (64 bytes)");
        System.out.println("Encoding: Base64");
        System.out.println("\nGenerated JWT Secret (Base64 encoded):");
        System.out.println(base64Secret);

        // Optional: Provide instructions
        System.out.println("\nInstructions:");
        System.out.println("1. Copy the above secret");
        System.out.println("2. Add to application.properties:");
        System.out.println("   jwt.secret=" + base64Secret);
        System.out.println("3. Keep this secret confidential!");
    }
}
