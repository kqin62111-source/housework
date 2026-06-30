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
public class Schedule {
    private Long id;
    private Long classId;
    private String className;
    private Long courseId;
    private String courseName;
    private Long teacherId;
    private String teacherName;
    private String dayOfWeek;
    private Integer period;
    private String classroom;

    public String getJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("classId", classId);
        map.put("className", className);
        map.put("courseId", courseId);
        map.put("courseName", courseName);
        map.put("teacherId", teacherId);
        map.put("teacherName", teacherName);
        map.put("dayOfWeek", dayOfWeek);
        map.put("period", period);
        map.put("classroom", classroom);
        return new ObjectMapper().writeValueAsString(map);
    }
}