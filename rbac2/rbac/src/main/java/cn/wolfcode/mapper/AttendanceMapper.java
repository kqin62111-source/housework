package cn.wolfcode.mapper;

import cn.wolfcode.domain.Attendance;
import cn.wolfcode.domain.AttendanceQuery;

import java.util.List;

public interface AttendanceMapper {
    void addAttendance(Attendance attendance);
    void updateAttendance(Attendance attendance);
    void deleteAttendance(Long id);
    Attendance getAttendance(Long id);
    List<Attendance> selectAllAttendance();
    List<Attendance> selectAttendanceByQuery(AttendanceQuery query);
    void deleteByStudentId(Long studentId);
}