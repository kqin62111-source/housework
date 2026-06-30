package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentQuery extends QueryObjet {
    private String name;
    private String gender;
    private String className;
    private String grade;
}