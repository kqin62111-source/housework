package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceReportQuery {
    private String studentName;
    private String startDate;
    private String endDate;
}
