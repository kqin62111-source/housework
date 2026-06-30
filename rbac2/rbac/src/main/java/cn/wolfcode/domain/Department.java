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
public class Department {
    private Long id;
    private String name;
    private String sn;

    public String getJson() throws JsonProcessingException {
        Map<String ,Object > map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("sn",sn);
        return new ObjectMapper().writeValueAsString(map);
    }
}
