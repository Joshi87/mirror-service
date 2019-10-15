package org.ioak.mirror.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUser {
    private String id;
    private String email;
}
