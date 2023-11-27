package ru.otus.rebuilder;

import ru.otus.data.ChatSession;
import ru.otus.data.Message;
import ru.otus.data.SMSData;
import ru.otus.data.SMSInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SMSDataRebuilder {

    public Map<SMSInfo, String> parseInfoAndCorrespondingNumber(SMSData smsData) {
        Map<SMSInfo, String> infoNumMap = new HashMap<>();
        for (ChatSession chatSession : smsData.getChatSessions()) {
            for (Message message : chatSession.getMessages()) {
                SMSInfo info = new SMSInfo();
                info.setChatIdentifier(chatSession.getChatIdentifier());
                info.setText(message.getText());
                info.setSendDate(message.getSendDate());
                info.setLastMember(chatSession.getLastMemberByMessageId(message));
                infoNumMap.put(info, message.getBelongNumber());
            }
        }
        return infoNumMap;
    }

    public Map<String, List<SMSInfo>> groupInfoByNumberAndDate(Map<SMSInfo, String> infoNumMap) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        Map<String, List<SMSInfo>> modifiedMap = new HashMap<>();
        for (var entry : infoNumMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (modifiedMap.containsKey(value)) {
                modifiedMap.get(value).add(key);
                modifiedMap.get(value).sort(Comparator.comparing(info -> LocalDateTime.parse(info.getSendDate(), formatter)));
            } else {
                List<SMSInfo> infos = new ArrayList<>();
                infos.add(key);
                modifiedMap.put(value, infos);
            }
        }
        return modifiedMap;
    }
}



