package ru.otus.rebuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ru.otus.data.SMSData;
import ru.otus.data.SMSJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new JsonMapper();
        try {
            SMSData data = objectMapper.readValue(SMSJson.SMS_JSON, SMSData.class);
            SMSDataRebuilder rebuilder = new SMSDataRebuilder();
            var info = rebuilder.parseInfoAndCorrespondingNumber(data);
            var result = rebuilder.groupInfoByNumberAndDate(info);
            String serializedNewData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println(serializedNewData);


            Path path = Paths.get("hw09-serialization","src", "main", "java", "ru", "otus", "rebuilder", "SMSDataNew.json");
            Files.writeString(path, serializedNewData);




        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
