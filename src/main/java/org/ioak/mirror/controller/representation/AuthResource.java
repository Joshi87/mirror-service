package org.ioak.mirror.controller.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthResource {
    @Data
    @NoArgsConstructor
    public static class KeyResponse {
        private String salt;
        private String solution;

    }

    @Data
    @NoArgsConstructor
    public static class SigninResponse {
        private String token;
        private String secret;
        private String firstname;
        private String lastname;
    }

    @Data
    @NoArgsConstructor
    public static class SigninRequest {
        private String name;
        private String solution;

    }
}
