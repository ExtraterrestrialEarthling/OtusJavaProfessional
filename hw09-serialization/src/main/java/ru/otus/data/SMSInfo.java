package ru.otus.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SMSInfo {
    @JsonProperty("chat_identifier")
    private String chatIdentifier;
    @JsonProperty("send_date")
    private String sendDate;
    @JsonProperty
    private String text;
    @JsonProperty("last_member")
    private String lastMember;
}

