package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleQuery extends QueryObjet {
    private String className;
    private String courseName;
    private String teacherName;
    private String dayOfWeek;
}