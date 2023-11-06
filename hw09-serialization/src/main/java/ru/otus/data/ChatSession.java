package ru.otus.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
public class ChatSession {
    @JsonProperty(value = "chat_id", access = WRITE_ONLY)
    private int chatId;
    @JsonProperty("chat_identifier")
    private String chatIdentifier;
    @JsonProperty(value = "display_name", access = WRITE_ONLY)
    private String displayName;
    @JsonProperty(value = "is_deleted", access = WRITE_ONLY)
    private int isDeleted;
    @JsonProperty
    private List<Member> members;
    @JsonProperty
    private List<Message> messages;


    public String getLastMemberByMessageId(Message message) {
        for (Member member : members) {
                if (member.getHandleId() == message.getHandleId()) {
                    return member.getLast();
            }
        }
        return null;
    }
}
