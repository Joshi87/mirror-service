package org.ioak.mirror.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "bookmark")
public class Bookmark {
    @Id
    private String id;
    private String userId;
    private String href;
    private String title;
    private String description;
    private String tags;
    private Date createdDate;
    private Date lastModifiedDate;


}
