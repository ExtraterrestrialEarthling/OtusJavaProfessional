package ru.otus.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
public class Member {
    @JsonProperty(access = WRITE_ONLY)
    private String first;
    @JsonProperty(value = "handle_id", access = WRITE_ONLY)
    private int handleId;
    @JsonProperty(value = "image_path", access = WRITE_ONLY)
    private String imagePath;
    @JsonProperty
    private String last;
    @JsonProperty(access = WRITE_ONLY)
    private String middle;
    @JsonProperty(value = "phone_number", access = WRITE_ONLY)
    private String phoneNumber;
    @JsonProperty(access = WRITE_ONLY)
    private String service;
    @JsonProperty(value = "thumb_path", access = WRITE_ONLY)
    private String thumbPath;
}
