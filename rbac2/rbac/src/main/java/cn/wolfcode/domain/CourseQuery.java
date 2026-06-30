package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseQuery extends QueryObjet {
    private String name;
    private String code;
}