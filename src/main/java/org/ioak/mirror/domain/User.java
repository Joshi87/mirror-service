package org.ioak.mirror.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String problem;
    private String solution;
}
