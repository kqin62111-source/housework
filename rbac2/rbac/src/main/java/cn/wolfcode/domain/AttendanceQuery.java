package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceQuery extends QueryObjet {
    private String studentName;
    private String status;
    private String attendanceDate;
}