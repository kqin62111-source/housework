package cn.wolfcode.mapper;

import cn.wolfcode.domain.Schedule;
import cn.wolfcode.domain.ScheduleQuery;

import java.util.List;

public interface ScheduleMapper {
    void addSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    Schedule getSchedule(Long id);
    List<Schedule> selectAllSchedule();
    List<Schedule> selectScheduleByQuery(ScheduleQuery query);
    void deleteByGradeId(Long gradeId);
    void deleteByClassId(Long classId);
    void deleteByCourseId(Long courseId);
    void deleteByTeacherId(Long teacherId);
    void deleteByDepartmentId(Long departmentId);
}