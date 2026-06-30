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
public class Score {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private Integer score;
    private String examType;
    private String examDate;

    public String getJson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("studentId", studentId);
        map.put("studentName", studentName);
        map.put("courseId", courseId);
        map.put("courseName", courseName);
        map.put("score", score);
        map.put("examType", examType);
        map.put("examDate", examDate);
        return new ObjectMapper().writeValueAsString(map);
    }
}