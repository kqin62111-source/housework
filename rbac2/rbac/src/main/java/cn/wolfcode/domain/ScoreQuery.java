package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreQuery extends QueryObjet {
    private String studentName;
    private String courseName;
    private Integer minScore;
    private Integer maxScore;
}