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
public class Permission {
    private Long id;
    private String name;
    private String sn;
    private String url;
    private String icon;
    private Integer type;
    private Long parentId;

    public String getJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("sn", sn);
        map.put("url", url);
        map.put("icon", icon);
        map.put("type", type);
        map.put("parentId", parentId);
        return new ObjectMapper().writeValueAsString(map);
    }
}