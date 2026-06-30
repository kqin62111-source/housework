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
public class Employee {
    private Long id;
    private String name;
    private String sn;
    private String phone;
    private String email;
    private Long departmentId;
    private String departmentName;
    private String position;

    public String getJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("sn", sn);
        map.put("phone", phone);
        map.put("email", email);
        map.put("departmentId", departmentId);
        map.put("departmentName", departmentName);
        map.put("position", position);
        return new ObjectMapper().writeValueAsString(map);
    }
}