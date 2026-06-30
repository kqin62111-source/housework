package cn.wolfcode.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatus {
    private Long id;
    private Long studentId;
    private String studentName;
    private String status;
    private String reason;
    private String operateDate;
    private String operator;

    public String getJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("studentId", studentId);
        map.put("studentName", studentName);
        map.put("status", status);
        map.put("reason", reason);
        map.put("operateDate", operateDate);
        map.put("operator", operator);
        return new ObjectMapper().writeValueAsString(map);
    }
}