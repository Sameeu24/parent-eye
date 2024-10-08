package com.parenteye.contentmanagement_service.domian;

import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "processed_messages")  // MongoDB collection name
public class ProcessedMessage {

    @Id
    private String id;  // MongoDB will generate one if not provided
    private String url;
    private String status;
    private int duration;
    private String originalMessage;
    private String updatedMessage;
    private Integer totalDuration;  // Total duration attribute

    public ProcessedMessage(String url, String status, int duration, String originalMessage, String updatedMessage, Integer totalDuration) {
        this.url = url;
        this.status = status;
        this.duration = duration;
        this.originalMessage = originalMessage;
        this.updatedMessage = updatedMessage;
        this.totalDuration = totalDuration;  // Initialize the total duration
    }
}
