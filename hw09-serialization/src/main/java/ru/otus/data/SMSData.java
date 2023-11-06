package ru.otus.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SMSData {
    @JsonProperty("chat_sessions")
    List<ChatSession> chatSessions;


}
