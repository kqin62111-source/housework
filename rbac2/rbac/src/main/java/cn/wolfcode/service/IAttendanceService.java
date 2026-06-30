package cn.wolfcode.service;

import cn.wolfcode.domain.Attendance;
import cn.wolfcode.domain.AttendanceQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface IAttendanceService {
    void addAttendance(Attendance attendance);
    void updateAttendance(Attendance attendance);
    void deleteAttendance(Long id);
    Attendance getAttendance(Long id);
    PageInfo<Attendance> selectAllAttendance(QueryObjet qo);
    PageInfo<Attendance> selectAttendanceByQuery(AttendanceQuery query);
    void saveOrUpdate(Attendance attendance);
}