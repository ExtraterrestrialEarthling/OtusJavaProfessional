package ru.otus.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
public class Message {
    @JsonProperty(value = "ROWID", access = WRITE_ONLY)
    private long rowId;
    @JsonProperty(access = WRITE_ONLY)
    private String attributedBody;
    @JsonProperty("belong_number")
    private String belongNumber;
    @JsonProperty(access = WRITE_ONLY)
    private long date;
    @JsonProperty(value = "date_read", access = WRITE_ONLY)
    private long dateRead;
    @JsonProperty(access = WRITE_ONLY)
    private String guid;
    @JsonProperty(value = "handle_id", access = WRITE_ONLY)
    private int handleId;
    @JsonProperty(value = "has_dd_results", access = WRITE_ONLY)
    private int hasDdResults;
    @JsonProperty(value = "is_deleted", access = WRITE_ONLY)
    private int isDeleted;
    @JsonProperty(value = "is_from_me", access = WRITE_ONLY)
    private int isFromMe;
    @JsonProperty("send_date")
    private String sendDate;
    @JsonProperty(value = "send_status", access = WRITE_ONLY)
    private int sendStatus;
    @JsonProperty(access = WRITE_ONLY)
    private String service;
    @JsonProperty
    private String text;
}
