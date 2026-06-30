package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleReportQuery {
    private String className;
    private String teacherName;
    private String dayOfWeek;
}
